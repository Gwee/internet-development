/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.HashSet;
import java.util.List;

/**
 *
 * @author amitmarko
 */
public class BeanClass {
    
     private List<Student> list ;
     private HashSet<String> catlist;
     private Student student;
     private List<Book> listOfBooksByCategory;
     private List<Book> listAllBooks;
     private List<Loan> listAllLoans;
     private String bookname;
     private int copynumber;

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public int getCopynumber() {
        return copynumber;
    }

    public void setCopynumber(int copynumber) {
        this.copynumber = copynumber;
    }
     
     
     

    public List<Loan> getListAllLoans() {
        return listAllLoans;
    }

    public void setListAllLoans(List<Loan> listAllLoans) {
        this.listAllLoans = listAllLoans;
    }
     
     
     

    public List<Book> getListAllBooks() {
        return listAllBooks;
    }

    public void setListAllBooks(List<Book> listAllBooks) {
        this.listAllBooks = listAllBooks;
    }
     
     
     
     

    public List<Book> getListOfBooksByCategory() {
        return listOfBooksByCategory;
    }

    public void setListOfBooksByCategory(List<Book> listOfBooksByCategory) {
        this.listOfBooksByCategory = listOfBooksByCategory;
    }
     
     
    
    
     
     
    public HashSet<String> getCatlist() {
        return catlist;
    }

    public void setCatlist(HashSet<String> catlist) {
        this.catlist = catlist;
    }

     
     
     
     
     
     
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
     
     
     

     
        
        

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    
    
}
