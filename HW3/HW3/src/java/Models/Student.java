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
public class Student implements Serializable
{
    private String email;
    private String firstName;
    private String lastName;
    private String password;

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
    
    public String getPassword() {
        return password;
    }
    private int  id ; 
 
    public Student(String email, String firstName, String lastName, int id, String password) {
        this.email = email; 
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.password = password;
    }
}
