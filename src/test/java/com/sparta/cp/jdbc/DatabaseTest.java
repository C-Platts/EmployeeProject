package com.sparta.cp.jdbc;

import com.sparta.cp.csvproject.dto.EmployeeDTO;
import com.sparta.cp.csvproject.jdbc.DatabaseManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DatabaseTest {

    private static DatabaseManager dbManager;
    private static EmployeeDTO testEmployee;
    private static ArrayList<EmployeeDTO> list;

    @BeforeAll
    static void setup() {
        dbManager = new DatabaseManager();

         testEmployee = new EmployeeDTO(
                new String[]{"1", "Mr", "Test", "T", "Testington", "M", "test@test.com", "11/11/1111", "2/2/2222", "12345"}
        );

        list =  new ArrayList<EmployeeDTO>();
        list.add(testEmployee);

        dbManager.truncateTable();

    }

    @Test
    public void testEmployeeCanBeAddedToDatabase() {

        dbManager.addEmployeesToDB(list);

        Assertions.assertEquals(1, dbManager.getRecordCount());

        dbManager.truncateTable();

    }

    @Test
    public void testDatabaseCanBeReadFrom() {

        dbManager.addEmployeesToDB(list);

        dbManager.getAllEmployees();

        Assertions.assertTrue(dbManager.getRecordCount() == 1);

        dbManager.truncateTable();

    }

    @Test
    public void testRecordCountCanBeRetrievedFromDatabase() {

    }

    @Test
    public void testDatabaseCanBeTruncated() {

        dbManager.truncateTable();

        Assertions.assertEquals(0, dbManager.getRecordCount());

    }

}
