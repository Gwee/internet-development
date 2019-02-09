/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author amitmarko
 */
import java.io.Serializable;

@SuppressWarnings("serial")
public class Student implements Serializable {

    private String ID;
    private String Fname;
    private String Sname;
    private String email;
    private int maxBorrows;
    private int myBorrows;
    private int MaxFine;
    private int myFine;
    private boolean premission;
    private String password;
    
    
    

    public Student() {
    }

    
    
    
    
    
    
    
    
    
    public Student(String ID, String Fname, String Sname, String email, int maxBorrows, int myBorrows, int MaxFine, int myFine, boolean premission, String password) {
        this.ID = ID;
        this.Fname = Fname;
        this.Sname = Sname;
        this.email = email;
        this.maxBorrows = maxBorrows;
        this.myBorrows = myBorrows;
        this.MaxFine = MaxFine;
        this.myFine = myFine;
        this.premission = premission;
        this.password = password;
    }

    
    
    
    
    
    
    
    
    
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String Sname) {
        this.Sname = Sname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMaxBorrows() {
        return maxBorrows;
    }

    public void setMaxBorrows(int maxBorrows) {
        this.maxBorrows = maxBorrows;
    }

    public int getMyBorrows() {
        return myBorrows;
    }

    public void setMyBorrows(int myBorrows) {
        this.myBorrows = myBorrows;
    }

    public int getMaxFine() {
        return MaxFine;
    }

    public void setMaxFine(int MaxFine) {
        this.MaxFine = MaxFine;
    }

    public int getMyFine() {
        return myFine;
    }

    public void setMyFine(int myFine) {
        this.myFine = myFine;
    }

    public boolean isPremission() {
        return premission;
    }

    public void setPremission(boolean premission) {
        this.premission = premission;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
    
    
}
