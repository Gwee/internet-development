/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lec1;

import java.io.Serializable;

/**
 *
 * @author lac
 */
public class Book implements Serializable{
    private int isbn;
    private String author;
    private String name;
    
    public Book(int i, String a, String n){
        isbn = i; author=a; name = n;
    }
    
}
