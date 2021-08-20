package com.sparta.cp.csvproject.jdbc.concurrency;

import com.sparta.cp.csvproject.dto.EmployeeDTO;

import java.sql.Connection;
import java.util.List;

public class ThreadPool {

    private final Thread[] pool;
    private static int THREAD_COUNT;

    public ThreadPool(int threadCount) {
        THREAD_COUNT = threadCount;
        pool = new Thread[THREAD_COUNT];
    }

    public static int getThreadCount() {
        return THREAD_COUNT;
    }

    public void initialiseThread(List<EmployeeDTO> segment, Connection connection, int threadNumber) {

        pool[threadNumber] = new Thread(new DatabaseThread(segment, connection));

    }


    public void start() {

        for(Thread thread : pool) {
            thread.start();
        }

    }

    public void join() throws InterruptedException {

        for(Thread thread : pool) {
            thread.join();
        }
    }


}
