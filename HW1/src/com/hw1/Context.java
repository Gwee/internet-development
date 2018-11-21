package com.hw1;
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

}
