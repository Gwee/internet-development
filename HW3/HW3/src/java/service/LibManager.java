package service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import helper.FooStack;
import Models.Book;
import Models.Student;
import Models.Student_Book;
import repos.Context;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Guy
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
        this.students = students != null ? students : new ArrayList<Student>();
        this.books = books != null ? books : new ArrayList<Book>();
        this.students_books = students_books != null ? students_books : new ArrayList<Student_Book>();;

        this.fs = new FooStack();
    }

    public int getLastBookId() {
        int id = 0;
        for (Book book : books) {
            if (book.getid() > id) {
                id = book.getid();
            }
        }
        return id;
    }

    public int getLastStudentId() {
        int id = 0;
        for (Book book : books) {
            if (book.getid() > id) {
                id = book.getid();
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

    public String borrowBook(Student student, String isbn, Context context) {

        //List<Book> copies = findBookByTitle(title);
        List<Book> copies = getFreeBookByIsbn(isbn);
        Book copy = findFeeCopy(copies);

        if (copy == null) {
            return "No copies left to rent!";
        }
        if (0 == copies.size()) {
            return "Book cannot be found";
        }
        if (isOverdueBooks(student)) {
            return "Book cannot be borrowed!You are overdue!";
        }
        if (isLimitReached(student)) {
            return "Book cannot be borrowed! You borrowed too many books!";
        }

        Student_Book sb = new Student_Book(student.getId(), copy.getid(), new Date());
        students_books.add(sb);
        save(context, "Student_Book.txt");
        return "you booked!";
    }

    public List<Book> getStudentsBooks(int id) {
        List<Integer> ids = new ArrayList<Integer>();
        List<Book> result = new ArrayList<>();

        students_books.forEach((sb) -> {
            if (sb.getStudentId() == id) {
                ids.add(sb.getBookId());
            }
        });

        ids.forEach((i) -> {
            books.forEach((b) -> {
                if (i == b.getid()) {
                    result.add(getBooktByID(i));
                }

            });
        });

        return result;
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
                if (sb.getBookId() == copy.getid()) {
                    copiesTemp.remove(copy);
                    if (copiesTemp.size() == 0) {
                        return null;
                    }
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
            if (book.getid() == id) {
                return book;
            }
        }
        return null;
    }

    public List<Book> getFreeBookByIsbn(String isbn) {
        List<Book> freeBooks = getFreeBooks();
        List<Book> result = new ArrayList();

        for (Book book : freeBooks) {
            if (book.getIsbn().equals(isbn)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getFreeBooks() {
        List<Book> freeBooks = new ArrayList(books);

        if (students_books == null) {
            return freeBooks;
        }
        if (students_books.size() == 0) {
            return freeBooks;
        }

        for (Book book : books) {
            for (Student_Book sb : students_books) {
                if (book.getid() == sb.getBookId()) {
                    freeBooks.remove(book);
                }
            }
        }
        return freeBooks;
    }

    public List<Book> getBorrowedBooks() {
        List<Book> result = new ArrayList<Book>();

        if (students_books == null) {
            return result;
        }
        if (students_books.size() == 0) {
            return result;
        }
        for (Student_Book sb : students_books) {
            for (Book book : books) {
                if (sb.getBookId() == book.getid()) {
                    result.add(book);
                }
            }
        }
        return result;
    }

    public Student getStudentByEmail(String email) {

        for (Student student : students) {
            if (student.getEmail().equals(email)) {
                return student;
            }
        }
        return null;
    }

    public String returnBook(int studId, int[] booksId, Context context) {

        List<Student_Book> toRemove = new ArrayList<>();
        for (int i = 0; i < booksId.length; i++) {
            for (Student_Book student_book : students_books) {
                if (student_book.getBookId() == booksId[i]
                        && student_book.getStudentId() == studId) {

                    toRemove.add(student_book);
                }
            }
        }

        students_books.removeAll(toRemove);
        context.writeToFile(students_books, "Student_Book.txt");

        return "Returned, thenks!";
    }
}
