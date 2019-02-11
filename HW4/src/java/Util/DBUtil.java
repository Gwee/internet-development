/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Guy Moyal
 */
public class DBUtil {
    
    
   private static Connection connection =null;  
     
   public static  Connection getConnection() {
    if (connection != null) {
            return connection;
    } else {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
             
            String urlCn="jdbc:derby://localhost:1527/DB_Library";
            connection = DriverManager.getConnection(urlCn, "root", "root");
  
        } catch (Exception e) {
            e.printStackTrace();
             }
    }
    return  connection;
}
    
    
    
    
}
