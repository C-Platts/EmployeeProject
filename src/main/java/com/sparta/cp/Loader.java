package com.sparta.cp;

import com.sparta.cp.csvproject.csv.CsvManager;
import com.sparta.cp.csvproject.dto.EmployeeDTO;
import com.sparta.cp.csvproject.jdbc.concurrency.ConnectionPool;
import com.sparta.cp.csvproject.jdbc.DatabaseManager;
import com.sparta.cp.csvproject.jdbc.concurrency.ThreadPool;
import com.sparta.cp.csvproject.jdbc.util.DatabasePrinter;
import com.sparta.cp.csvproject.jdbc.util.ListSplitter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Loader {

    private static final int THREAD_COUNT = 90;

    private final ConnectionPool connectionPool = new ConnectionPool(THREAD_COUNT);
    private final ThreadPool threadPool = new ThreadPool(THREAD_COUNT);

    private final DatabaseManager dbManager = new DatabaseManager();
    private final CsvManager csv = new CsvManager();


    public void start() {

        //Read records from file.
        ArrayList<EmployeeDTO> employeeList = csv.readRecords();


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

        //Time benchmark = 110.35 seconds (Large file)
        //2 threads = 83.23 seconds
        //4 threads = 44.21 seconds
        //8 threads = 24.62
        //9 threads = 23.08
        //10 threads = (1st): 25.92, (2nd): 20.92
        //11 threads = 20.74
        //12 threads = 19.12
        //24 threads = 11.94
        //32 threads = 10.38
        //64 threads = 8.17
        //70 threads = 8.00
        //80 threads = 7.86
        //90 threads = 7.39 <---Optimal
        //91 threads = (1st): 7.61, (2nd): 7.81
        //92 threads = (1st): 8.12, (2nd): 7.73
        //100 threads =
        //128 threads = 13.32
    }

}
