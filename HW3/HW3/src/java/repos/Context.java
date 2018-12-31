/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.List;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Guy
 */
public class Context {

    dbRecords records;
    String partialPath = System.getProperty("user.home") + "\\kutamich\\";

    public Context() throws IOException {

        boolean check = new File(partialPath).exists();
        if (!check) {
            records = new dbRecords(this, partialPath);
            records.creatFiles();
            records.setBooks();
            records.setStudents();
        }
    }

    public <T> void writeToFile(List<T> items, String fileName) {
        try {
            String path = partialPath + fileName;

            FileOutputStream f = new FileOutputStream(path);
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(items);
            o.close();
            f.close();
        } catch (IOException e) {
        }
    }

    public <T> List<T> readFromFile(String fileName) {
        try {
            String path = partialPath + fileName;

            FileInputStream fi = new FileInputStream(path);
            ObjectInputStream oi = new ObjectInputStream(fi);
            // Read objects
            List<T> items = (List<T>) oi.readObject();
            oi.close();
            fi.close();

            return items;
        } catch (IOException | ClassNotFoundException e) {
            int t = 3;
        }
        return null;
    }

    public String deleteAll(String fileName) {
        try {
            //String path = partialPath + fileName;
            FileOutputStream writer = new FileOutputStream(fileName);
            writer.write(("").getBytes());
            writer.close();

            return "Deleted!";
        } catch (IOException e) {
            return "Not Deleted!";
        }
    }

}
