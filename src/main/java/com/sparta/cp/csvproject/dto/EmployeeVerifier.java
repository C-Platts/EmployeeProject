package com.sparta.cp.csvproject.dto;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;

public class EmployeeVerifier {

    HashSet<EmployeeDTO> employeeDTOSet;
    ArrayList<EmployeeDTO> duplicateList;

    public boolean verifyAttributes(String[] attributes) {

        if(
            this.isNumericalFieldValid(attributes[0]) &&
            this.isPrefixValid(attributes[1]) &&
            this.isNameValid(attributes[2]) &&
            this.isMiddleInitialValid(attributes[3]) &&
            this.isNameValid(attributes[4]) &&
            this.isGenderSelectionValid(attributes[5]) &&
            this.isEmailValid(attributes[6]) &&
            //DateofBirth
            //DateOfJoining
            this.isNumericalFieldValid(attributes[9])
        ) {
            return true;
        }
        return false;
    }

    public EmployeeDTO buildEmployee(String[] attributes) {
        //Each employee has 10 attributes

        if(!verifyAttributes(attributes))
            return null;

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


    //Ms.,Beulah,J,Weeks,F,beula.weeks@aol.com,4/5/1975,2/11/2011,139978

    public void printEmployees() {
        System.out.println(employeeDTOSet.toString());
    }

    public void printDuplicates() {
        System.out.println(duplicateList.toString());
    }

    public void printAmount() {
        System.out.println("Employees: " + employeeDTOSet.size() +
                "\n" + "Duplicates Found: " + duplicateList.size() +
                "\n" + "Total Records: " + (employeeDTOSet.size() + duplicateList.size()));
    }

    public boolean isPrefixValid(String prefix) {
        return prefix.matches("(Mr|Ms|Mrs|Prof|Dr|Hon|Drs).");
    }
    public boolean isNameValid(String name) {
        return name.matches("[A-Za-z]*");
    }
    public boolean isMiddleInitialValid(String initial) {
        return initial.matches("[A-Z]");
    }
    public boolean isGenderSelectionValid(String gender) {
        return gender.matches("[MF]");
    }
    public boolean isNumericalFieldValid(String numerical) {
        return numerical.matches("[0-9]*");
    }
    public boolean isEmailValid(String email) {
        return email.matches("[A-Za-z0-9._%+-]*@[A-Za-z0-9.-]*\\.[a-z]*");
    }

}
