package com.hw1;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Book implements Serializable {
    public enum Category {
        SCIENCE, MATH, ANTHOLOGY
    }
    private String bookIsbn;
    private String bookId;
    private String bookName;
    private String bookAuthor;
    private Date bookReleaseDate;
    private Date bookRentedDate;
    private Category bookCategory;
    private boolean isRented;
    public Book(String bookIsbn, String bookName){
        this.bookIsbn = bookIsbn;
        this.bookName = bookName;
        this.bookId = Integer.toString((int)(Math.random()*10000));
        this.isRented = false;
        this.bookRentedDate = null;
    }
    public Book(String bookIsbn, String bookName, Category bookCategory){
        this.bookIsbn = bookIsbn;
        this.bookName = bookName;
        this.bookCategory = bookCategory;
        this.bookId = Integer.toString((int)(Math.random()*10000));
        this.isRented = false;
        this.bookRentedDate = null;
    }
    public Book(String bookId, String bookName, String bookIsbn, String bookAuthor, Date bookReleaseDate, Category bookCategory){
        this.bookName = bookName;
        this.bookIsbn = bookIsbn;
        this.bookAuthor = bookAuthor;
        this.bookReleaseDate = bookReleaseDate;
        this.bookCategory = bookCategory;
        this.bookId = Integer.toString((int)(Math.random()*10000));
        this.isRented = false;
        this.bookRentedDate = null;
    }

    public boolean isRented() {
        return isRented;
    }
    public void rentBook(){
        if (!isRented) {
            this.isRented = true;
            this.bookRentedDate = new Date();
        }
    }
    public void returnBook(){
        if (isRented) {
            this.isRented = false;
            this.bookRentedDate = null;
        }
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookId() {
        return bookId;
    }

    public Category getBookCategory() {
        return bookCategory;
    }

    public Date getBookReleaseDate() {
        return bookReleaseDate;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }


    public Date getBookRentedDate() {
        return bookRentedDate;
    }

    @Override
    public String toString() {
        return "Name: "+bookName+ "\nID: "+bookId + "\nISBN: "+bookIsbn;
    }
}
