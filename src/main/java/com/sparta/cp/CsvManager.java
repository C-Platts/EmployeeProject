package com.sparta.cp;

import com.sparta.cp.csvproject.reader.CsvFileFilter;
import com.sparta.cp.csvproject.reader.CsvFileReader;

public class CsvManager {

    private final String fileName = "src/main/resources/CustomRecords.csv";
    private CsvFileFilter filter;
    private CsvFileReader reader;

    public void start() {

        reader.readFromFile(fileName, filter);

    }


}
