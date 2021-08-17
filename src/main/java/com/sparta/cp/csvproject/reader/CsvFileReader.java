package com.sparta.cp.csvproject.reader;

import com.sparta.cp.csvproject.dto.EmployeeDTO;
import com.sparta.cp.csvproject.dto.EmployeeVerifier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CsvFileReader {
    public void readFromFile(String fileName, CsvFileFilter filter) {

        EmployeeVerifier employeeVerifier;

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
                        .map(CsvFileReader::buildEmployee)
                        .filter(Objects::nonNull)
                        .distinct()
                        .collect(Collectors.toList());

                for (EmployeeDTO e: employeeDTOList) {
                    System.out.println(e);
                }
                System.out.println(employeeDTOList.size());

            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("File not found");
        }



        //DTO filters out duplicate employees
        if (employeeDTOList != null) {
            employeeVerifier = new EmployeeVerifier((ArrayList<EmployeeDTO>) employeeDTOList);
            employeeVerifier.printAmount();
        }


    }

    private static EmployeeDTO buildEmployee(String[] attributes) {
        //Each employee has 10 attributes
        try {
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    //ID                          //Name Prefix        //First Name   //Middle initial        //last name
                    Integer.parseInt(attributes[0]), attributes[+1], attributes[2], attributes[+3].charAt(0), attributes[+4],
                    //Gender         //email        //DOB          //date of join          //salary
                    attributes[+5], attributes[+6], attributes[+7], attributes[+8], Integer.parseInt(attributes[+9]));

            return employeeDTO;

        } catch(NumberFormatException | ParseException n) {
            n.printStackTrace();
            return null;
        }

    }
}
