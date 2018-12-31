package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import repos.Context;
import helper.HTMLHelper;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Student;
import helper.webHelper;
import java.io.IOException;
import javax.servlet.http.Cookie;

/**
 *
 * @author Guy
 */
@WebServlet(name = "srvLogin", urlPatterns = {"/srvLogin"})
public class srvLogin extends HttpServlet {

    Context context;
    HTMLHelper htmlHelper = new HTMLHelper();
    webHelper whelper = new webHelper();
    List<Student> students;
    Cookie cookie;
    Cookie cname;

    public srvLogin() throws IOException {
        this.context = new Context();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        init();

        response.setContentType("text/html;charset=UTF-8");

        if (whelper.getCookie(request, "email") != null) {
            
            cname = whelper.getCookie(request, "name");
            cookie = whelper.getCookie(request, "email");
            
            htmlHelper.createMenu(response, cname.getValue());
            return;
        } else {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            cookie = whelper.getCookie(request, email);
            cname = whelper.getCookie(request, "name");

            //whelper.removeCookie(response, "username");
            for (Student student : students) {
                if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                    cookie = new Cookie("email", email);
                    cname = new Cookie("name", student.getFirstName());

                    whelper.addCookie(response, "email", email);
                    whelper.addCookie(response, cname);
                    break;
                }
            }

            //Wrong credentials
            if (cookie == null) {
                htmlHelper.badCredentials(response);
                return;
            } else {
                htmlHelper.createMenu(response, cname.getValue());
                return;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public void init() {
        students = context.readFromFile("Students.txt");
        if (students == null) {
            students = new ArrayList<>();
        }
    }
}
