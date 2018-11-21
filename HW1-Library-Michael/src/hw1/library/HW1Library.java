/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1.library;

import hw1.library.Models.Book;
import hw1.library.Models.Student;
import java.util.List;
import java.util.Scanner;
import helper.FooStack;
import hw1.library.Models.Student_Book;
import hw1.library.repos.Context;
import java.util.ArrayList;

/**
 *
 * @author Michael
 */
public class HW1Library {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Context context = new Context();
        FooStack fstack = new FooStack();
        LibManager manager;

        List<Book> books;
        List<Book> newBooks;
        Book newBook;
        List<Student> students;
        Student student;
        List<Student_Book> students_books;
        
        books = context.readFromFile("Books.txt");
        students = context.readFromFile("Students.txt");
        students_books = context.readFromFile("Student_Book.txt");
        
        if(books == null) books = new ArrayList<>();
        if(students == null) students = new ArrayList<>();
        if(students_books == null) students_books = new ArrayList<>();
        
        manager = new LibManager(books, students, students_books);
        int cmd = 0;

        System.out.println("Add book press - 1");
        System.out.println("Delete book press - 2");
        System.out.println("Add student press - 3");
        System.out.println("To borrow a book press - 4");

        System.out.println("Delete all students - 5");
        System.out.println("Delete all books- 6");

        System.out.println("Print all books- 7");
        System.out.println("Print all students- 8");
        System.out.println("Print all borrowed books - 9");
        System.out.println("Exit press - 10");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("**********************************");
            System.out.println("To continue press number from menu:");
            cmd = scanner.nextInt();
            if (cmd == 1) {
                newBook = new Book();
                System.out.println("Book title:");
                newBook.setTitle(scanner.next());
                System.out.println("Add author name:");
                newBook.setAuthorName(scanner.next());
                System.out.println("Add numer of copies:");
                int copies = scanner.nextInt();
                int isbn = manager.getLastBookId() + 1;
                newBooks = fstack.bookCopies(copies, newBook, isbn);
                
                books = context.readFromFile("Books.txt");
                if (books == null) {
                    books = newBooks;//first time
                } else {
                    books.addAll(newBooks);
                }

                context.writeToFile(books, "Books.txt");
                manager.setBooks(books);
                System.out.println("Book added!");
            }

            if (cmd == 2) {
                System.out.println("Enter book title to delete:");
                String title = (scanner.next());
                manager.deleteBookByTitle(title);
            }

            if (cmd == 3) {
                System.out.println("Enter first name:");
                String fname = scanner.next();
                System.out.println("Enter last name:");
                String lname = scanner.next();
                System.out.println("Enter email:");
                String email = scanner.next();
                int id = 0;

                do {
                    System.out.println("Enter valid ID number:");
                    id = scanner.nextInt();
                } while (!fstack.isIDNumberValid(id, manager.getStudents()));

                students = context.readFromFile("Students.txt");
                
                if (students == null) {
                    students = new ArrayList<>();
                    students.add(new Student(email, fname, lname, id));//first time
                } else {
                    students.add(new Student(email, fname, lname, id));
                }
                
                context.writeToFile(students, "Students.txt");
                students = context.readFromFile("Students.txt");
                manager.setStudents(students);
                System.out.println("Student added!");
            }

            if (cmd == 4) {
                System.out.println("Enter yourn ID:");

                int id = scanner.nextInt();
                student = manager.getStudentByID(id);
 
                if (student == null) {
                    System.out.println("Wrong ID!");
                     System.exit(0);
                } else {
                    System.out.println("Enter book name you want to borrow:");
                }
                String bname = scanner.next();
                String responseDesc = manager.borrowBook(student, bname, context); 
                System.out.println(responseDesc);
            }

            if (cmd == 5) {
                String res = context.deleteAll("Students.txt");
                String res2 = context.deleteAll("Student_Book.txt");
                manager.setStudents(new ArrayList<Student>());
                manager.setStudents_books(new ArrayList<Student_Book>());
                System.out.println("Students-" + res);
            }
            
            if (cmd == 6) {
                String res = context.deleteAll("Books.txt"); 
                String res2 = context.deleteAll("Student_Book.txt");
                manager.setBooks(new ArrayList<Book>());
                manager.setStudents_books(new ArrayList<Student_Book>());               
                System.out.println("Books-" + res);
            }

            if (cmd == 7) {
                printAllBooks(manager);
            }
            if (cmd == 8) {
                printAllStudents(manager);
            }
            if (cmd == 9) {
                printBorrowedBooks(manager);
            }
            if (cmd == 10) {
                System.exit(0);
            }
        }
    }

    private static void printAllBooks(LibManager manager) {
        List<Book> books = manager.getBooks();      
        if(books==null) {
            System.out.println("No records found!");
            return;                    
        }
        books.forEach((book) -> {
            System.out.println("Title: " + book.getTitle() + "," +
                    "Author: " + book.getAuthorName() + "," +
                    book.getIsbn());
        });
    }

    private static void printAllStudents(LibManager manager) {
        List<Student> students = manager.getStudents();
        if(students==null) {
            System.out.println("No records found!");
            return;                  
        }
        for (Student student : students) {
            System.out.println("first name: " + student.getFirstName() + ","
                    + "last name: " + student.getLastName() + ","
                    + "email: " + student.getEmail() + ","
                    + "ID: " + student.getId());
        }
    }
    
    private static void printBorrowedBooks(LibManager manager){
       
        manager.getStudents_books().forEach((sb) -> {
            Student s = manager.getStudentByID(sb.getStudentId());
            Book b = manager.getBooktByID(sb.getIsbn());
            
            System.out.println("First name: " + s.getFirstName() + "," +
                    "Last name: " + s.getLastName() + "," +
                            "StudentId: " + s.getId()+ "," +
                                    "Book name: " + b.getTitle() + "," +
                                            "BookId: " + b.getIsbn() + "," +
                                                "Date borrowed: " + sb.getBorrowDate());
        });
    }
}
