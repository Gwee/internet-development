/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lec1;

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
/**
 *
 * @author lac
 */
public class Lec1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Welcome blabla");
/*        
        FileOutputStream fos = new FileOutputStream("c:\\temp\\books.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        ArrayList<Book> mybooks = new ArrayList<Book>();
        for (int i = 0 ; i < 10 ; i++){
           Book x = new Book(i,"lama"+i,"kaha"+i); 
           mybooks.add(x);
        }
        
        oos.writeObject(mybooks);
*/
        
        FileInputStream fis = new FileInputStream("c:\\temp\\books.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object wawawiwa = ois.readObject();
        System.out.println("The end");
/*        
        Book x = new Book(7,"lama","kaha");
        FileOutputStream fos = new FileOutputStream("c:\\temp\\books.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(x);
        *\
/*        
        FileReader fr = new FileReader("c:\\temp\\shalom.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        System.out.println("line="+line);
        */
/*        
        int c;
        while ((c = fr.read()) != -1){
            System.out.print((char)c);
        }
        */
/*        
        FileInputStream fius = new FileInputStream("C:\\temp\\test1.bmp");
        FileOutputStream fos = new FileOutputStream("C:\\temp\\testCopy.bmp");
        int i = 0;
        while (fius.available() > 0){
            int b = fius.read();
            if ( i > 2000 && i % 5 == 0){
                fos.write(0);
            }
            else fos.write(b);
            i++;
        }
        fius.close();
        fos.close();
        */
/*        
 *  
        File f = new File("C:\\temp\\shalom.txt");
        System.out.println("exists="+f.exists());
        File f2 = new File("C:\\temp");
        System.out.println("exists2="+f2.exists());
        System.out.println("isDirectory1="+f.isDirectory());
        System.out.println("isDirectory2="+f2.isDirectory());
        
        String[] arr = f2.list();
        for (String str : arr){
            System.out.println(str);
        }
*/        
    }
}
