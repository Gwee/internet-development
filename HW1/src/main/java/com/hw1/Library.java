package com.hw1;

import java.io.*;
import java.util.*;

public class Library implements Serializable {
    //private Map<String,List<Book>> books;
    private List<Book> books;
    private List<Rental> rentals;
    private List<Student> students;
    private Context context;
    private File studentFile;
    private File rentalFile;
    private File bookFile;

    public Library(List<Book> books, List<Rental> rentals, List<Student> students){
        this.books = books;
        this.rentals = rentals;
        this.students = students;
        studentFile = new File("students.txt");
        rentalFile = new File("rentals.txt");
        bookFile = new File("books.txt");
        context = new Context();
    }
    private boolean doesBookExist(String bookIsbn){
        for (Book b: books){
            if (b.getBookIsbn().equals(bookIsbn)){
                System.out.println("Book already exists");
                return true;
            }
        }
        return false;
    }
    private boolean doesRentalExistForStudent(Student student){
        for (Rental rental:rentals
             ) {
            if (rental.getStudent().getStudentId().equals(student.getStudentId())){
                return true;
            }
        }
        return false;
    }
    public void addStudent(String firstName, String lastName, String studentId, String studentEmail){
        students.add(new Student(firstName, lastName, studentId, studentEmail));
        context.writeToFile(students,studentFile);
    }
    public void addBook2(String bookIsbn, String bookName, int copies, Book.Category bookCategory){
        if (!doesBookExist(bookIsbn)){
            if (copies <= 3) {
                for (int i = 0; i < copies; i++) {
                    books.add(new Book(bookIsbn,bookName,bookCategory));
            }
            }else{
                System.out.println("Can only add up to 3 copies. Not adding book "+bookName);
        }
        }
        context.writeToFile(books,bookFile);
    }
//    public boolean addBook(String bookIsbn, String bookName, int copies, Book.Category bookCategory) {
//        if (books.containsKey(bookIsbn)){
//            //add new book if it doesn't exist
//            System.out.println("Book exists, not adding anything");
//            return false;
//        }
//        books.put(bookIsbn,new ArrayList<Book>());
//        for (int i=0; i<copies; i++){
//            books.get(bookIsbn).add(new Book(bookIsbn,bookName,bookCategory));
//        }
//        return true;
//    }

    public void rentBook2(String bookIsbn, Student student){
        if (!doesRentalExistForStudent(student)){
            rentals.add(new Rental(student));
        }
            for (Rental rental : rentals
            ) {
                if (rental.getStudent().getStudentId().equals(student.getStudentId())) {
                    Book availableBook = getAvailableBookByIsbn(bookIsbn);
                    if (availableBook != null && rental.getNumOfRentals() <3) {
                        rental.addBook(availableBook);
                        availableBook.rentBook();
                    } else {
                        System.out.println("Cannot rent book since student has too many rentals or book has been rented");
                    }
                }
            }
        context.writeToFile(rentals,rentalFile);
        context.writeToFile(books,bookFile);
    }

//    public boolean rentBook(String bookIsbn, Student student) {
//        if (!rentals.containsKey(student.getStudentId())) {
//            rentals.put(student.getStudentId(), new Rental(student));
//        }
//        if (rentals.get(student.getStudentId()).getNumOfRentals() >= 3) {
//            System.out.println("Cannot rent more than 3 copies");
//            return false;
//        }
//        Book availableBook = getAvailableBook(bookIsbn);
//        if (availableBook != null) {
//            rentals.get(student.getStudentId()).addBook(availableBook);
//            availableBook.rentBook();
//            System.out.println("Book " + bookIsbn + " added for Student " + student.getFirstName());
//            return true;
//        } else {
//            System.out.println("No copies left to rent for book: " + bookIsbn);
//            return false;
//        }
//    }
    private Book getBookById(Book book){
        for (Book b: books){
            if (book.getBookId().equals(b.getBookId())){
                return b;
            }
        }
        return null;
    }
    public void returnBook2(String bookId, Student student){
        Rental rental = getRentalByStudentId(student.getStudentId());
        Book returnBook = rental.getBookById(bookId);
        if (returnBook != null) {
            returnBook.returnBook();
            rental.removeBook(returnBook);
            //todo: fix this. book return not working
            Book b = getBookById(returnBook);
            if (b!=null){
                b.returnBook();
            }
        }
        context.writeToFile(rentals,rentalFile);
    }
//    public boolean returnBook(String bookId, Student student){
//        Rental rental = rentals.get(student.getStudentId());
//        Book returnBook = rental.getBookById(bookId);
//        if (returnBook != null) {
//            rental.removeBook(returnBook);
//            return true;
//        }else{
//            System.out.println("Cannot return this book since it has not been rented by student "+student.getFirstName());
//            return false;
//        }
//    }
    public boolean isStudentLate2(Student student){
        Rental rental = getRentalByStudentId(student.getStudentId());
        if (rental != null) {
            return rental.checkIfStudentLate();
        }else{
            System.out.println("Could not find rental");
            return false;
        }
    }
//    public boolean isStudentLate(Student student){
//        return rentals.get(student.getStudentId()).checkIfStudentLate();
//    }
    private Rental getRentalByStudentId(String studentId){
        for (Rental rental: rentals
             ) {
            if (rental.getStudent().getStudentId().equals(studentId)){
                return rental;
            }
        }
        return null;
    }
    private Book getAvailableBookByIsbn(String bookIsbn){
        for (Book book: books
             ) {
            if (book.getBookIsbn().equals(bookIsbn) && !book.isRented()) {
                return book;
            }
        }
        return null;
    }
//    private Book getAvailableBook(String bookIsbn){
//        List<Book> bookList = books.get(bookIsbn);
//        if (bookList!=null) {
//            for (Book book : bookList) {
//                if (book != null && !book.isRented()) {
//                    return book;
//                }
//            }
//        } else{
//            System.out.println("Book doesn't exist");
//        }
//        return null;
//    }
    public void printAvailableBooksByCategory(Book.Category category){
        System.out.println("Book by category "+category.toString());
        for (Book book : books) {
            if (book.getBookCategory() == category && !book.isRented()) {
                System.out.println("Book Name: " + book.getBookName()
                        + " Book Isbn: " + book.getBookIsbn()
                        + " Book ID: " + book.getBookId());
            }
        }
        }
//    public void printBooksByCategory(Book.Category category){
//        System.out.println("Book by category "+category.toString());
//        for (List<Book> bookList : books.values()) {
//            Book firstBook = bookList.get(0);
//            if (firstBook.getBookCategory() == category) {
//                System.out.println("Book Name: " + firstBook.getBookName()
//                        + " Book Isbn: " + firstBook.getBookIsbn()
//                        + " Book ID: " + firstBook.getBookId());
//            } else{
//                System.out.println("No book with that category found");
//            }
//        }
//    }
//    public void printAllBooks() {
//        for (List<Book> bookList : books.values()) {
//            for (Book b : bookList) {
//                System.out.println("Book Name: " + b.getBookName()
//                        + " Book Isbn: " + b.getBookIsbn()
//                        + " Book ID: " + b.getBookId());
//            }
//        }
//    }
    public void printRentedBooks2(Student student) {
        for (Rental rental : rentals){
            if  (student.getStudentId().equals(rental.getStudent().getStudentId())) {
                for (Book book : rental.getBookList()) {
                    System.out.println("Book Name: " + book.getBookName()
                            + " Book Isbn: " + book.getBookIsbn()
                            + " Book ID: " + book.getBookId()
                            + " Book Rented: " + book.isRented());
                }
            }
        }
    }
    public void printAllStudents(){
        for (Student student:students
             ) {
            System.out.println("Student ID: " + student.getStudentId()
                    +" Student Name: "+student.getFirstName());
        }
    }
    public Student getStudentById(String studentId){
        for (Student student:students
             ) {
            if (student.getStudentId().equals(studentId)){
                return student;
            }
        }
        return  null;
    }
//    public void printRentedBooks(){
//        for (Rental rental : rentals.values()){
//            System.out.println("Rentals for student: "+rental.getStudent().getFirstName());
//            for (Book book : rental.getBookList()){
//                System.out.println("Book Name: " + book.getBookName()
//                        + " Book Isbn: " + book.getBookIsbn()
//                        + " Book ID: " + book.getBookId()
//                        + " Book Rented: "+book.isRented());
//            }
//        }
//    }
    public void removeBook(String bookIsbn) {
        books.remove(bookIsbn);
        context.writeToFile(books,bookFile);
    }

    public List<Book> searchBook(String bookName) {
        List<Book> bookList = new ArrayList<>();
        for (Book book: books
             ) {
            if (bookName.contains(book.getBookName())){
                bookList.add(book);
            }
        }
        return bookList;
    }

    public boolean doesBookIdExist(String bookId) {
        for (Book book: books
             ) {
            if (book.getBookId().equals(bookId)){
                return true;
            }

        }
        return false;
    }
}
