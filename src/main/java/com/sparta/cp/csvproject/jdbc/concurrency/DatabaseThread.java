package com.sparta.cp.csvproject.jdbc.concurrency;

import com.sparta.cp.csvproject.dto.EmployeeDTO;
import com.sparta.cp.csvproject.jdbc.DatabaseManager;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DatabaseThread implements Runnable {

    Connection connection;
    List<EmployeeDTO> employees;
    DatabaseManager dbManager;

    public DatabaseThread(List<EmployeeDTO> list, Connection connection) {
        this.employees = list;
        this.connection = connection;
        this.dbManager = new DatabaseManager(connection);
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void run() {
        dbManager.addEmployeesToDB(employees);
    }


}
