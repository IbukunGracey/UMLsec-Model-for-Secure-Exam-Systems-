/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iusers;

import DAO.Db;
import interfaces.IProctor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import person.Proctor;

/**
 *
 * @author Tunde
 */
public class Proctor_Actions implements IProctor{
    Connection conn;
    PreparedStatement prepare;
    ResultSet rset;
    
    @Override
    public String createAccount(person.Proctor proc) {
       try{
            int ch = 0;
           
            proc  = new Proctor();
             conn = Db.connect();
            String sql = "insert into proctor_reg_table values (?,?,?,?,?,?,?,?,?,?)";
            prepare = conn.prepareStatement(sql);
            
            prepare.setString(1, proc.getProctorId());
            prepare.setString(2, proc.getFirstName());
            prepare.setString(3, proc.getLastName());
            prepare.setString(4, proc.getAddress());
            prepare.setString(5, proc.getPassword());
            //prepare.setString(6, examtaker.getPhotoId());
            prepare.setString(6, "C:\\Users\\Tunde\\Documents\\NetBeansProjects\\OnlineExam\\web\\images\\photocam\\proctor\\9193932.jpeg");
            prepare.setString(7, proc.getEmail());
            prepare.setString(8, getCurrentDate());
            prepare.setString(9, getCurrentTime());
            prepare.setString(10, proc.getUserType());
           
            ch = prepare.executeUpdate();
            System.out.println("fff " + ch);
           
            if(ch==1){
                 sql = "insert into login_table values (?,?,?,?)";
            prepare = conn.prepareStatement(sql);
            prepare.setString(1, proc.getProctorId());
            prepare.setString(2, proc.getPassword());
            prepare.setString(3, proc.getUserType());
            prepare.setString(4, "no");
            
            ch = prepare.executeUpdate();
            System.out.println("222 " + ch);
            
            if(ch==1){
               addMessage("successfully submitted");
                return "yes";  
            }
            else{
                addMessage("error");
            }
        }
        else{
                addMessage("error");
            }
            
        }
        catch(Exception e){
            if(e.getMessage().contains("PRIMARY")){
                addMessage("user id has been choosen");
            }
            e.printStackTrace();
        }
        finally {
            try {
                prepare.close();
             conn.close();
             //examtaker.setExamTakerId(null);
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    return "no";
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
    
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage("mess", message);
    }
    
    
    @Override
    public void superviseExam() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
