package com.sparta.cp.csvproject;

import java.util.ArrayList;

public class CsvPrinter {

    public static <T> void printNumberOfInvalidRecords(int originalSize, ArrayList<T> list) {

        System.out.println("Invalid records found: " + (originalSize - list.size()));

    }

}
