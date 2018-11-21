/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1.library.Models;

import java.io.Serializable;

/**
 *
 * @author Michael
 */
public class Book implements Serializable{
   private int isbn;
   //private int  catId;
   private String category;
   private String title;
   private String authorName;

    public int getIsbn() {
        return isbn;
    }

    public String getCatId() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }
    
    public Book() {}
    
    public Book(int isbn, String category, String title, String authorName) {
        this.isbn = isbn;
        this.category = category;
        this.title = title;
        this.authorName = authorName;
    }
    //copy book 
    public Book(Book book, int id) {
        this.category = book.category;
        this.title = book.title;
        this.authorName = book.authorName;
        this.isbn = id;
    }  
}