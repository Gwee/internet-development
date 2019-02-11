/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Model.Book;
import Model.Copy;
import Model.Loan;
import Model.Student;
import Util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guy Moyal
 */
public class DB {

    private Connection connection = null;

    public DB() {
        connection = DBUtil.getConnection();
    }

    public Student getStudent(String id, String password) {
        PreparedStatement pStatement;
        Student student = null;
        try {
            pStatement = connection.prepareStatement("SELECT * FROM STUDENT where id=?");
            pStatement.setString(1, id);
            ResultSet rs = pStatement.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setID(rs.getString("ID"));
                student.setFname(rs.getString("fname"));
                student.setSname(rs.getString("sname"));
                student.setEmail(rs.getString("email"));
                student.setMaxBorrows(rs.getInt("MaxBorrows"));
                student.setMyBorrows(rs.getInt("MyBorrows"));
                student.setMaxFine(rs.getInt("maxfine"));
                student.setMyFine(rs.getInt("myfine"));
                student.setPremission(rs.getBoolean("Premission"));

                String tmp = rs.getString("password");
                if (tmp.equals(password)) {
                    student.setPassword(password);
                } else {
                    student.setPassword(null);
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return student;
    }

    public boolean addStudent(Student student) {
        PreparedStatement pStatement;
        try {
            pStatement = connection.prepareStatement("INSERT INTO ROOT.STUDENT (ID, FNAME, SNAME, EMAIL, MAXBORROWS, MYBORROWS, MAXFINE, MYFINE, PREMISSION, PASSWORD) \n"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)");

            pStatement.setString(1, student.getID());
            pStatement.setString(2, student.getFname());
            pStatement.setString(3, student.getSname());
            pStatement.setString(4, student.getEmail());
            pStatement.setInt(5, student.getMaxBorrows());
            pStatement.setInt(6, 0);
            pStatement.setInt(7, 50);
            pStatement.setInt(8, 0);
            pStatement.setBoolean(9, student.isPremission());
            pStatement.setString(10, student.getPassword());

            pStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public List<Student> getListOfStudent() {
        PreparedStatement pStatement;
        Student student;
        List<Student> List = new ArrayList<>();
        try {
            pStatement = connection.prepareStatement("SELECT * FROM STUDENT");
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                student = new Student();
                student.setID(rs.getString("ID"));
                student.setFname(rs.getString("fname"));
                student.setSname(rs.getString("sname"));
                student.setEmail(rs.getString("email"));
                student.setMaxBorrows(rs.getInt("MaxBorrows"));
                student.setMyBorrows(rs.getInt("MyBorrows"));
                student.setMaxFine(rs.getInt("maxfine"));
                student.setMyFine(rs.getInt("myfine"));
                student.setPremission(rs.getBoolean("Premission"));
                student.setPassword(rs.getString("password"));
                List.add(student);

            }
        } catch (SQLException ex) {
        }
        return List;
    }

    public HashSet<String> getSetOfCat() {
        PreparedStatement pStatement;
        HashSet<String> List = new HashSet<String>();
        try {
            pStatement = connection.prepareStatement("SELECT * FROM CATEGORY");
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                List.add(rs.getString("catname"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return List;

    }

    public boolean addBook(Book book) {
        PreparedStatement pStatement;
        try {
            pStatement = connection.prepareStatement("INSERT INTO ROOT.BOOK (ISBN, BOOKNAME, AUTHOR, RELEASE, TOTALCOPYS, AVAILABLECOPYS, CATNAME) \n"
                    + "VALUES (?,?,?,?,?,?,?)");
            pStatement.setString(1, book.getID());
            pStatement.setString(2, book.getBook_Name());
            pStatement.setString(3, book.getAuthor());
            pStatement.setInt(4, book.getDate());
            pStatement.setInt(5, book.getNumber_of_copys());
            pStatement.setInt(6, book.getNumber_of_copys());
            pStatement.setString(7, book.getCat());
            pStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public boolean addCopy(Copy copy) {
        PreparedStatement pStatement;
        try {
            pStatement = connection.prepareStatement("INSERT INTO ROOT.COPY (ISBN, COPYNUMBER, STATUS) VALUES (?,?,?)");
            pStatement.setString(1, copy.getIsbn());
            pStatement.setInt(2, copy.getCopy_number());
            pStatement.setBoolean(3, copy.isIsTookean());
            pStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addCategory(String othercat) {
        PreparedStatement pStatement;
        try {
            pStatement = connection.prepareStatement("INSERT INTO ROOT.CATEGORY (CATNAME) VALUES (?)");
            pStatement.setString(1, othercat);
            pStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Book getBook(String isbn) {
        PreparedStatement pStatement;
        Book book = null;
        try {
            pStatement = connection.prepareStatement("SELECT * FROM BOOK where isbn=?");
            pStatement.setString(1, isbn);
            ResultSet rs = pStatement.executeQuery();

            if (rs.next()) {
                book = new Book();
                book.setID(isbn);
                book.setBook_Name(rs.getString("BOOKNAME"));
                book.setAuthor(rs.getString("Author"));
                book.setDate(rs.getInt("RELEASE"));
                book.setNumber_of_copys(rs.getInt("TOTALCOPYS"));
                book.setAvailabe_Copys(rs.getInt("AVAILABLECOPYS"));
                book.setCategory(rs.getString("CATNAME"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return book;
    }

    public boolean checkAllCopysFree(String isbn) {

        PreparedStatement pStatement;
        try {
            pStatement = connection.prepareStatement("SELECT * FROM Copy where isbn=? and status = ?");
            pStatement.setString(1, isbn);
            pStatement.setBoolean(2, true);
            ResultSet rs = pStatement.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;

    }

    public boolean removeBook(String isbn) {
        PreparedStatement pStatement;
        try {
            pStatement = connection.prepareStatement("DELETE FROM ROOT.BOOK WHERE ISBN = ?");
            pStatement.setString(1, isbn);
            pStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public boolean removeAllCopysOfBook(String isbn) {
        PreparedStatement pStatement;
        try {
            pStatement = connection.prepareStatement("DELETE FROM Copy WHERE ISBN = ?");
            pStatement.setString(1, isbn);
            pStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public boolean payFine(String id, int newMyFine) {
        PreparedStatement pStatement;
        try {
            pStatement = connection.prepareStatement("UPDATE ROOT.STUDENT SET MYFINE = ? WHERE ID = ?");
            pStatement.setInt(1, newMyFine);
            pStatement.setString(2, id);
            pStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Book> getListOfBooksByCategory(String cat) {
        PreparedStatement pStatement;
        Book book;
        List<Book> List = new ArrayList<Book>();
        try {
            pStatement = connection.prepareStatement("SELECT * FROM Book where CATNAME = ? ");
            pStatement.setString(1, cat);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                book = new Book();
                book.setID(rs.getString("isbn"));
                book.setBook_Name(rs.getString("BOOKNAME"));
                book.setAuthor(rs.getString("Author"));
                book.setDate(rs.getInt("RELEASE"));
                book.setNumber_of_copys(rs.getInt("TOTALCOPYS"));
                book.setAvailabe_Copys(rs.getInt("AVAILABLECOPYS"));
                book.setCategory(rs.getString("CATNAME"));
                List.add(book);
            }
        } catch (SQLException ex) {
        }
        return List;
    }

    public List<Book> getListOfBooks() {
        PreparedStatement pStatement;
        Book book;
        List<Book> List = new ArrayList<Book>();
        try {
            pStatement = connection.prepareStatement("SELECT * FROM Book  ");
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                book = new Book();
                book.setID(rs.getString("isbn"));
                book.setBook_Name(rs.getString("BOOKNAME"));
                book.setAuthor(rs.getString("Author"));
                book.setDate(rs.getInt("RELEASE"));
                book.setNumber_of_copys(rs.getInt("TOTALCOPYS"));
                book.setAvailabe_Copys(rs.getInt("AVAILABLECOPYS"));
                book.setCategory(rs.getString("CATNAME"));
                List.add(book);
            }
        } catch (SQLException ex) {
        }
        return List;

    }

    public boolean setAviableCopiesBook(String isbn, int Copynum) {
        PreparedStatement pStatement;
        try {
            pStatement = connection.prepareStatement("UPDATE ROOT.Book SET AVAILABLECOPYS = ? WHERE isbn = ?");
            pStatement.setInt(1, Copynum);
            pStatement.setString(2, isbn);
            pStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public Copy getAviableCopy(String isbn) {
        PreparedStatement pStatement;
        Copy copy = null;
        try {
            pStatement = connection.prepareStatement("SELECT * FROM Copy where isbn=? and STATUS=?");
            pStatement.setString(1, isbn);
            pStatement.setBoolean(2, false);
            ResultSet rs = pStatement.executeQuery();
            if (rs.next()) {
                copy = new Copy();
                copy.setIsbn(isbn);
                copy.setCopy_number(rs.getInt("copynumber"));
                copy.setIsTookean(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return copy;
    }

    public boolean setNotAviableCopy(String isbn, int copy_number) {
        PreparedStatement pStatement;
        try {
            pStatement = connection.prepareStatement("UPDATE ROOT.Copy SET status = ? WHERE isbn = ? and copynumber = ?");
            pStatement.setBoolean(1, true);
            pStatement.setString(2, isbn);
            pStatement.setInt(3, copy_number);

            pStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public boolean makeNewLoan(String id, String bn, int copy_number, String fromDate, String toDate) {

        PreparedStatement pStatement;
        try {
            pStatement = connection.prepareStatement("INSERT INTO ROOT.BORROW (ID, ISBN, COPYNUMBER, FROMDATE, TODATE) VALUES (?, ?, ?, ?, ?)");
            pStatement.setString(1, id);
            pStatement.setString(2, bn);
            pStatement.setInt(3, copy_number);
            pStatement.setString(4, fromDate);
            pStatement.setString(5, toDate);
            pStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public List<Loan> getListOfLons() {
        PreparedStatement pStatement;
        Loan loan;
        List<Loan> List = new ArrayList<Loan>();
        try {
            pStatement = connection.prepareStatement("SELECT * FROM BOOK JOIN BORROW ON BOOK.ISBN = BORROW.ISBN order by todate");
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                loan = new Loan();
                loan.setBookname(rs.getString("bookname"));
                loan.setIsbn(rs.getString("isbn"));
                loan.setId(rs.getString("id"));
                loan.setCategory(rs.getString("catname"));
                loan.setCopynumber(rs.getInt("copynumber"));
                loan.setFrom(rs.getString("fromdate"));
                loan.setUntill(rs.getString("todate"));
                List.add(loan);
            }
        } catch (SQLException ex) {
        }
        return List;
    }

    public boolean setStudentBooks(String id, int i) {
        PreparedStatement pStatement;
        try {
            pStatement = connection.prepareStatement("UPDATE ROOT.STUDENT SET MYBORROWS = ? WHERE ID = ?");
            pStatement.setInt(1, i);
            pStatement.setString(2, id);
            pStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public List<Loan> getListOfLonsByID(String id) {
        PreparedStatement pStatement;
        Loan loan;
        List<Loan> List = new ArrayList<Loan>();
        try {
            pStatement = connection.prepareStatement("SELECT * FROM BOOK JOIN BORROW ON BOOK.ISBN = BORROW.ISBN  where id = ? order by fromdate");
            pStatement.setString(1, id);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                loan = new Loan();
                loan.setBookname(rs.getString("bookname"));
                loan.setIsbn(rs.getString("isbn"));
                loan.setId(rs.getString("id"));
                loan.setCategory(rs.getString("catname"));
                loan.setCopynumber(rs.getInt("copynumber"));
                loan.setFrom(rs.getString("fromdate"));
                loan.setUntill(rs.getString("todate"));
                List.add(loan);
            }
        } catch (SQLException ex) {
        }
        return List;
    }

    public boolean updateBookCopys(String bn, int i) {
        PreparedStatement pStatement;
        try {
            pStatement = connection.prepareStatement("UPDATE ROOT.BOOK SET TOTALCOPYS = ? WHERE ISBN = ? ");
            pStatement.setInt(1, i);
            pStatement.setString(2, bn);
            pStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeCopy(String bn, int copynumber) {
        PreparedStatement pStatement;
        try {
            pStatement = connection.prepareStatement("DELETE FROM ROOT.Copy WHERE ISBN = ? and COPYNUMBER = ?");
            pStatement.setString(1, bn);
            pStatement.setInt(2, copynumber);
            pStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean updateBookAviablesCopys(String bn, int availabe_Copys) {
        PreparedStatement pStatement;
        try {
            pStatement = connection.prepareStatement("UPDATE ROOT.BOOK SET AVAILABLECOPYS = ? WHERE ISBN = ? ");
            pStatement.setInt(1, availabe_Copys);
            pStatement.setString(2, bn);
            pStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteBorrow(String id, String bn, int copynumber) {
        PreparedStatement pStatement;
        try {
            pStatement = connection.prepareStatement("DELETE FROM ROOT.BORROW WHERE ID = ? AND ISBN = ? AND COPYNUMBER = ?");
            pStatement.setString(1, id);
            pStatement.setString(2, bn);
            pStatement.setInt(3, copynumber);
            pStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Loan getBorrow(String id, String bn, int copynumber) {
        PreparedStatement pStatement;
        Loan loan = null;
        try {
            pStatement = connection.prepareStatement("SELECT * FROM ROOT.BORROW where id = ? and isbn= ? and COPYNUMBER= ?");
            pStatement.setString(1, id);
            pStatement.setString(2, bn);
            pStatement.setInt(3, copynumber);
            ResultSet rs = pStatement.executeQuery();
            if (rs.next()) {
                loan = new Loan();
                loan.setIsbn(rs.getString("isbn"));
                loan.setId(rs.getString("id"));
                loan.setCopynumber(rs.getInt("copynumber"));
                loan.setFrom(rs.getString("fromdate"));
                loan.setUntill(rs.getString("todate"));

            }
        } catch (SQLException ex) {
        }
        return loan;
    }

    public boolean updateCopyStatus(String isbn, int copynumber) {
        PreparedStatement pStatement;
        try {
            pStatement = connection.prepareStatement("UPDATE ROOT.Copy SET status = ? WHERE isbn = ? and copynumber = ?");
            pStatement.setBoolean(1, false);
            pStatement.setString(2, isbn);
            pStatement.setInt(3, copynumber);
            pStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Book> getListOfBooksByDers(String description) {

        PreparedStatement pStatement;
        Book book;
        List<Book> List = new ArrayList<Book>();
        try {
            pStatement = connection.prepareStatement("SELECT * FROM Book  where bookname like ? ");
            pStatement.setString(1, description);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                book = new Book();
                book.setID(rs.getString("isbn"));
                book.setBook_Name(rs.getString("BOOKNAME"));
                book.setAuthor(rs.getString("Author"));
                book.setDate(rs.getInt("RELEASE"));
                book.setNumber_of_copys(rs.getInt("TOTALCOPYS"));
                book.setAvailabe_Copys(rs.getInt("AVAILABLECOPYS"));
                book.setCategory(rs.getString("CATNAME"));
                List.add(book);
            }
        } catch (SQLException ex) {
        }
        return List;

    }

}
