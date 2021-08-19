package com.sparta.cp;

import com.sparta.cp.csvproject.csv.CsvManager;
import com.sparta.cp.csvproject.dto.EmployeeDTO;
import com.sparta.cp.csvproject.jdbc.concurrency.ConnectionPool;
import com.sparta.cp.csvproject.jdbc.DatabaseManager;
import com.sparta.cp.csvproject.jdbc.concurrency.DatabaseThread;
import com.sparta.cp.csvproject.jdbc.util.DatabasePrinter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;

public class Loader {

    private static final int THREAD_COUNT = 90;
    private final ConnectionPool pool = new ConnectionPool(THREAD_COUNT);
    private DatabaseManager dbManager = new DatabaseManager();
    private final Thread[] threads = new Thread[THREAD_COUNT];

    public void start() {
        CsvManager csv = new CsvManager();

        ArrayList<EmployeeDTO> employeeList = csv.readRecords();

        int segmentSize = (employeeList.size() / THREAD_COUNT + 1);

        //split ListArray into NUM_THREADS segments, passing each segment goes to a thread
        LinkedList<List<EmployeeDTO>> segments = new LinkedList<>();
        for (int i = 0 ; i < employeeList.size(); i+= segmentSize) {
            segments.add(
                    employeeList.subList(i, Math.min(i + segmentSize, employeeList.size()))
            );
        }

        System.out.println(segments.size());


        DatabasePrinter.printTaskInputtingRecords();
        double start = System.nanoTime();

        for(int i = 0; i < THREAD_COUNT; i ++) {
            threads[i] = new Thread(new DatabaseThread(segments.get(i), pool.getConnection()));
            threads[i].start();
        }

        //dbManager.addEmployeesToDB(employeeList);

        for(Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
