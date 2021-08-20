package com.sparta.cp.csvproject.csv;

import java.util.ArrayList;

public class CsvPrinter {

    public static <T> void printNumberOfInvalidRecords(int originalSize, ArrayList<T> list) {
        System.out.println("Invalid records found: " + (originalSize - list.size()) + '\n');
    }

    public static <T> void printNumberOfRecords(ArrayList <T> list) {
        System.out.println("Number of records: " + list.size() + '\n');
    }

    public static void printMessageReadingFromCsvFile(String fileName) {
        System.out.println("Reading from file :" + fileName);
    }

    public static void printMessageFilteringByRecordLength() {
        System.out.println("Removing records of invalid length:");
    }

    public static void printTaskRemovingDuplicates() {
        System.out.println("Removing duplicate records:");
    }

    public static <T> void printRemainingRecordsCount(ArrayList<T> list) {
        System.out.println("Records Remaining: " + list.size());
    }

}
