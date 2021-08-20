package com.sparta.cp.csvproject.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Objects;

public class EmployeeDTO {

    private final int id;
    private final String namePrefix;
    private final String firstName;
    private final char middleInitial;
    private final String lastName;
    private final char gender;
    private final String email;
    private final LocalDate dateOfBirth;
    private final LocalDate dateOfJoining;
    private final int salary;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

    public EmployeeDTO(String[] attributes) {

        this.id = Integer.parseInt(attributes[0]);
        this.namePrefix = attributes[1];
        this.firstName = attributes[2];
        this.middleInitial = attributes[3].charAt(0);
        this.lastName = attributes[4];
        this.gender = attributes[5].charAt(0);
        this.email = attributes[6];
        this.dateOfBirth = LocalDate.parse(attributes[7], formatter);
        this.dateOfJoining = LocalDate.parse(attributes[8], formatter);
        this.salary = Integer.parseInt(attributes[9]);
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

    public char getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getDateOfJoining() {
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

        return id == employeeDTO.id;

    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
}
