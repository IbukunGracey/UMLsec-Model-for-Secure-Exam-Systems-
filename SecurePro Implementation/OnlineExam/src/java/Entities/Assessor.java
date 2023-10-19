/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import DAO.Assessor_Interface;
import DAO.Db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import lombok.Getter;

/**
 *
 * @author Tunde
 */
public class Assessor implements Assessor_Interface{

    /**
     *
     * @param email
     * @param name
     * @param address
     * @param phonenumber
     */
    static Connection conn;
    static PreparedStatement prepare;
    static ResultSet rset;
    
    @Getter
    private String date_time;
    @Override
    public boolean createAccount(String email, String name, String address, String phonenumber,
                              String password, String image) {
        try{
            int ch=-1;
            conn = Db.connect();
            String sql = "insert into assessor values (?,?,?,?,?,?,?,?,?,?,?)";
            prepare = conn.prepareStatement(sql);
            prepare.setString(1, email);
            prepare.setString(2, name);
            prepare.setString(3, address);
            prepare.setString(4, phonenumber);
            prepare.setString(5, password);
            prepare.setString(6, image);
            prepare.setString(7, date_timee());
           
          
            ch = prepare.executeUpdate();
            
            if(ch!=-1){
                return true;
            }
            else{
                return false;
            }
           
        }
        catch(Exception e){
            e.printStackTrace();
        }
         finally {
            try {
                prepare.close();
             conn.close();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
        return false;
        
    }
    
     //This method returns the current time
    private String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        Date time = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        String currentTime = dateFormat.format(time);

        if (currentTime.isEmpty()) {
            return "";
        }

        return currentTime;
    }

    //This method returns the current date
    private String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        Date time = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd:MM:YYYY");
        String currentDate = dateFormat.format(time);

        if (currentDate.isEmpty()) {
            return "";
        }

        return currentDate;
    }
    
  
      private String date_timee(){
        date_time = getCurrentDate() + getCurrentTime();
        return date_time;
    }
    
}
