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
public class Copy implements Serializable{
	
	private String Isbn ;
	private int Copy_number;
                     private boolean isTookean; 
                    private static final long serialVersionUID = 1L;

    public Copy() {
    }

                    
                    
                    
                    
                    
    public Copy(String Isbn, int Copy_number, boolean isTookean) {
        this.Isbn = Isbn;
        this.Copy_number = Copy_number;
        this.isTookean = isTookean;
    }

                    
                    
                    
    public String getIsbn() {
        return Isbn;
    }

    public void setIsbn(String Isbn) {
        this.Isbn = Isbn;
    }

    public int getCopy_number() {
        return Copy_number;
    }

    public void setCopy_number(int Copy_number) {
        this.Copy_number = Copy_number;
    }

    public boolean isIsTookean() {
        return isTookean;
    }

    public void setIsTookean(boolean isTookean) {
        this.isTookean = isTookean;
    }

                    
	
}
