/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Tunde
 */
public class Db {
     public static Connection connect(){
      try{
          
         Class.forName("com.mysql.jdbc.Driver");
         System.out.println("loadeddd");
        //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/examonlinedb","root","");
       Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/onlineexam","root","");
         
       return conn;
         
     }
     catch(Exception e){
//         System.out.println(e);
         e.printStackTrace();
         System.out.println(e.getMessage());
System.out.println("no db");
         return null;
     }   
}
}
