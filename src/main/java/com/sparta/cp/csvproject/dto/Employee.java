package com.sparta.cp.csvproject.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class Employee {

    private final int id;
    private String namePrefix;
    private String firstName;
    private char middleInitial;
    private String lastName;
    private String gender;
    private String email;
    private Date dateOfBirth;
    private Date dateOfJoining;
    private int salary;
    private final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    public Employee(int id, String namePrefix, String firstName, char middleInitial, String lastName, String gender, String email, String dateOfBirth, String dateOfJoining, int salary) throws ParseException {


        this.id = id;
        this.namePrefix = namePrefix;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dateOfBirth = formatter.parse(dateOfBirth);
        this.dateOfJoining = formatter.parse(dateOfJoining);
        this.salary = salary;
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
        Employee employee = (Employee) o;

        if(id == employee.id)
            return true;

        return middleInitial == employee.middleInitial && salary == employee.salary && Objects.equals(namePrefix, employee.namePrefix) && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(gender, employee.gender) && Objects.equals(email, employee.email) && Objects.equals(dateOfBirth, employee.dateOfBirth) && Objects.equals(dateOfJoining, employee.dateOfJoining) && Objects.equals(formatter, employee.formatter);
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
}
