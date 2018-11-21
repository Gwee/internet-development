package com.hw1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Rental implements Serializable {
    private Student student;
    private List<Book> bookList;
    private boolean isLate;
    private int numOfRentals;

    public Rental (Student student){
        this.student = student;
        bookList = new ArrayList<>();
        this.isLate = false;
    }

    public int getNumOfRentals() {
        return numOfRentals;
    }

    public void addBook(Book book){
        if (book != null && numOfRentals<=3) {
            bookList.add(book);
            numOfRentals++;
        }
    }

    public Student getStudent() {
        return student;
    }

    public void removeBook(Book book){
        if (book != null){
            bookList.remove(book);
            numOfRentals--;
        }
    }
    public Book getBookById(String bookId){
        for (Book book: bookList
             ) {
            if (book.getBookId().equals(bookId)){
                return book;
            }
        }
        return null;
    }
    public List<Book> getBookList(){
        return bookList;
    }
    public boolean checkIfStudentLate(){
        int counter = 0;
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        Date lateDate = new Date(System.currentTimeMillis() - (7*DAY_IN_MS));
        for (Book book: bookList
             ) {
            if (book.getBookRentedDate().before(lateDate)){
                counter++;
            }
            }
        if (counter >= 3){
            this.isLate = true;
            return true;
        }else{
            return false;
        }
    }
}
