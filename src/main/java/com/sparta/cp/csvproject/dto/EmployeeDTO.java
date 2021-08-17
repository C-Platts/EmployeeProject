package com.sparta.cp.csvproject.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDTO {

    HashSet<Employee> employeeSet;
    ArrayList<Employee> duplicateList;

    public EmployeeDTO(ArrayList<Employee> employeeList) {

        employeeSet = new HashSet<>();
        duplicateList = new ArrayList<>();

        for (Employee employee: employeeList) {
            //check for duplicate
            if(!employeeSet.add(employee))
            {
                duplicateList.add(employee);
            }
        }
    }
    //Ms.,Beulah,J,Weeks,F,beula.weeks@aol.com,4/5/1975,2/11/2011,139978

    public void printEmployees() {
        System.out.println(employeeSet.toString());
    }

    public void printDuplicates() {
        System.out.println(duplicateList.toString());
    }

    public void printAmount() {
        System.out.println("Employees: " + employeeSet.size() +
                "\n" + "Duplicates Found: " + duplicateList.size() +
                "\n" + "Total Records: " + (employeeSet.size() + duplicateList.size()));
    }

}
