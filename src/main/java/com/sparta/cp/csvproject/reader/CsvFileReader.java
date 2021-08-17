package com.sparta.cp.csvproject.reader;

import com.sparta.cp.csvproject.dto.Employee;
import com.sparta.cp.csvproject.dto.EmployeeDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CsvFileReader {
    public static void readFromFile(String fileName) {

        String[] attributes;

        EmployeeDTO employeeDTO;

        List<Employee> employeeList = null;

        try {

            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            System.out.println("Reading file: " + fileName);

            //Skip first line
            if(bufferedReader.readLine() != null) {

                employeeList = bufferedReader.lines()
                        .map(l -> l.split(","))
                        .filter(a -> a.length == 10)
                        .map(CsvFileReader::buildEmployee)
                        .filter(Objects::nonNull)
                        .distinct()
                        .collect(Collectors.toList());

                for (Employee e:employeeList) {
                    System.out.println(e);
                }
                System.out.println(employeeList.size());

//                //Read file line by line
//                for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
//
//                    //Split line into (hopefully) 10 attributes separated by commas
//                    attributes = line.split(",");
//
//                    //Build employee from attributes and add to list
//                    employeeList.add(buildEmployee(attributes));
//                }

            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("File not found");
        }



        //DTO filters out duplicate employees
        if (employeeList != null) {
            employeeDTO = new EmployeeDTO((ArrayList<Employee>) employeeList);
            employeeDTO.printAmount();
        }


    }

    private static Employee buildEmployee(String[] attributes) {
        //Each employee has 10 attributes
        try {
            Employee employee = new Employee(
                    //ID                          //Name Prefix        //First Name   //Middle initial        //last name
                    Integer.parseInt(attributes[0]), attributes[+1], attributes[2], attributes[+3].charAt(0), attributes[+4],
                    //Gender         //email        //DOB          //date of join          //salary
                    attributes[+5], attributes[+6], attributes[+7], attributes[+8], Integer.parseInt(attributes[+9]));

            return employee;

        } catch(NumberFormatException | ParseException n) {
            n.printStackTrace();
            return null;
        }

    }
}
