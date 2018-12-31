package helper;

import Models.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Guy
 */
public class HTMLHelper {

    public PrintWriter badCredentials(HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        /* TODO output your page here. You may use following sample code. */
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet srvLogin</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>" + "Wrong credentials, try again:" + "</h1>");
        out.println("<a href=\"~/../index.html\">Log in<a>");
        out.println("</body>");
        out.println("</html>");

        return out;
    }

    public PrintWriter showMessage(HttpServletResponse response, String message) throws IOException {

        PrintWriter out = response.getWriter();
        /* TODO output your page here. You may use following sample code. */
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet srvLogin</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<a href=\"/HW3/srvLogin\">Go to menu<a>");
        out.println("</body>");
        out.println("</html>");

        return out;
    }
    
        public PrintWriter showMessageStudentAddError(HttpServletResponse response, String message) throws IOException {

        PrintWriter out = response.getWriter();
        /* TODO output your page here. You may use following sample code. */
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet srvLogin</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<a href=\"/HW3/\">Go to menu<a>");
        out.println("</body>");
        out.println("</html>");

        return out;
    }

    public PrintWriter createMenu(HttpServletResponse response, String name) throws IOException {
        PrintWriter out = response.getWriter();
        startPartial(out, name);
        endPartial(out);
        return out;
    }

    public PrintWriter searchBook(HttpServletResponse response, String name) throws IOException {

        PrintWriter out = response.getWriter();
        startPartial(out, name);
        out.println("<form action=\"srvSearchResults\" method=\"post\">");
        out.println("<input type=\"text\" name=\"txtSearch\"  placeholder=\"Enter isbn\" />");
        out.println("<input type=\"submit\" name=\"btnSearch\"  value=\"Borrow\"/>");
        out.println("</form>");
        endPartial(out);
        return out;
    }

    public PrintWriter searchBookByText(HttpServletResponse response, String name) throws IOException {

        PrintWriter out = response.getWriter();
        startPartial(out, name);
        out.println("<form action=\"srvMenuAction\" method=\"post\">");
        out.println("<input type=\"text\" name=\"txtSearch\" placeholder=\"Enter text\" />");
        out.println("<input type=\"submit\" name=\"btnSearchByText\" value=\"Search\"/>");
        out.println("</form>");
        endPartial(out);
        return out;
    }

    public PrintWriter showBorrowed(HttpServletResponse response, String name, List<Book> books) throws IOException {

        PrintWriter out = response.getWriter();
        startPartial(out, name);
        out.println("<form action=\"srvReturnBooks\" method=\"post\">");
        out.println("<table id = \"main_section\" cellspacing=\"1\" bgcolor=\"red\" > ");
        out.println("<tr>");
        out.println("<th>Title</th>");
        out.println("<th>ISBN</th>");
        out.println("<th>Author</th>");
        out.println("<th>Publishing Date</th>");
        out.println("<th>Check to return</th>");
        out.println("</tr>");
        out.println("<tbody>");

        for (int i = 0; i < books.size(); i++) {
            out.println("<tr>");
            Book b = books.get(i);
            out.println("<td>" + b.getTitle() + "</td>");
            out.println("<td>" + b.getIsbn() + "</td>");
            out.println("<td>" + b.getAuthorName() + "</td>");
            out.println("<td>" + b.getPublishDate() + "</td>");
            out.println("<td><input type=\"checkbox\" name=\"checkbox\" value=\"" + b.getid() + "\"></td>");

            //            if (i > 0 && (i + 1) % 4 == 0) {
            //                out.println("<td><input type=\"checkbox\" name=\"checkbox\"></td>");
            //                out.println("</tr><tr>");
            //            }
            out.println("</tr>");
        }

        out.println("</tbody>");
        out.println("</table>");
        out.println("<input type=\"submit\" name=\"btnReturn\"  value=\"Return Book\"/>");
        out.println("</form>");
        endPartial(out);
        return out;
    }

    public PrintWriter showBorrowedAndFree(HttpServletResponse response, String name, List<Book> freeBooks, List<Book> borrowedBooks) throws IOException {

        PrintWriter out = response.getWriter();
        startPartial(out, name);
        out.println("<table id = \"main_section\" cellspacing=\"1\" bgcolor=\"silver\" > ");
        out.println("<tr>");
        out.println("<th>Title</th>");
        out.println("<th>ISBN</th>");
        out.println("<th>Author</th>");
        out.println("<th>Publishing Date</th>");
        out.println("<th>Status</th>");
        out.println("</tr>");
        out.println("<tbody>");

        for (int i = 0; i < freeBooks.size(); i++) {
            out.println("<form action=\"srvSearchResults\" method=\"post\">");
            out.println("<tr>");
            Book b = freeBooks.get(i);
            out.println("<td><input type='submit' name=\"ddd\" data-isbn=\""+b.getIsbn()+"\" value=\""+b.getTitle()+"\" style=\"padding:0; border:none; background:none; cursor:pointer\" ></td>");
            out.println("<td>" + b.getIsbn() + "</td>");
            out.println("<td>" + b.getAuthorName() + "</td>");
            out.println("<td>" + b.getPublishDate() + "</td>");
            out.println("<td>" + "Free to borrow!" + "</td>");
            out.println("</tr>");
            out.println("</form>");
        }

        for (int i = 0; i < borrowedBooks.size(); i++) {
            out.println("<tr>");
            Book b = borrowedBooks.get(i);
            out.println("<td>" + b.getTitle() + "</td>");
            out.println("<td>" + b.getIsbn() + "</td>");
            out.println("<td>" + b.getAuthorName() + "</td>");
            out.println("<td>" + b.getPublishDate() + "</td>");
            out.println("<td>" + "Can't be borrowed!" + "</td>");
            out.println("</tr>");
        }

        out.println("</tbody>");
        out.println("</table>");
        endPartial(out);
        return out;
    }

    private PrintWriter startPartial(PrintWriter out, String name) throws IOException {

        /* TODO output your page here. You may use following sample code. */
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<link rel='stylesheet' type='text/css' href='/HW3/CSS/Style.css' />");
        out.println("<link rel='stylesheet' type='text/css' href='/HW3/CSS/MenuStyle.css' />");
        out.println("</style>");  // terminate style
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"navbar\">");
        out.println("<form action=\"srvMenuAction\" method=\"post\">");
        out.println("<input type=\"submit\" name=\"btnBorrowBook\"  value=\"Borrow Book\"/>");
        out.println("<input type=\"submit\" name=\"btnReturnBooks\"  value=\"Return Books\"/>");
        out.println("<input type=\"submit\" name=\"btnSearchBooks\"  value=\"Search Books\"/>");
        out.println("<div class=\"logArea\">");
        out.println("<label>" + "Hello," + name + "</label>");
        out.println("<bold>" + "|" + "</bold>");
        out.println("<a href='/HW3/srvLogout' />Log out</a>");
        out.println("</div>");
        out.println("</form>");
        out.println("</div>");

        return out;
    }

    private PrintWriter endPartial(PrintWriter out) throws IOException {
        out.println("</body>");
        out.println("</html>");
        return out;
    }

}
