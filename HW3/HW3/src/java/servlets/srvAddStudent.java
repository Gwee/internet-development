package servlets;

import repos.Context;
import helper.HTMLHelper;
import Models.Student;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.LibManager;

/**
 *
 * @author Guy
 */
@WebServlet(urlPatterns = {"/srvAddStudent"})
public class srvAddStudent extends HttpServlet {

    Context context;
    HTMLHelper htmlHelper = new HTMLHelper();
    List<Student> students;

    public srvAddStudent() throws IOException {
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

        init();

        response.setContentType("text/html;charset=UTF-8");

        int id = Integer.parseInt(request.getParameter("sid"));
        String email = request.getParameter("email");
        String fname = request.getParameter("fName");
        String lname = request.getParameter("lName");
        String password = request.getParameter("password");

        for (Student student : students) {
            if (student.getId() == id || student.getEmail().equals(email)) {
                htmlHelper.showMessageStudentAddError(response, "ID or Email already exists!");
                return;
            }
        }

        students.add(new Student(email, fname, lname, id, password));
        context.writeToFile(students, "Students.txt");
        response.sendRedirect("~/../index.html");
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

    public void init() {

        students = context.readFromFile("Students.txt");

        if (students == null) {
            students = new ArrayList<>();

        }
    }
}
