package com.sparta.cp.csvproject.csv;

import com.sparta.cp.csvproject.csv.CsvFilter;
import com.sparta.cp.csvproject.csv.CsvPrinter;
import com.sparta.cp.csvproject.dto.EmployeeDTO;
import com.sparta.cp.csvproject.csv.CsvFileReader;

import java.util.ArrayList;

public class CsvManager {

    public ArrayList<EmployeeDTO> readRecords() {

        CsvFileReader reader = new CsvFileReader();
        String fileName = "src/main/resources/EmployeeRecords.csv";

        //read from file
        CsvPrinter.printTaskReading(fileName);
        ArrayList<String> list = reader.readFromFile(fileName);
        CsvPrinter.printNumberOfRecords(list);


        //Remove records without 10 attributes
        CsvPrinter.printTaskFilteringLength();
        ArrayList<String[]> attributeList = CsvFilter.filterLinesLength(list);
        CsvPrinter.printNumberOfInvalidRecords(list.size(), attributeList);


        //Remove duplicate records
        CsvPrinter.printTaskRemovingDuplicates();
        ArrayList<EmployeeDTO> employeeList = CsvFilter.filterDuplicates(attributeList);
        CsvPrinter.printNumberOfInvalidRecords(attributeList.size(), employeeList);
        CsvPrinter.printRemainingrecordsCount(employeeList);

        return employeeList;
    }


}
