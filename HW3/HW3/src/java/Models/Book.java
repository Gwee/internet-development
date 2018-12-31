/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;

/**
 *
 * @author Guy
 */
public class Book implements Serializable {

    private int id;
    private String isbn;
    //private int  catId;
    private String category;
    private String title;
    private String authorName;
    private String publishDate;
    
    public int getid() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getCatId() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public void setIsbn(String isbn) {
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

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public Book() {
    }

    public Book(String isbn, String category, String title, String authorName, String publishDate) {
        this.isbn = isbn;
        this.category = category;
        this.title = title;
        this.authorName = authorName;
        this.publishDate = publishDate;
    }
    
        public Book(String isbn, int id, String category, String title, String authorName, String publishDate) {
        this.isbn = isbn;
        this.category = category;
        this.title = title;
        this.authorName = authorName;
        this.id = id;
        this.publishDate = publishDate;
    }

    //copy book 
    public Book(Book book, int id) {
        this.category = book.category;
        this.title = book.title;
        this.authorName = book.authorName;
        this.isbn = book.isbn;
        this.id = id;
        this.publishDate = book.publishDate;
    }
}
