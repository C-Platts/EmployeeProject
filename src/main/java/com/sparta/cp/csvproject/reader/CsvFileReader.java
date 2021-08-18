package com.sparta.cp.csvproject.reader;

import com.sparta.cp.csvproject.dto.EmployeeDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CsvFileReader {

    public List<String> readFromFile(String fileName) {

        List<String> lines = null;

        try {

            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            System.out.println("Reading file: " + fileName);

            //Skip first line
            if(bufferedReader.readLine() != null) {

               lines  = (ArrayList<String>) bufferedReader.lines()
                       .collect(Collectors.toList());
            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("File not found");
        }

        return lines;

    }


}
