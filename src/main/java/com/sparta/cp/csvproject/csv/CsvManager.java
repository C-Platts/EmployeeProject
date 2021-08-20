package com.sparta.cp.csvproject.csv;

import com.sparta.cp.csvproject.dto.EmployeeDTO;

import java.util.ArrayList;

public class CsvManager {



    public ArrayList<EmployeeDTO> readRecords(CsvFileReader reader, String fileName) {

        //read from file
        //TODO: change method name - not clear
        CsvPrinter.printMessageReadingFromCsvFile(fileName);
        ArrayList<String> list = reader.readFromCsvFile(fileName);
        CsvPrinter.printNumberOfRecords(list);


        //Remove records without 10 attributes
        CsvPrinter.printMessageFilteringByRecordLength();
        ArrayList<String[]> attributeList = CsvFilter.getRecordsWithLengthTen(list);
        CsvPrinter.printNumberOfInvalidRecords(list.size(), attributeList);


        //Remove duplicate records
        CsvPrinter.printTaskRemovingDuplicates();
        ArrayList<EmployeeDTO> employeeList = CsvFilter.getUniqueRecordsList(attributeList);
        CsvPrinter.printNumberOfInvalidRecords(attributeList.size(), employeeList);
        CsvPrinter.printRemainingRecordsCount(employeeList);

        return employeeList;
    }


}
