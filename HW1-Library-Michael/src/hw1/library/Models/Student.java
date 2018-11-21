/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1.library.Models;

import hw1.library.*;
import java.io.Serializable;

/**
 *
 * @author Michael
 */
public class Student implements Serializable
{
    private String email;
    private String firstName;
    private String lastName;

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }
    private int  id ; 
 
    public Student(String email, String firstName, String lastName, int id) {
        this.email = email; 
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.email = email;
    }
}
