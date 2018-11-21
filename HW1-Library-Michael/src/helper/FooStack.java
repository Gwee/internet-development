/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import hw1.library.Models.Book;
import hw1.library.Models.Student;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Michael
 */
public class FooStack {
    
        public List<Book> bookCopies(int copies, Book newBook, int isbn) {
        List<Book> books = new ArrayList<>();

        for (int i = 0; i < copies; i++) {
            books.add(new Book(newBook, isbn + i));
        }
        return books;
    }
     
    public Boolean isIDNumberValid(int id, List<Student> students) {
        if(id < 1) return false;
        
        if(students == null) return true;
        
        for (Student student : students) {
            if (student.getId() == id)
               return false;
        }
        return true;
    } 
    
   
    public int diffDates(Date d1, Date d2) 
    {
       long diff = d1.getTime() - d2.getTime();
       int days = (int)(diff / (1000 * 60 * 60 * 24));      
       return days;       
    }
    
    
    
    
    
    
    
    
}
