package com.sparta.cp.csvproject.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    public Employee(int id, String namePrefix, String firstName, char middleInitial, String lastName, String gender, String email, String dateOfBirth, String dateOfJoining, int salary) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

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
    public boolean equals(Object obj) {
        if (obj instanceof Employee) {
            return ((Employee) obj).id == id ||
                    (((Employee) obj).firstName.equals(firstName) &&
                    ((Employee) obj).middleInitial == middleInitial &&
                    ((Employee) obj).lastName.equals(lastName) &&
                    ((Employee) obj).gender.equals(gender) &&
                    ((Employee) obj).email.equals(email) &&
                    ((Employee) obj).dateOfBirth.equals(dateOfBirth) &&
                    ((Employee) obj).dateOfJoining.equals(dateOfJoining) &&
                    ((Employee) obj).salary == salary);
        }
        return false;
    }
    @Override
    public int hashCode() {
        return this.id;
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
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", dateOfJoining='" + dateOfJoining + '\'' +
                ", salary=" + salary +
                '}';
    }
}
