package com.sparta.cp;

import com.sparta.cp.csvproject.csv.CsvFilter;
import com.sparta.cp.csvproject.csv.CsvPrinter;
import com.sparta.cp.csvproject.dto.EmployeeDTO;
import com.sparta.cp.csvproject.csv.CsvFileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ReadFromFileTest {

    private static CsvFileReader reader;
    private static ArrayList<EmployeeDTO> employees;

    @BeforeAll
    static void setup() {
        reader = new CsvFileReader();
    }

    @Test
    public void testValidRecordIsRead() {

        String fileName = "src/main/resources/EmployeeRecords.csv";
        ArrayList<String> list = reader.readFromCsvFile(fileName);

        CsvPrinter.printNumberOfRecords(list);

        ArrayList<String[]> attributeList = CsvFilter.getRecordsWithLengthTen(list);

        CsvPrinter.printNumberOfInvalidRecords(list.size(), attributeList);

        ArrayList<EmployeeDTO> employeeList = CsvFilter.getUniqueRecordsList(attributeList);

        CsvPrinter.printNumberOfInvalidRecords(attributeList.size(), employeeList);

        Assertions.assertEquals(1, employees.size());

    }

    @Test
    public void testDuplicatesAreIgnored() {
        String fileName = "src/main/resources/EmployeeRecords.csv";
        ArrayList<String> list = reader.readFromCsvFile(fileName);

        CsvPrinter.printNumberOfRecords(list);

        ArrayList<String[]> attributeList = CsvFilter.getRecordsWithLengthTen(list);

        CsvPrinter.printNumberOfInvalidRecords(list.size(), attributeList);

        ArrayList<EmployeeDTO> employeeList = CsvFilter.getUniqueRecordsList(attributeList);

        CsvPrinter.printNumberOfInvalidRecords(attributeList.size(), employeeList);

        Assertions.assertEquals(4, employees.size());
    }

    @Test
    public void testInvalidRecordsAreIgnored() {
        String fileName = "src/main/resources/EmployeeRecords.csv";
        ArrayList<String> list = reader.readFromCsvFile(fileName);

        CsvPrinter.printNumberOfRecords(list);

        ArrayList<String[]> attributeList = CsvFilter.getRecordsWithLengthTen(list);

        CsvPrinter.printNumberOfInvalidRecords(list.size(), attributeList);

        ArrayList<EmployeeDTO> employeeList = CsvFilter.getUniqueRecordsList(attributeList);

        CsvPrinter.printNumberOfInvalidRecords(attributeList.size(), employeeList);

        Assertions.assertEquals(3, employees.size());
    }

}
