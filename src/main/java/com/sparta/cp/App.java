package com.sparta.cp;

import com.sparta.cp.csvproject.reader.CsvFileReader;

public class App
{
    public static void main( String[] args ) {

        CsvFileReader.readFromFile("src/main/resources/CustomRecords.csv");


    }

}
