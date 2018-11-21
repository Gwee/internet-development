/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1.library.repos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 * @author Michael
 */
public class Context {

    private String partialPath = "src\\hw1\\library\\repos\\";

    public <T> void writeToFile(List<T> items, String fileName) {
        try {
            String path = partialPath + fileName;

            FileOutputStream f = new FileOutputStream(path);
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(items);
            o.close();
            f.close();
        } catch (Exception e) {
        }
    }

    public <T> List<T> readFromFile(String fileName) {
        try {
            String path = partialPath + fileName;

            FileInputStream fi = new FileInputStream(path);
            ObjectInputStream oi = new ObjectInputStream(fi);
            // Read objects
            List<T> items = (List<T>)oi.readObject();
            oi.close();
            fi.close();

            return items;
        } catch (IOException | ClassNotFoundException e) {
        }
        return null;
    }

    public String deleteAll(String fileName) {
        try {
            String path = partialPath + fileName;
            FileOutputStream writer = new FileOutputStream(path);
            writer.write(("").getBytes());
            writer.close();
            
            return "Deleted!";
        } catch (IOException e) {
              return "Not Deleted!";
        }

    }
}
