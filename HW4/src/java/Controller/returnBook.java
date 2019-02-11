/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DB.DB;
import Model.BeanClass;
import Model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.time.Period;
import java.util.concurrent.TimeUnit;
import static jdk.nashorn.internal.objects.NativeString.substring;

/**
 *
 * @author Guy Moyal
 */
@WebServlet(name = "returnBook", urlPatterns = {"/returnBook"})
public class returnBook extends HttpServlet {

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

            HttpSession session = request.getSession();
            BeanClass bean = (BeanClass) session.getAttribute("bean");
            if (bean.getStudent().getFname().equals("guest")) {
                RequestDispatcher dis = request.getRequestDispatcher("newLoan.jsp");
                dis.forward(request, response);

            } else {
                String condition = request.getParameter("condition");
                DB db = new DB();

                if (condition == null) {
                    List<Loan> list = db.getListOfLonsByID(bean.getStudent().getID());
                    bean.setListAllLoans(list);
                    session.setAttribute("bean", bean);
                    RequestDispatcher dis = request.getRequestDispatcher("returnBookTable.jsp");
                    dis.forward(request, response);
                } else {

                    String isbn = bean.getBookname();
                    int copynumber = bean.getCopynumber();

                    int con = Integer.parseInt(request.getParameter("condition"));
                    Book b = db.getBook(isbn);
                    if (con == 3) {
                        db.removeCopy(isbn, copynumber);
                        db.updateBookCopys(isbn, b.getNumber_of_copys() - 1);
                    } else {
                        db.updateBookAviablesCopys(isbn, b.getAvailabe_Copys() + 1);
                        db.updateCopyStatus(isbn, copynumber);
                    }

                     db.setStudentBooks(bean.getStudent().getID() , bean.getStudent().getMyBorrows()-1);
                    Loan loan = db.getBorrow(bean.getStudent().getID(), isbn, copynumber);
                    db.deleteBorrow(bean.getStudent().getID(), isbn, copynumber);

                    Date date = null;
                    String tmp = loan.getUntill().substring(0, 10);

                    SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d");
                    String dateInString = loan.getUntill();
                    try {
                        date = formatter.parse(dateInString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    date.setYear(new Date().getYear());

                    long diff = date.getTime() - new Date().getTime();
                    int diffDays = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                    String nextPage = "SuccessfullPage.jsp?returnBooks=";

                    if (con != 0) {
                        nextPage = "SuccessfullPage.jsp?con=" + con * 5;
                    }
                    long x = 0;
                    if (diffDays < 0) {
                        nextPage = "SuccessfullPage.jsp?late=" + diffDays * -1;
                        x = diffDays * -1;

                    }
                    if (con != 0 && diffDays < 0) {
                        nextPage = "SuccessfullPage.jsp?con=" + con * 5 + "&late=" + diffDays * -1;
                        x = diffDays * -1;
                    }
                    int Fine = (int) x + 5 * con;
                    int newMyFine = bean.getStudent().getMyFine() + Fine;
                    bean.getStudent().setMyFine(newMyFine);

                    db.payFine(bean.getStudent().getID(), newMyFine);
                    session.setAttribute("bean", bean);

                    RequestDispatcher dis = request.getRequestDispatcher(nextPage);
                    dis.forward(request, response);
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
