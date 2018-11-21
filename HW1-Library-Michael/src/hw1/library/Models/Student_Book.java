/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1.library.Models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Michael
 */
public class Student_Book  implements Serializable{
    private int studentId;
    private int isbn;
    private Date borrowDate;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Student_Book(int studentId, int isbn, Date borrowDate) {
        this.studentId = studentId;
        this.isbn = isbn;
        this.borrowDate = borrowDate;
    }
    
    
}
