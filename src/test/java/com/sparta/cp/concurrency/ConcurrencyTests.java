package com.sparta.cp.concurrency;

import com.sparta.cp.csvproject.dto.EmployeeDTO;
import com.sparta.cp.csvproject.jdbc.DatabaseManager;
import com.sparta.cp.csvproject.jdbc.concurrency.ConnectionPool;
import com.sparta.cp.csvproject.jdbc.concurrency.ThreadPool;
import com.sparta.cp.csvproject.util.ListSplitter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConcurrencyTests {

    private static ThreadPool threadPool;
    private static ConnectionPool connectionPool;
    private static List<EmployeeDTO> employeeList;
    private static ArrayList<String[]> recordList;
    private static DatabaseManager dbManager;
    private static int THREAD_COUNT;
    private static LinkedList<List<EmployeeDTO>> segments;

    @BeforeAll
    static void setup() {

        dbManager = new DatabaseManager();



        recordList = new ArrayList<String[]>();
        recordList.add(
                new String[]
                        {"1", "Mr", "Test", "A", "First", "M", "tFirst@email.com", "1/11/1111", "2/22/2222", "12345"}
        );
        recordList.add(
                new String[]
                        {"2", "Mr", "Test", "A", "First", "M", "tFirst@email.com", "1/11/1111", "2/22/2222", "12345"}
        );
        recordList.add(
                new String[]
                        {"3", "Mr", "Test", "A", "First", "M", "tFirst@email.com", "1/11/1111", "2/22/2222", "12345"}
        );
        recordList.add(
                new String[]
                        {"4", "Mr", "Test", "A", "First", "M", "tFirst@email.com", "1/11/1111", "2/22/2222", "12345"}
        );
        recordList.add(
                new String[]
                        {"5", "Mr", "Test", "A", "First", "M", "tFirst@email.com", "1/11/1111", "2/22/2222", "12345"}
        );
        recordList.add(
                new String[]
                        {"6", "Mr", "Test", "A", "First", "M", "tFirst@email.com", "1/11/1111", "2/22/2222", "12345"}
        );
        recordList.add(
                new String[]
                        {"7", "Mr", "Test", "A", "First", "M", "tFirst@email.com", "1/11/1111", "2/22/2222", "12345"}
        );
        recordList.add(
                new String[]
                        {"8", "Mr", "Test", "A", "First", "M", "tFirst@email.com", "1/11/1111", "2/22/2222", "12345"}
        );
        recordList.add(
                new String[]
                        {"9", "Mr", "Test", "A", "First", "M", "tFirst@email.com", "1/11/1111", "2/22/2222", "12345"}
        );

        employeeList = new ArrayList<>();

        for(String[] record : recordList) {
            employeeList.add(new EmployeeDTO(record));
        }

    }

    @Test
    public void testMultipleThreadsAreFasterThanOne() {

        dbManager.truncateTable();

        setupThreads(1);
        double singleThreadTime = getTime();

        dbManager.truncateTable();

        setupThreads(8);
        double multiThreadTime = getTime();

        dbManager.truncateTable();

        Assertions.assertTrue(multiThreadTime < singleThreadTime);

    }

    private double getTime() {
        double start = System.nanoTime();
        threadPool.start();

        try {
            threadPool.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double end = System.nanoTime();

        return end - start;
    }

    private void setupThreads(int numThreads) {
        threadPool = new ThreadPool(numThreads);
        connectionPool = new ConnectionPool(numThreads);

        segments = ListSplitter.split(employeeList, (employeeList.size() / numThreads));

        for(int i = 0; i < numThreads; i ++) {
            threadPool.initialiseThread(segments.get(i), connectionPool.getConnection(), i);
        }
    }

}
