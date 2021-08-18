package com.sparta.cp.csvproject.jdbc.dao;

import java.sql.*;

public class EmployeeDAO {

    private Connection connection;
    private Statement statement;

    private static final String GET_RECORD_COUNT = "SELECT COUNT(*) FROM employees_db.employees";

    EmployeeDAO(Connection connection) {
        this.connection = connection;

        try {
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Create

    //Read
    public ResultSet ReadRecords(String query) {

        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public int getRecordCount() {
        try {
            ResultSet count = statement.executeQuery(GET_RECORD_COUNT);
            return count.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
