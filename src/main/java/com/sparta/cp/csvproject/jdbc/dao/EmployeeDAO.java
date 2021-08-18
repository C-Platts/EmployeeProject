package com.sparta.cp.csvproject.jdbc.dao;

import java.sql.*;
import java.time.LocalDate;

public class EmployeeDAO {

    private Connection connection;
    private Statement statement;

    private static final String GET_RECORD_COUNT = "SELECT COUNT(*) FROM employees_db.employee";
    private static final String CREATE_NEW_RECORD = "INSERT INTO `employee_db`.`employee` (`idemployee`, `name_prefix`, `first_name`, `middle_initial`, `last_name`, `gender`, `email`, `date_of_birth`, `date_of_joining`, `salary`) VALUES (?,?,?,?,?,?,?,?,?,?)";

    EmployeeDAO(Connection connection) {
        this.connection = connection;

        try {
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Create
    public void createRecord(int id, String namePrefix, String firstName, char middleInitial, String lastName, char gender, String email, LocalDate dateOfBith, LocalDate dateOfJoining, int salary) {
            try {

                PreparedStatement preparedStatement = connection.prepareStatement(CREATE_NEW_RECORD);

                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, firstName);
                preparedStatement.setString(3, Character.toString(middleInitial));
                preparedStatement.setString(4, lastName);
                preparedStatement.setString(5, Character.toString(gender));
                preparedStatement.setString(6, email);
                preparedStatement.setDate(7, Date.valueOf(dateOfBith));
                preparedStatement.setDate(8, Date.valueOf(dateOfJoining));
                preparedStatement.setInt(9, salary);

                preparedStatement.execute();
                preparedStatement.close();

            }catch (SQLException e) {
                e.printStackTrace();
            }
    }


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
