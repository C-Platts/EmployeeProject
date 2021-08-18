package com.sparta.cp.csvproject.jdbc.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabasePrinter {

    public static void printRecords(ResultSet resultSet) throws SQLException {

        System.out.println("id, namePrefix, firstName, middleInitial, lastName, gender, email, dateOfBirth, dateOfJoining, salary");
        while(resultSet.next()) {
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getString(3));
            System.out.println(resultSet.getString(4));
            System.out.println(resultSet.getString(5));
            System.out.println(resultSet.getString(6));
            System.out.println(resultSet.getString(7));
            System.out.println(resultSet.getDate(8));
            System.out.println(resultSet.getDate(8));
            System.out.println(resultSet.getInt(9));
        }
    }

    public static void printRecordCount(int count) {
        System.out.println(count);
    }

}
