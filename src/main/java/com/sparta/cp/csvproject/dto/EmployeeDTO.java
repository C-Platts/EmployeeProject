package com.sparta.cp.csvproject.dto;

import com.sparta.cp.csvproject.exception.InvalidEmployeeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

public class EmployeeDTO {

    private final int id;
    private final String namePrefix;
    private final String firstName;
    private final char middleInitial;
    private final String lastName;
    private final char gender;
    private final String email;
    private final Date dateOfBirth;
    private final Date dateOfJoining;
    private final int salary;
    private final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    private EmployeeVerifier verifier = new EmployeeVerifier();

    public EmployeeDTO(String[] attributes) throws ParseException, InvalidEmployeeException {

        if(verifier.verifyAttributes(attributes)) {
            this.id = Integer.parseInt(attributes[0]);
            this.namePrefix = attributes[1];
            firstName = attributes[2];
            middleInitial = attributes[3].charAt(0);
            lastName = attributes[4];
            gender = attributes[5].charAt(0);
            email = attributes[6];
            dateOfBirth = formatter.parse(attributes[7]);
            dateOfJoining = formatter.parse(attributes[8]);
            salary = Integer.parseInt(attributes[9]);
        } else {
            throw new InvalidEmployeeException();
        }
    }

    public int getId() {
        return id;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public char getMiddleInitial() {
        return middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDTO employeeDTO = (EmployeeDTO) o;

        if(id == employeeDTO.id)
            return true;

        return middleInitial == employeeDTO.middleInitial && salary == employeeDTO.salary && Objects.equals(namePrefix, employeeDTO.namePrefix) && Objects.equals(firstName, employeeDTO.firstName) && Objects.equals(lastName, employeeDTO.lastName) && Objects.equals(gender, employeeDTO.gender) && Objects.equals(email, employeeDTO.email) && Objects.equals(dateOfBirth, employeeDTO.dateOfBirth) && Objects.equals(dateOfJoining, employeeDTO.dateOfJoining) && Objects.equals(formatter, employeeDTO.formatter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namePrefix, firstName, middleInitial, lastName, gender, email, dateOfBirth, dateOfJoining, salary, formatter);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", namePrefix='" + namePrefix + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleInitial=" + middleInitial +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth='" + formatter.format(dateOfBirth) + '\'' +
                ", dateOfJoining='" + formatter.format(dateOfJoining) + '\'' +
                ", salary=" + salary +
                '}';
    }

    private static class EmployeeVerifier {

        private boolean verifyAttributes(String[] attributes) {

            if (
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

        private boolean isPrefixValid(String prefix) {
            return prefix.matches("(Mr|Ms|Mrs|Prof|Dr|Hon).");
        }

        private boolean isNameValid(String name) {
            return name.matches("[A-Za-z]*");
        }

        private boolean isMiddleInitialValid(String initial) {
            return initial.matches("[A-Z]");
        }

        private boolean isGenderSelectionValid(String gender) {
            return gender.matches("[MF]");
        }

        private boolean isNumericalFieldValid(String numerical) {
            return numerical.matches("[0-9]*");
        }

        private boolean isEmailValid(String email) {
            return email.matches("[A-Za-z0-9._%+-]*@[A-Za-z0-9.-]*\\.[a-z]*");
        }
    }
}
