package com.sparta.cp;

import com.sparta.cp.csvproject.csv.CsvFileReader;
import com.sparta.cp.csvproject.csv.CsvManager;
import com.sparta.cp.csvproject.dto.EmployeeDTO;
import com.sparta.cp.csvproject.jdbc.concurrency.ConnectionPool;
import com.sparta.cp.csvproject.jdbc.DatabaseManager;
import com.sparta.cp.csvproject.jdbc.concurrency.ThreadPool;
import com.sparta.cp.csvproject.util.DatabasePrinter;
import com.sparta.cp.csvproject.util.ListSplitter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Loader {

    private static final int THREAD_COUNT = 90;

    private final ConnectionPool connectionPool = new ConnectionPool(THREAD_COUNT);
    private final ThreadPool threadPool = new ThreadPool(THREAD_COUNT);

    private final DatabaseManager dbManager = new DatabaseManager();
    private final CsvManager csv = new CsvManager();

    private final String fileName = "src/main/resources/EmployeeRecordsLarge.csv";

    public void start() {

        //Read records from file.
        ArrayList<EmployeeDTO> employeeList = csv.readRecords(new CsvFileReader(), fileName);


        //split ListArray into NUM_THREADS segments
        int segmentSize = (employeeList.size() / THREAD_COUNT + 1);
        LinkedList<List<EmployeeDTO>> segments = ListSplitter.split(employeeList, segmentSize);

        //Give each thread its own segment of unique values to push to the database
        for(int i = 0; i < THREAD_COUNT; i ++) {
            threadPool.initialiseThread(segments.get(i), connectionPool.getConnection(), i);
        }

        DatabasePrinter.printTaskInputtingRecords();
        double start = System.nanoTime();
        //Begin inputting files into database
        threadPool.start();

        //Join all threads together
        try {
            threadPool.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double end = System.nanoTime();

        DatabasePrinter.printTaskReadingRecordCount();
        DatabasePrinter.printRecordCount(dbManager.getRecordCount());
        System.out.println("\n Time taken to input records: " + ((end - start) / 1000_000_000));

        dbManager.truncateTable();
    }

}
