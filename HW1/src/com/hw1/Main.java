package com.hw1;
import java.io.*;
//import java.util.ArrayList;
//import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
//import java.util.Map;
import java.util.regex.Pattern;


public class Main {
//TODO: read and write objects to separate files (Books, Students, Rentals) and then create library with them

    private static Context context = new Context();
    private static File studentFile = new File("students.txt");
    private static File rentalFile = new File("rentals.txt");
    private static File bookFile = new File("books.txt");
    public static void main(String[] args) throws IOException {
        Library library;
        List<Student> students;
        List<Book> books;
        List<Rental> rentals;
        //Context context = new Context();
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));


        //will not overwrite if exists
        studentFile.createNewFile();
        rentalFile.createNewFile();
        bookFile.createNewFile();

        if (studentFile.length()==0){
            students = new ArrayList<>();
        }else{
            students = context.readFromFile(studentFile);
        }
        if (rentalFile.length()==0){
            rentals = new ArrayList<>();
        }else{
            rentals = context.readFromFile(rentalFile);
        }
        if (bookFile.length()==0){
            books = new ArrayList<>();
        }else{
            books = context.readFromFile(bookFile);
        }
        //checks if libFile is empty then creates new library instead of loading existing and adds book
        library = new Library(books,rentals,students);
        //library.addStudent("Guy", "Moyal", "209639", "guy@moyal.me");
        Student selectedStudent = selectStudent(library,read);
        while(true) {
            int input = getMenuInput(read);
            switch (input) {
                case 1:
                    addNewBook(read,library);
                    break;
                case 2:
                    rentBookByCategory(read,library, selectedStudent);
                    break;
                case 3:
                    returnBook(read,library,selectedStudent);
                    break;
                case 4:
                    removeBook(read,library);
                    break;
                case 5:
                    searchBookToRent(read,library,selectedStudent);
                    break;
                case 6:
                    addStudent(read, library);
                    break;
                case 7:
                    System.out.println("to be implemented");
                    break;
                case 8:
                    System.out.println("to be implemented");
                    break;
                case 9:
                    System.exit(0);
                    read.close();
            }
        }
            //book removal
            //System.out.println("Enter ISBN of book to remove: ");
            //String bookIsbnToRemove = read.readLine();
            //library.removeBook(bookIsbnToRemove);
//            library.rentBook("333", s1);
//            library.isStudentLate(s1);
//            library.printAllBooks();
//            library.printRentedBooks();
//            library.printBooksByCategory(Book.Category.MATH);
    }
    private static Student selectStudent(Library library, BufferedReader reader) throws IOException {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Available students:");
        library.printAllStudents();
        System.out.println("Please enter the student ID to use: ");
        String studentId = reader.readLine();
        Student s = library.getStudentById(studentId);
        while(s==null){
            System.out.println("Wrong student ID entered. If no student exists press 1 to add a new student or enter a valid student ID.");
            System.out.println("Available students:");
            library.printAllStudents();
            String input = reader.readLine();
            if (checkIfInt(input) && Integer.parseInt(input )==1){
                addStudent(reader,library);
            }
            s = library.getStudentById(input);
        }
        //reader.close();
        return s;
    }
    private static void searchBookToRent(BufferedReader reader, Library library, Student student)throws IOException{
        System.out.println("Please enter book name to search: ");
        String bookName = reader.readLine();
        List<Book> bookList = library.searchBook(bookName);
        if (bookList.isEmpty()){
            System.out.println("No book found. Returning to menu");
            getMenuInput(reader);
        }
        for (Book book :bookList
             ) {
            if (!book.isRented()) {
                System.out.println(book.toString());
            }
        }
        System.out.println("Enter book ISBN to rent: ");
        String bookIsbn = reader.readLine();
        library.rentBook2(bookIsbn,student);
    }
    private static int getMenuInput(BufferedReader reader) throws IOException {

        printMenu();
        String input = reader.readLine();
        while(!checkIfInt(input)){
            System.out.println("Bad input, please enter a number from 1 to 9");
            printMenu();
            input = reader.readLine();
        }
        return Integer.parseInt(input);

    }
    private static boolean checkIfInt(String s){
        String regex = "[1-9]";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(s).matches();
    }
    private static void printMenu(){
        System.out.println("Please select a command:");
        System.out.println("1. Add a book");
        System.out.println("2. Rent a book");
        System.out.println("3. Return a book");
        System.out.println("4. Remove a book");
        System.out.println("5. Search a book to rent");
        System.out.println("6. Add a student");
        System.out.println("7. Print book categories");
        System.out.println("8. Print all books");
        System.out.println("9. Quit");

    }
    private static void addStudent(BufferedReader reader, Library library) throws IOException {
        System.out.println("Please enter student first name: ");
        String sFirstName = reader.readLine();
        System.out.println("Please enter student last name: ");
        String sLastName = reader.readLine();
        System.out.println("Please enter student ID: ");
        String sId = reader.readLine();
        System.out.println("Please enter student E-mail: ");
        String sEmail = reader.readLine();
        library.addStudent(sFirstName,sLastName,sId,sEmail);
        //context.readFromFile(studentFile);

    }
    private static void rentBookByCategory(BufferedReader reader, Library library, Student student) throws IOException {
        Book.Category category = getBookCategoryFromUser(reader);
        library.printAvailableBooksByCategory(category);
        System.out.println("Please enter book ISBN to rent: ");
        String bookIsbn = reader.readLine();
        library.rentBook2(bookIsbn,student);
    }

    private static void removeBook(BufferedReader reader, Library library) throws IOException {
        System.out.println("Please enter book ISBN to remove: ");
        String bookIsbn = reader.readLine();
        library.removeBook(bookIsbn);
    }
    private static void returnBook(BufferedReader reader, Library library, Student student) throws IOException {
        System.out.println("Showing books for student : "+student.getFirstName());
        library.printRentedBooks2(student);
        System.out.println("Please enter book ID to return: ");
        String bookId = reader.readLine();
        if (!library.doesBookIdExist(bookId)){
            System.out.println("Please enter a valid book ID: ");

        }else {
            library.returnBook2(bookId, student);
        }
    }

    private static void addNewBook(BufferedReader reader, Library library) throws IOException {
        System.out.println("Please enter book");
        System.out.print("Book ISBN: ");
        String bookIsbn = reader.readLine();
        System.out.print("Book Name: ");
        String bookName = reader.readLine();
        System.out.println("Book Category: ");
        Book.Category bookCategory = getBookCategoryFromUser(reader);
        System.out.println("Enter number of copies: ");
        int numOfCopies = Integer.parseInt(reader.readLine());
        library.addBook2(bookIsbn,bookName,numOfCopies, bookCategory);
    }
    private static Book.Category getBookCategoryFromUser(BufferedReader reader) throws IOException {
        Book.Category bookCateogry = null;
        System.out.println("Please pick a number and press enter");
        while(bookCateogry==null) {
            System.out.println("1. Science");
            System.out.println("2. Anthology");
            System.out.println("3. Math");
            switch (reader.readLine()) {
                case "1":
                    bookCateogry = Book.Category.SCIENCE;
                    break;
                case "2":
                    bookCateogry = Book.Category.ANTHOLOGY;
                    break;
                case "3":
                    bookCateogry = Book.Category.MATH;
                    break;
            }
            if (bookCateogry == null){
                System.out.println("ERROR: Please select a category by entering a number");
            }
        }
        return bookCateogry;
    }
//    private static Library loadLibrary (File file) throws IOException, ClassNotFoundException {
//        Library library;
//        FileInputStream fi = new FileInputStream(file);
//        ObjectInputStream oi = new ObjectInputStream(fi);
//        library = (Library) oi.readObject();
//        fi.close();
//        oi.close();
//        return library;
//
//    }
//    private static void writeLibrary(Library library, File file) throws IOException {
//        FileOutputStream fo = new FileOutputStream(file);
//        ObjectOutputStream oj = new ObjectOutputStream(fo);
//        oj.writeObject(library);
//        fo.close();
//        oj.close();
//    }
}