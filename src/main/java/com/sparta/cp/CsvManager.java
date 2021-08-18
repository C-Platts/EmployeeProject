package com.sparta.cp;

import com.sparta.cp.csvproject.dto.EmployeeDTO;
import com.sparta.cp.csvproject.reader.CsvFileFilter;
import com.sparta.cp.csvproject.reader.CsvFileReader;

import java.util.ArrayList;

public class CsvManager {

    private final String fileName = "src/main/resources/CustomRecords.csv";
    private CsvFileFilter filter;
    private CsvFileReader reader;

    public void start() {

        reader = new CsvFileReader();

        ArrayList<EmployeeDTO> employees = (ArrayList<EmployeeDTO>) reader.readFromFile(fileName, new EmployeeVerifier());



    }


}
