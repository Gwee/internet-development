package com.hw1;

import java.io.Serializable;

public class Student implements Serializable {
    private String firstName;
    private String lastName;
    private String studentId;
    private String studentEmail;
    private int studentRentals;
    private boolean isStudentLate;
    private int lateRentals;
    public Student(String firstName, String lastName, String studentId, String studentEmail){

        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.studentEmail = studentEmail;
        this.studentRentals = 0;
        this.isStudentLate = false;
        this.lateRentals = 0;
    }

    public String getStudentId() {
        return studentId;
    }
    public void addRental(){
        studentRentals++;
    }

    public void setStudentLate(boolean studentLate) {
        this.isStudentLate = studentLate;
        if (studentLate){
            this.lateRentals++;
        }else {
            this.lateRentals--;
        }

    }

    public int getStudentRentals() {
        return studentRentals;
    }

    public String getFirstName() {
        return firstName;
    }

    public void removeRental() {
        studentRentals--;
    }
}
