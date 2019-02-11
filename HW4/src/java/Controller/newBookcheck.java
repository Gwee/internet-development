/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DB.DB;
import Model.BeanClass;
import Model.Book;
import Model.Copy;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Guy Moyal
 */
@WebServlet(name = "newBookcheck", urlPatterns = {"/newBookcheck"})
public class newBookcheck extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String bookname = request.getParameter("bookname");
            String isbn = request.getParameter("isbn");
            String author = request.getParameter("author");
            String cat = request.getParameter("cat");
            int relaseyear = Integer.parseInt(request.getParameter("relaseyear"));
            int copysnumber = Integer.parseInt(request.getParameter("copysnumber"));

            DB db = new DB();
            HttpSession session = request.getSession();
            BeanClass bean = (BeanClass) session.getAttribute("bean");
            String nextPage = "ErrorPage.jsp?Bookexist=";

            if (!cat.equals("other")) {

                Book book = new Book(isbn, bookname, author, cat, relaseyear, copysnumber);
                if (db.addBook(book)) {
                    nextPage = "/SuccessfullPage.jsp?addnewBook=";
                    for (int i = 1; i <= copysnumber; i++) {
                        Copy copy = new Copy(isbn, i, false);
                        db.addCopy(copy);
                    }
                }
                RequestDispatcher dis = request.getRequestDispatcher(nextPage);
                dis.forward(request, response);
            } else {
                String othercat = request.getParameter("othercat");
                if (othercat == null || othercat.equals("")) {
                    RequestDispatcher dis = request.getRequestDispatcher("ErrorPage.jsp?DontEnterCat=");
                    dis.forward(request, response);
                } else {
                    othercat = othercat.toLowerCase().trim();
                    boolean flag = false;
                    for (String s : db.getSetOfCat()) {
                        if (s.equals(othercat)) {
                            flag = true;
                        }
                    }

                    if (flag) {
                        RequestDispatcher dis = request.getRequestDispatcher("ErrorPage.jsp?CatExist=");
                        dis.forward(request, response);
                    } else {
                        db.addCategory(othercat);
                        Book book = new Book(isbn, bookname, author, othercat, relaseyear, copysnumber);
                        if (db.addBook(book)) {
                            nextPage = "/SuccessfullPage.jsp?addnewBook=";
                            for (int i = 1; i <= copysnumber; i++) {
                                Copy copy = new Copy(isbn, i, false);
                                db.addCopy(copy);
                            }
                        }
                        RequestDispatcher dis = request.getRequestDispatcher(nextPage);
                        dis.forward(request, response);
                    }

                }
            }

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

}
