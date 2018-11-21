/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1.library;

import helper.FooStack;
import hw1.library.Models.Book;
import hw1.library.Models.Student;
import hw1.library.Models.Student_Book;
import hw1.library.repos.Context;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Michael
 */
public class LibManager {

    private List<Book> books;
    private List<Student> students;
    private List<Student_Book> students_books;
    FooStack fs;

    public void setStudents_books(List<Student_Book> students_books) {
        this.students_books = students_books;
    }

    public List<Student_Book> getStudents_books() {
        return students_books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setBook(Book book) {
        this.books.add(book);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public LibManager() {
        books = new ArrayList<Book>();
        students = new ArrayList<Student>();
        students_books = new ArrayList<Student_Book>();
    }

    public LibManager(List<Book> books, List<Student> students, List<Student_Book> students_books) {
        this.students = students;
        this.books = books;
        this.students_books = students_books;

        this.fs = new FooStack();
    }

    public int getLastBookId() {
        int id = 0;
        for (Book book : books) {
            if (book.getIsbn() > id) {
                id = book.getIsbn();
            }
        }
        return id;
    }

    public int getLastStudentId() {
        int id = 0;
        for (Book book : books) {
            if (book.getIsbn() > id) {
                id = book.getIsbn();
            }
        }
        return id;
    }

    public void deleteBookByTitle(String title) {
        List<Book> temp = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle() == title) {
                temp.add(book);
            }
        }
        books.removeAll(temp);
    }

    public String borrowBook(Student student, String title, Context context) {

        List<Book> copies = findBookByTitle(title);
        Book copy = findFeeCopy(copies);

        if (0 == copies.size()) {
            return "Book not found";
        }
        if (copy == null) {
            return "All copies is borrowed!";
        }
        if (isOverdueBooks(student)) {
            return "Book cannot be borrowed!You have overdue!";
        }
        if (isLimitReached(student)) {
            return "Book cannot be borrowed!You borrowed too much books!";
        }

        Student_Book sb = new Student_Book(student.getId(), copy.getIsbn(), new Date());
        students_books.add(sb);
        save(context, "Student_Book.txt");
        return "you booked!";
    }

    private Boolean isOverdueBooks(Student student) {
        Date currDate = new Date();
        if (students_books.isEmpty()) {
            return false;
        }

        for (Student_Book sb : students_books) {
            if (sb.getStudentId() == student.getId()) {
                if (fs.diffDates(sb.getBorrowDate(), currDate) > 7) {
                    return true;
                }
            }
        }
        return false;
    }

    private Boolean isLimitReached(Student student) {
        int counter = 0;
        if (students_books.size() == 0) {
            return false;
        }

        for (Student_Book sb : students_books) {
            if (sb.getStudentId() == student.getId()) {
                counter++;
                if (counter == 7) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<Book> findBookByTitle(String title) {
        List<Book> temp = new ArrayList<>();

        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                temp.add(book);
            }
        }
        return temp;
    }

    private Book findFeeCopy(List<Book> copies) {
        List<Book> copiesTemp = new ArrayList(copies);
        if (copiesTemp.size() == 0) {
            return null;
        }
        if (students_books == null) {
            return copiesTemp.get(0);
        }

        for (Student_Book sb : students_books) {
            for (Book copy : copiesTemp) {
                if (sb.getIsbn() == copy.getIsbn()) {
                    copiesTemp.remove(copy);
                    if(copiesTemp.size()==0) return null;
                }
            }
        }
        if (copiesTemp.size() != 0) {
            return copies.get(0);
        } else {
            return null;
        }
    }

    private void save(Context context, String fileName) {
        context.writeToFile(students_books, fileName);
        students_books = context.readFromFile(fileName);
    }

    public Student getStudentByID(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public Book getBooktByID(int id) {
        for (Book book : books) {
            if (book.getIsbn() == id) {
                return book;
            }
        }
        return null;
    }
}
