package com.sparta.cp;

import com.sparta.cp.csvproject.csv.CsvManager;
import com.sparta.cp.csvproject.dto.EmployeeDTO;
import com.sparta.cp.csvproject.jdbc.ConnectionManager;
import com.sparta.cp.csvproject.jdbc.DatabaseManager;
import com.sparta.cp.csvproject.jdbc.dao.EmployeeDAO;
import com.sparta.cp.csvproject.jdbc.util.DatabasePrinter;

import java.sql.Connection;
import java.util.ArrayList;

public class Loader {

    public void start() {
        CsvManager csv = new CsvManager();
        DatabaseManager dbManager = new DatabaseManager();

        ArrayList<EmployeeDTO> employeeList = csv.readRecords();

        DatabasePrinter.printTaskInputtingRecords();
        double start = System.nanoTime();
        dbManager.addEmployeesToDB(employeeList);
        double end = System.nanoTime();
        DatabasePrinter.printTaskReadingRecordCount();
        DatabasePrinter.printRecordCount(dbManager.getRecordCount());
        System.out.println("Time taken to input records: " + ((end - start) / 1000_000_000));


    }

}
