package com.sparta.cp.csvproject.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CsvFileReader {

    public ArrayList<String> readFromCsvFile(String fileName) {

        ArrayList<String> lines = null;

        try {

            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //Skip first line
            if(bufferedReader.readLine() != null) {

               lines  = (ArrayList<String>) bufferedReader.lines()
                       .collect(Collectors.toList());
            }

            bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;

    }


}
