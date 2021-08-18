package com.sparta.cp;

import com.sparta.cp.csvproject.dto.EmployeeDTO;
import com.sparta.cp.csvproject.dto.EmployeeVerifier;
import com.sparta.cp.csvproject.reader.CsvFileReader;
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

        employees = (ArrayList<EmployeeDTO>) reader.readFromFile("src/test/resources/Valid Record.csv", new EmployeeVerifier());

        Assertions.assertEquals(1, employees.size());

    }

    @Test
    public void testDuplicatesAreIgnored() {
        employees = (ArrayList<EmployeeDTO>) reader.readFromFile("src/test/resources/Duplicates.csv", new EmployeeVerifier());

        Assertions.assertEquals(4, employees.size());
    }

    @Test
    public void testInvalidRecordsAreIgnored() {
        employees = (ArrayList<EmployeeDTO>) reader.readFromFile("src/test/resources/InvalidRecords.csv", new EmployeeVerifier());

        Assertions.assertEquals(3, employees.size());
    }

}
