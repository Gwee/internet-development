package com.hw1;
    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
import java.io.*;
import java.util.List;

    public class Context {

        private String partialPath = "src\\hw1\\library\\repos\\";

        public <T> void writeToFile(List<T> items, File file) {
            try {
                FileOutputStream f = new FileOutputStream(file);
                ObjectOutputStream o = new ObjectOutputStream(f);

                // Write objects to file
                o.writeObject(items);
                o.close();
                f.close();
            } catch (Exception e) {
            }
        }

        public <T> List<T> readFromFile(File file) {
            try {

                FileInputStream fi = new FileInputStream(file);
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

}
