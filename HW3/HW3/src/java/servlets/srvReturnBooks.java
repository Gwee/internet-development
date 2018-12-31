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
import helper.HTMLHelper;
import helper.webHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.LibManager;

/**
 *
 * @author Guy
 */
@WebServlet(name = "srvReturnBooks", urlPatterns = {"/srvReturnBooks"})
public class srvReturnBooks extends HttpServlet {

    LibManager manager;
    Context context;
    HTMLHelper htmlHelper;
    webHelper whelper = new webHelper();

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
        htmlHelper = new HTMLHelper();
        context = new Context();
        String[] checked = request.getParameterValues("checkbox");
        int[] booksId = Arrays.stream(checked).mapToInt(Integer::parseInt).toArray();
        List<Integer> borrowedBooks = new ArrayList<Integer>();
        

        Cookie email = whelper.getCookie(request, "email");
        List<Book> books = context.readFromFile("Books.txt");
        List<Student> students = context.readFromFile("Students.txt");
        List<Student_Book> students_books = context.readFromFile("Student_Book.txt");

        manager = new LibManager(books, students, students_books);
        Student student = manager.getStudentByEmail(email.getValue());
        
        String massege = manager.returnBook(student.getId(),booksId, context); 
        htmlHelper.showMessage(response, massege);
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

}
