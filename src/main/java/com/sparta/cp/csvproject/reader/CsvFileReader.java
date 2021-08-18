package com.sparta.cp.csvproject.reader;

import com.sparta.cp.csvproject.dto.EmployeeDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CsvFileReader {

    public List<EmployeeDTO> readFromFile(String fileName) {

        List<EmployeeDTO> employeeDTOList = null;

        try {

            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            System.out.println("Reading file: " + fileName);

            //Skip first line
            if(bufferedReader.readLine() != null) {

                employeeDTOList = bufferedReader.lines()
                        .map(l -> l.split(","))
                        .filter(a -> a.length == 10)
                        .map(a -> new EmployeeDTO(a))
                        .filter(Objects::nonNull)
                        .distinct()
                        .collect(Collectors.toList());

                for (EmployeeDTO e: employeeDTOList) {
                    System.out.println(e);
                }
                System.out.println("Valid Records: " + employeeDTOList.size());
//                System.out.println("Duplicates found: ");
//                System.out.println("Invalid records found: ");

            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("File not found");
        }

        return employeeDTOList;

    }


}
