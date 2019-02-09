/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DB.DB;
import Model.BeanClass;
import Model.Copy;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author amitmarko
 */
@WebServlet(name = "madeBarrow", urlPatterns = {"/madeBarrow"})
public class madeBarrow extends HttpServlet {

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
            String isbn = request.getParameter("isbn");
            int copynum = Integer.parseInt(request.getParameter("copynum"));
            DB db = new DB();
            HttpSession session = request.getSession();
            BeanClass bean = (BeanClass) session.getAttribute("bean");

            db.setAviableCopiesBook(isbn, copynum - 1);
            Copy c = db.getAviableCopy(isbn);
            db.setNotAviableCopy(c.getIsbn(), c.getCopy_number());
            db.setStudentBooks(bean.getStudent().getID() , bean.getStudent().getMyBorrows()+1);
            db.makeNewLoan(bean.getStudent().getID(), isbn, c.getCopy_number(), new Date().toString(), getnewdate().toString());
            bean.getStudent().setMyBorrows(bean.getStudent().getMyBorrows() + 1);
            session.setAttribute("bean", bean);
            RequestDispatcher dis = request.getRequestDispatcher("SuccessfullPage.jsp?succbarrow=");
            dis.forward(request, response);

        }
    }

    private Date getnewdate() {
        Date tmp = new Date();
        if (tmp.getMonth() == 11 && tmp.getDate() > 23) {
            return new Date(tmp.getYear() + 1, 0, 1);
        }
        if (tmp.getDate() > 23) {
            return new Date(tmp.getYear() + 1, tmp.getMonth() + 1, 1);
        }
        Date d = new Date(tmp.getYear(), tmp.getMonth(), tmp.getDate() + 5);
        return d;
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
