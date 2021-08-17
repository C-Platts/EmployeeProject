package com.sparta.cp.csvproject.dto;

import java.util.Date;

public class Employee {

    private final int id;
    private String namePrefix;
    private String firstName;
    private char middleInitial;
    private String lastName;
    private String gender;
    private String email;
    private String dateOfBirth;
    private String dateOfJoining;
    private int salary;

    public Employee(int id, String namePrefix, String firstName, char middleInitial, String lastName, String gender, String email, String dateOfBirth, String dateOfJoining, int salary) {
        this.id = id;
        this.namePrefix = namePrefix;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public char getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(char middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Employee) {
//            return (((Employee) obj).firstName.equals(firstName) &&
//                    ((Employee) obj).middleInitial == middleInitial &&
//                    ((Employee) obj).lastName.equals(lastName) &&
//                    ((Employee) obj).gender.equals(gender) &&
//                    ((Employee) obj).email.equals(email) &&
//                    ((Employee) obj).dateOfBirth.equals(dateOfBirth) &&
//                    ((Employee) obj).dateOfJoining.equals(dateOfJoining) &&
//                    ((Employee) obj).salary == salary);
            return ((Employee) obj).id == id;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return this.id;
    }
}
