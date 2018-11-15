package com.hw1;

import java.io.Serializable;
import java.util.*;

public class Library implements Serializable {
    private Map<String,List<Book>> books;
    private Map<String, Rental> rentals;
    private List<Student> students;

    public Library(){
        this.books = new HashMap<>();
        this.rentals = new HashMap<>();
    }
//    public void addBook2(String bookIsbn, String bookName, int copies, Book.Category bookCategory) {
//        for (Book book: books
//             ){
//            if (book.)
//        }
//    }
    public boolean checkIfBookExists(Book book, String bookIsbn){
        return book.getBookIsbn().equals(bookIsbn);
    }
    public boolean addBook(String bookIsbn, String bookName, int copies, Book.Category bookCategory) {
        if (books.containsKey(bookIsbn)){
            //add new book if it doesn't exist
            System.out.println("Book exists, not adding anything");
            return false;
        }
        books.put(bookIsbn,new ArrayList<Book>());
        for (int i=0; i<copies; i++){
            books.get(bookIsbn).add(new Book(bookIsbn,bookName,bookCategory));
        }
        return true;
    }
    public boolean rentBook(String bookIsbn, Student student) {
        if (!rentals.containsKey(student.getStudentId())) {
            rentals.put(student.getStudentId(), new Rental(student));
        }
        if (rentals.get(student.getStudentId()).getNumOfRentals() >= 3) {
            System.out.println("Cannot rent more than 3 copies");
            return false;
        }
        Book availableBook = getAvailableBook(bookIsbn);
        if (availableBook != null) {
            rentals.get(student.getStudentId()).addBook(availableBook);
            availableBook.rentBook();
            System.out.println("Book " + bookIsbn + " added for Student " + student.getFirstName());
            return true;
        } else {
            System.out.println("No copies left to rent for book: " + bookIsbn);
            return false;
        }
    }
    public boolean returnBook(String bookId, Student student){
        Rental rental = rentals.get(student.getStudentId());
        Book returnBook = rental.getBookById(bookId);
        if (returnBook != null) {
            rental.removeBook(returnBook);
            return true;
        }else{
            System.out.println("Cannot return this book since it has not been rented by student "+student.getFirstName());
            return false;
        }
    }
    public boolean isStudentLate(Student student){
        return rentals.get(student.getStudentId()).checkIfStudentLate();
    }
    private Book getAvailableBook(String bookIsbn){
        List<Book> bookList = books.get(bookIsbn);
        if (bookList!=null) {
            for (Book book : bookList) {
                if (book != null && !book.isRented()) {
                    return book;
                }
            }
        } else{
            System.out.println("Book doesn't exist");
        }
        return null;
    }
    public void printBooksByCategory(Book.Category category){
        System.out.println("Book by category "+category.toString());
        for (List<Book> bookList : books.values()) {
            Book firstBook = bookList.get(0);
            if (firstBook.getBookCategory() == category) {
                System.out.println("Book Name: " + firstBook.getBookName()
                        + " Book Isbn: " + firstBook.getBookIsbn()
                        + " Book ID: " + firstBook.getBookId());
            } else{
                System.out.println("No book with that category found");
            }
        }
    }
    public void printAllBooks() {
        for (List<Book> bookList : books.values()) {
            for (Book b : bookList) {
                System.out.println("Book Name: " + b.getBookName()
                        + " Book Isbn: " + b.getBookIsbn()
                        + " Book ID: " + b.getBookId());
            }
        }
    }
    public void printRentedBooks(){
        for (Rental rental : rentals.values()){
            System.out.println("Rentals for student: "+rental.getStudent().getFirstName());
            for (Book book : rental.getBookList()){
                System.out.println("Book Name: " + book.getBookName()
                        + " Book Isbn: " + book.getBookIsbn()
                        + " Book ID: " + book.getBookId()
                        + " Book Rented: "+book.isRented());
            }
        }
    }
    public void removeBook(String bookIsbn) {
        books.remove(bookIsbn);
    }
}
