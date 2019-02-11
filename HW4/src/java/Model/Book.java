/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Guy Moyal
 */
import java.util.*;
import java.io.Serializable;
import java.util.Map.Entry;

@SuppressWarnings("serial")
public class Book implements Serializable {

    private String ID;
    private String Book_Name;
    private String Author;
    private String Category;
    private int date;
    private int Number_of_copys;
    private int Availabe_Copys;
    
    
    
    
    

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setBook_Name(String Book_Name) {
        this.Book_Name = Book_Name;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public void setDate(int date) {
        this.date = date;
    }

    
    
    
    
    public Book() {
    }
   
    
    public String getID(){
        return ID;
    }
    

    public Book(String iD, String book_Name, String A, String category, int date, int number_of_copys) {
        ID = iD;
        Book_Name = book_Name;
        Author = A;
        Category = category;
        this.date = date;
        Number_of_copys = number_of_copys;
        Availabe_Copys = Number_of_copys;
    
    }

    
 
    
    public String getCat() {
        return Category;
    }

    public int getAvilabeCopys() {
        return Availabe_Copys;
    }

    public String getAuthor() {
        return Author;
    }

    public String getBook_Name() {
        return Book_Name;
    }

    public int getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Book [ID=" + ID + ", Book_Name=" + Book_Name + ", Author=" + Author
                + ", Category=" + Category + ", date=" + date
                + ", Number_of_copys=" + Number_of_copys + ", Availabe_Copys="
                + Availabe_Copys + "]";
    }

 

    public void setNumber_of_copys(int number_of_copys) {
        Number_of_copys = number_of_copys;
    }

    public boolean fullCopys() {
        return Number_of_copys == Availabe_Copys;
    }

    public int getNumber_of_copys() {
        return Number_of_copys;
    }

    public boolean isAvailabe() {
        if (Availabe_Copys <= Number_of_copys) {
            return true;
        }
        return false;
    }

    public boolean NoCopys() {
        return Number_of_copys <= 0;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public int getAvailabe_Copys() {
        return Availabe_Copys;
    }

    public void setAvailabe_Copys(int Availabe_Copys) {
        this.Availabe_Copys = Availabe_Copys;
    }


        

    boolean checkID(String ID) {
        return this.ID.equals(ID);
    }

    boolean checkCategory(String C) {
        return Category.equals(C);
    }

    

 
    public boolean CompareID(String s) {
        return ID.equals(s);
    }

    
}
