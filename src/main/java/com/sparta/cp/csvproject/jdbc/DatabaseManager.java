package com.sparta.cp.csvproject.jdbc;

import com.sparta.cp.csvproject.dto.EmployeeDTO;
import com.sparta.cp.csvproject.jdbc.dao.EmployeeDAO;
import com.sparta.cp.csvproject.jdbc.util.DatabasePrinter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private  EmployeeDAO employeeDAO;

    public DatabaseManager() {
        employeeDAO = new EmployeeDAO(ConnectionManager.connectToDatabase());
    }

    public DatabaseManager(Connection connection) {
        employeeDAO = new EmployeeDAO(connection);
    }

    public void addEmployeesToDB(List<EmployeeDTO> employees) {

        for (EmployeeDTO employee: employees) {

             employeeDAO.createRecord(
                    employee.getId(), employee.getNamePrefix(),
                    employee.getFirstName(), employee.getMiddleInitial(),
                    employee.getLastName(), employee.getGender(),
                    employee.getEmail(), employee.getDateOfBirth(),
                    employee.getDateOfJoining(), employee.getSalary());

        }

    }

    public void getAllEmployees() {
        try {
            DatabasePrinter.printTaskReadingRecords();

            DatabasePrinter.printRecords(
                employeeDAO.ReadRecords("SELECT * FROM employee_db.employee"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getRecordCount() {
        return employeeDAO.getRecordCount();
    }

    public boolean truncateTable() {
        return employeeDAO.truncateTable();
    }

}
