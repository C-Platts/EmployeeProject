package com.sparta.cp.csvproject.dto;

import java.util.ArrayList;
import java.util.HashSet;

public class EmployeeVerifier {

    HashSet<EmployeeDTO> employeeDTOSet;
    ArrayList<EmployeeDTO> duplicateList;

    public EmployeeVerifier(ArrayList<EmployeeDTO> employeeDTOList) {

        employeeDTOSet = new HashSet<>();
        duplicateList = new ArrayList<>();

        for (EmployeeDTO employeeDTO : employeeDTOList) {
            //check for duplicate
            if(!employeeDTOSet.add(employeeDTO))
            {
                duplicateList.add(employeeDTO);
            }
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

    public boolean validateName(String name) {
        return name.matches("[A-Za-z]*");
    }
    public boolean validateGender(String gender) {
        return gender.matches("[MF]");
    }
    public boolean validateNumerical(String numerical) {
        return numerical.matches("[0-9]*");
    }

}
