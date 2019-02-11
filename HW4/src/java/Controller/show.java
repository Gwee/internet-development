/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DB.*;
import Model.BeanClass;
import Model.*;
import Model.Student;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "show", urlPatterns = {"/show"})
public class show extends HttpServlet {

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

            DB db = new DB();
            HttpSession session = request.getSession();
            BeanClass bean = (BeanClass) session.getAttribute("bean");

            if (request.getParameter("students") != null) {

                List<Student> list = db.getListOfStudent();
                bean.setList(list);
                session.setAttribute("bean", bean);
                RequestDispatcher dis = request.getRequestDispatcher("/showStudents.jsp");
                dis.forward(request, response);

            } else if (request.getParameter("books") != null) {

                List<Book> list = db.getListOfBooks();
                bean.setListAllBooks(list);
                session.setAttribute("bean", bean);
                RequestDispatcher dis = request.getRequestDispatcher("/showbooks.jsp");
                dis.forward(request, response);

            } else if (request.getParameter("loans") != null) {
                List<Loan> list = db.getListOfLons();
                bean.setListAllLoans(list);
                session.setAttribute("bean", bean);
                RequestDispatcher dis = request.getRequestDispatcher("/showLoans.jsp");
                dis.forward(request, response);

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
