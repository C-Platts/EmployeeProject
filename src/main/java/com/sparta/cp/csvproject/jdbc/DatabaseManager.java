package com.sparta.cp.csvproject.jdbc;

import com.sparta.cp.csvproject.dto.EmployeeDTO;
import com.sparta.cp.csvproject.jdbc.dao.EmployeeDAO;

import java.sql.Connection;
import java.util.ArrayList;

public class DatabaseManager {

    private Connection connection;
    private  EmployeeDAO employeeDAO;

    public DatabaseManager() {
        connection = ConnectionManager.connectToDatabase();
        employeeDAO = new EmployeeDAO(connection);
    }

    public void addEmployeesToDB(ArrayList<EmployeeDTO> employees) {

        for (EmployeeDTO employee: employees) {

             if( employeeDAO.createRecord(
                    employee.getId(), employee.getNamePrefix(),
                    employee.getFirstName(), employee.getMiddleInitial(),
                    employee.getLastName(), employee.getGender(),
                    employee.getEmail(), employee.getDateOfBirth(),
                    employee.getDateOfJoining(), employee.getSalary()
            )) {
                 System.err.println("Error entering new employee!");
                 break;
            }

        }

    }

    public int getRecordCount() {
        return employeeDAO.getRecordCount();
    }

}
