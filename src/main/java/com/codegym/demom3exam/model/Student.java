package com.codegym.demom3exam.model;

import java.time.LocalDate;

public class Student {
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private String address;
    private String phone;
    private String email;
    private int classroom;

    public Student() {
    }

    public Student(String name, String dateOfBirth, String address, String phone, String email, int classroom) {
        this.name = name;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.classroom = classroom;
    }

    public Student(int id, String name, String dateOfBirth, String address, String phone, String email, int classroom) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.classroom = classroom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return String.valueOf(dateOfBirth);
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getClassroom() {
        return classroom;
    }

    public void setClassroom(int classroom) {
        this.classroom = classroom;
    }
}
