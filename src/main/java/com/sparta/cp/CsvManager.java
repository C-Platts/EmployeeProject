package com.sparta.cp;

import com.sparta.cp.csvproject.dto.EmployeeDTO;
import com.sparta.cp.csvproject.reader.CsvFileReader;

import java.util.ArrayList;

public class CsvManager {

    private final String fileName = "src/main/resources/EmployeeRecords.csv";
    private CsvFileReader reader;

    public void start() {

        reader = new CsvFileReader();

        ArrayList<EmployeeDTO> employees = (ArrayList<EmployeeDTO>) reader.readFromFile(fileName);



    }


}
