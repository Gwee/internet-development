package Model;


import java.util.*;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Loan implements Serializable {

    private String id;
    private String isbn;
    private String bookname;
    private int copynumber;
    private String category;
    private String From;
    private String Untill;
    private static final long serialVersionUID = 1L;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    
    
    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getCopynumber() {
        return copynumber;
    }

    public void setCopynumber(int copynumber) {
        this.copynumber = copynumber;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String From) {
        this.From = From;
    }

    public String getUntill() {
        return Untill;
    }

    public void setUntill(String Untill) {
        this.Untill = Untill;
    }

  

}

