package com.sparta.cp.csvproject.jdbc;

import java.sql.Connection;
import java.util.LinkedList;

public class ConnectionPool {

    private LinkedList<Connection> pool;
    private final int size;

    public ConnectionPool(int size) {
        this.size = size;

    }

    private void initialisePool() {
        for(int i = 0; i < size; i ++) {
            pool.add(ConnectionManager.connectToDatabase());
        }
    }

    public synchronized Connection getConnection() {
        return pool.pop();
    }

}
