/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repos;

import repos.Context;
import Models.Book;
import Models.Student;
import Models.Student_Book;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guy
 */
public class dbRecords {

    private List<Student> students;
    private List<Book> books;
    private Context context;
    private String path;

    public dbRecords(Context context, String path) throws IOException {
        this.context = context;
        this.path = path;
    }

    public void creatFiles() throws FileNotFoundException, IOException {

        Path newFolderPath = Paths.get(path);
        Files.createDirectories(newFolderPath);

        File f = new File(path + "Students.txt");
        f.createNewFile(); // if file already exists will do nothing 
        FileOutputStream oFile = new FileOutputStream(f, false);

        f = new File(path + "Books.txt");
        f.createNewFile(); // if file already exists will do nothing 
        oFile = new FileOutputStream(f, false);

        f = new File(path + "Students_Books.txt");
        f.createNewFile(); // if file already exists will do nothing 
        oFile = new FileOutputStream(f, false);
    }

    public void setStudents() {

        students = new ArrayList<>();
        students.add(new Student("jon@gmail.com", "Jon", "Jackson", 1, "123456"));
        students.add(new Student("tom@gmail.com", "Tom", "Snow", 2, "123456"));
        students.add(new Student("Matilda@gmail.com", "Matilda", "Hong", 3, "123456"));
        students.add(new Student("Merry@gmail.com", "Merry", "Bush", 4, "123456"));
        context.writeToFile(students, "Students.txt");
    }

    public void setBooks() {

        books = new ArrayList<>();
        books.add(new Book("isbn1", 1, "comedy", "title1", "Alexander Duma", "2009-12-01"));
        books.add(new Book("isbn2", 2, "advanture", "title2", "Victor Gugo", "1955-10-15"));
        books.add(new Book("isbn3", 3, "drama", "title3", "Derek Traver", "2001-01-11"));
        books.add(new Book("isbn4", 4, "comedy", "title4", "Melisa Schmidt", "1990-05-10"));
        books.add(new Book("isbn4", 5, "comedy", "title5", "Melisa Schmidt", "1990-05-10"));
        context.writeToFile(books, "Books.txt");
    }
}
