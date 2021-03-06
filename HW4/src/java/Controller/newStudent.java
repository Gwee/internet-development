/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DB.*;
import Model.Student;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Guy Moyal
 */
@WebServlet(name = "newStudent", urlPatterns = {"/newStudent"})
public class newStudent extends HttpServlet {

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

            String first = request.getParameter("firstname");
            String second = request.getParameter("secondname");
            String id = request.getParameter("id");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            int maxloans = Integer.parseInt(request.getParameter("maxloans"));
            String tmp = request.getParameter("per");
            boolean status = true;

            if (tmp.equals("0")) {
                status = false;
            }

            if ("".equals(first) || "".equals(second) || "".equals(id) || "".equals(email) || "".equals(password)) {
                response.sendRedirect("ErrorPage.jsp?firstname=" + first + "&secondname=" + second + "&id=" + id + "&email=" + email);
            }

            if (!"".equals(first) && !"".equals(second) && !"".equals(id) && !"".equals(email)) {
                DB db = new DB();
                if (db.addStudent(new Student(id, first, second, email, maxloans, 0, 50, 0, status, password))) {
                    String nextPage = "/SuccessfullPage.jsp?addnewstudent=";
                    RequestDispatcher dis = request.getRequestDispatcher(nextPage);
                    dis.forward(request, response);
                } else {
                    response.sendRedirect("ErrorPage.jsp?studentexist=");
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
