package com.sparta.cp.csvproject.jdbc.concurrency;

import com.sparta.cp.csvproject.jdbc.ConnectionManager;

import java.sql.Connection;
import java.util.LinkedList;

public class ConnectionPool {

    private LinkedList<Connection> pool;
    private final int size;

    public ConnectionPool(int size) {
        this.size = size;
        initialisePool();

    }

    private void initialisePool() {
        for(int i = 0; i < size; i ++) {
            pool.push(ConnectionManager.connectToDatabase());
        }
    }

    public synchronized Connection getConnection() {
        return pool.pop();
    }

    public synchronized void returnConnection(Connection connection) {
        pool.push(connection);
    }

}
