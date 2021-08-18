package com.sparta.cp;

import com.sparta.cp.csvproject.csv.CsvManager;
import com.sparta.cp.csvproject.dto.EmployeeDTO;

import java.util.ArrayList;

public class Loader {

    public void start() {
        CsvManager csv = new CsvManager();
        ArrayList<EmployeeDTO> employeeList;


        employeeList = csv.readRecords();



    }

}
