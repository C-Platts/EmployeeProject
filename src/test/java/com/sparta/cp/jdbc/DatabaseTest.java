package com.sparta.cp.jdbc;

import com.sparta.cp.csvproject.dto.EmployeeDTO;
import com.sparta.cp.csvproject.jdbc.DatabaseManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DatabaseTest {

    private static DatabaseManager dbManager;

    @BeforeAll
    static void setup() {
        dbManager = new DatabaseManager();
    }

    @Test
    public void testEmployeeCanBeAddedToDatabase() {

        EmployeeDTO testEmployee = new EmployeeDTO(
                new String[]{"1", "Mr", "Test", "T", "Testington", "M", "test@test.com", "11/11/1111", "2/2/2222", "12345"}
        );

        ArrayList<EmployeeDTO> list =  new ArrayList<EmployeeDTO>();
        list.add(testEmployee);

        dbManager.addEmployeesToDB(list);

        Assertions.assertEquals(1, dbManager.getRecordCount());

        dbManager.truncateTable();

    }

    @Test
    public void testDatabaseCanBeReadFrom() {

        dbManager.getAllEmployees();

        //Assertions.assertTrue();

    }

}
