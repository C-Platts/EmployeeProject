package com.sparta.cp.csvproject.jdbc.concurrency;

import java.sql.Connection;
import java.util.List;

public class ThreadPool {

    private Thread[] pool;
    private static int THREAD_COUNT;

    public ThreadPool(int threadCount) {
        THREAD_COUNT = threadCount;
        pool = new Thread[THREAD_COUNT];
    }

    public void initialiseThread(Runnable threadType, List segment, Connection connection) {

        for(int i = 0; i < THREAD_COUNT; i ++) {
            pool[i] = new Thread(new DatabaseThread(segment, connection));
        }

    }

/*
    public void start() {

        for()

    }
*/

}
