/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import repos.Context;
import Models.Book;
import helper.FooStack;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Guy
 */
@WebServlet(name = "srvAddBook", urlPatterns = {"/srvAddBook"})
public class srvAddBook extends HttpServlet {

    Context context;
    FooStack fstack = new FooStack();

    List<Book> books;
    List<Book> newBooks;
    Book newBook;

    public srvAddBook() throws IOException {
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
        books = context.readFromFile("Books.txt");

        int copies = Integer.parseInt(request.getParameter("copies"));

        newBook = new Book();

        newBook.setIsbn(request.getParameter("isbn"));
        newBook.setTitle(request.getParameter("bookTitle"));
        newBook.setAuthorName(request.getParameter("author"));
        newBook.setPublishDate(request.getParameter("date"));

        int id = getLastBookId() + 1;
        newBooks = fstack.bookCopies(copies, newBook, id);

        if (books == null) {
            books = newBooks;//first time
        } else {
            books.addAll(newBooks);
        }
        context.writeToFile(books, "Books.txt");
        response.sendRedirect("~/../index.html");

    }

    public int getLastBookId() {
        int id = 0;
        if (books == null) {
            return id;
        }

        for (Book book : books) {
            if (book.getid() > id) {
                id = book.getid();
            }
        }
        return id;
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
