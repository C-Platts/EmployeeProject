package com.sparta.cp.concurrency;

import com.sparta.cp.csvproject.dto.EmployeeDTO;
import com.sparta.cp.csvproject.jdbc.concurrency.ConnectionPool;
import com.sparta.cp.csvproject.jdbc.concurrency.ThreadPool;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ConcurrencyTests {

    private static ThreadPool threadPool;
    private static ConnectionPool connectionPool;
    private static List<EmployeeDTO> employeeList;
    private static String[] recordList;

    @BeforeAll
    static void setup() {

        final int THREAD_COUNT = 1;

        threadPool = new ThreadPool(THREAD_COUNT);
        connectionPool = new ConnectionPool(THREAD_COUNT);

        recordList = new String[]
                {
                        "1, Mr, Test, A, First, M, tFirst@email.com, 1/11/1111, 2/22/2222, 12345",
                        "2, Mr, Test, B, Second, M, tSecond@email.com, 1/11/1111, 2/22/2222, 12345",
                        "3, Mr, Test, C, Third, M, tThird@email.com, 1/11/1111, 2/22/2222, 12345",
                        "4, Mr, Test, D, Fourth, M, tFourth@email.com, 1/11/1111, 2/22/2222, 12345",
                        "5, Mr, Test, E, Fifth, M, tFifth@email.com, 1/11/1111, 2/22/2222, 12345"
                };

        employeeList = new ArrayList<>();
        employeeList.add(new EmployeeDTO(recordList));

    }

    @Test
    public void placeholder() {

    }

}
