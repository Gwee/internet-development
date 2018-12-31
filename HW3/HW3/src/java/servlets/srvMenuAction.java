/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import repos.Context;
import Models.Book;
import Models.Student;
import Models.Student_Book;
import helper.FooStack;
import helper.HTMLHelper;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.webHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import service.LibManager;

/**
 *
 * @author Guy
 */
@WebServlet(name = "srvMenuAction", urlPatterns = {"/srvMenuAction"})
public class srvMenuAction extends HttpServlet {

    Context context;
    HTMLHelper htmlHelper = new HTMLHelper();
    webHelper whelper = new webHelper();

    FooStack fstack = new FooStack();

    List<Book> books;
    List<Book> newBooks;
    Book newBook;
    List<Student> students;
    Student student;
    List<Student_Book> students_books;

    LibManager manager;

    public srvMenuAction() throws IOException {
        this.context = new Context();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        if (request.getParameter("btnBorrowBook") != null) {
            String searchtxt = request.getParameter("btnSearchResult");
            serch(request, response);
        }
        if (request.getParameter("btnReturnBooks") != null) {
            borrowed(request, response);
        }
        if (request.getParameter("btnSearchBooks") != null) {
            String searchtxt = request.getParameter("btnSearch");
            searchBookByText(request, response, searchtxt);
        }
        if (request.getParameter("btnSearchByText") != null) {
            String searchtxt = request.getParameter("txtSearch");
            serchByTextResult(request, response, searchtxt);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void serch(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        Cookie cname = whelper.getCookie(request, "name");
        htmlHelper.searchBook(response, cname.getValue());
    }

    private void searchBookByText(HttpServletRequest request,
            HttpServletResponse response,
            String searchtxt) throws IOException {

        Cookie cname = whelper.getCookie(request, "name");
        htmlHelper.searchBookByText(response, cname.getValue());
    }

    private void serchByTextResult(HttpServletRequest request,
            HttpServletResponse response, String text) throws IOException {
        text = text.toLowerCase();
        
        Cookie cname = whelper.getCookie(request, "name");
        Cookie email = whelper.getCookie(request, "email");

        List<Book> books = context.readFromFile("Books.txt");
        List<Student> students = context.readFromFile("Students.txt");
        List<Student_Book> students_books = context.readFromFile("Student_Book.txt");
        manager = new LibManager(books, students, students_books);

        List<Book> freeBooksToPrint = new ArrayList<>();
        List<Book> borrowedBooksToPrint = new ArrayList<>();
        
        
        
        List<Book> freeBooks = manager.getFreeBooks();
        List<Book> borrowedBooks = manager.getBorrowedBooks();

        for (Book freeBook : freeBooks) {
            if (freeBook.getTitle().toLowerCase().contains(text)
                    || freeBook.getAuthorName().toLowerCase().contains(text)) {
                freeBooksToPrint.add(freeBook);
            }
        }

        for (Book borrowedBook : borrowedBooks) {
            if (borrowedBook.getTitle().toLowerCase().contains(text)
                    || borrowedBook.getAuthorName().toLowerCase().contains(text)) {
                borrowedBooksToPrint.add(borrowedBook);
            }
        }

        htmlHelper.showBorrowedAndFree(response, cname.getValue(), freeBooksToPrint, borrowedBooksToPrint);
    }

    private void borrowed(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie cname = whelper.getCookie(request, "name");
        Cookie email = whelper.getCookie(request, "email");

        List<Book> books = context.readFromFile("Books.txt");
        List<Student> students = context.readFromFile("Students.txt");
        List<Student_Book> students_books = context.readFromFile("Student_Book.txt");
        manager = new LibManager(books, students, students_books);
        Student student = manager.getStudentByEmail(email.getValue());

        List<Book> borrowed = manager.getStudentsBooks(student.getId());
        htmlHelper.showBorrowed(response, cname.getValue(), borrowed);
    }

    private void ret(HttpServletRequest request, HttpServletResponse response) {

        List<Book> books = context.readFromFile("Books.txt");
        List<Student> students = context.readFromFile("Students.txt");
        List<Student_Book> students_books = context.readFromFile("Student_Book.txt");
        manager = new LibManager(books, students, students_books);

    }

}
