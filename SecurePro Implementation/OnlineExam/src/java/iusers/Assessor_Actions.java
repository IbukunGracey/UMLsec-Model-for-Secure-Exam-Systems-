/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iusers;

import DAO.Db;
import exam.Exam;
import interfaces.IAssessor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import person.Assessor;
import person.Proctor;

/**
 *
 * @author Tunde
 */
public class Assessor_Actions implements IAssessor {
 Connection conn;
    PreparedStatement prepare;
    ResultSet rset;
    
    private int counter = 1;
    Exam exam = new Exam();
   @Override
    public String createAccount(person.Assessor assessor) {
       try{
            int ch = 0;
           
            assessor  = new Assessor();
             conn = Db.connect();
            String sql = "insert into assessor_reg_table values (?,?,?,?,?,?,?,?,?)";
            prepare = conn.prepareStatement(sql);
            
            prepare.setString(1, assessor.getAssessorId());
            prepare.setString(2, assessor.getFirstName());
            prepare.setString(3, assessor.getLastName());
            prepare.setString(4, assessor.getAddress());
            prepare.setString(5, assessor.getPassword());
            //prepare.setString(6, examtaker.getPhotoId());
           // prepare.setString(6, "C:\\Users\\Tunde\\Documents\\NetBeansProjects\\OnlineExam\\web\\images\\photocam\\proctor\\9193932.jpeg");
            prepare.setString(6, assessor.getEmail());
            prepare.setString(7, getCurrentDate());
            prepare.setString(8, getCurrentTime());
            prepare.setString(9, assessor.getUserType());
           
            ch = prepare.executeUpdate();
            System.out.println("fff " + ch);
           
            if(ch==1){
                 sql = "insert into login_table values (?,?,?,?)";
            prepare = conn.prepareStatement(sql);
            prepare.setString(1, assessor.getAssessorId());
            prepare.setString(2, assessor.getPassword());
            prepare.setString(3, assessor.getUserType());
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

    @Override
    public String createExamDetails(Exam exam){
         try{
            int ch = 0;
           
            
             conn = Db.connect();
            String sql = "insert into examdetails values (?,?,?,?,?)";
            prepare = conn.prepareStatement(sql);
            
            //prepare.setString(1, exam.getExamId());
            prepare.setString(1, exam.getExamCode());
            prepare.setString(2, exam.getExamTitle());
            prepare.setString(3, exam.getDuration());
            prepare.setString(4, getCurrentDate());
            prepare.setString(5, getCurrentTime());
            
            ch = prepare.executeUpdate();
            System.out.println("fff " + ch);
           
            if(ch==1){
                 sql = "insert into examcodelist values (?)";
            prepare = conn.prepareStatement(sql);
            prepare.setString(1, exam.getExamCode());
             ch = prepare.executeUpdate();
            System.out.println("fff " + ch);
           
            if(ch==1){
                addMessage("exam details successfully submitted");
                return "yes";
            }
                
            }
         }
         catch(Exception e){
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
    
    

    @Override
    public void createExamInstructionsAndRules() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String setExamQuestions(String examCode) {
        try{
            int ch = 0;
           
           
             conn = Db.connect();
            String sql = "insert into examquestions values (?,?,?,?,?,?,?,?)";
            prepare = conn.prepareStatement(sql);
            
            prepare.setString(1, exam.getCounter());
            prepare.setString(2, exam.getQuestion_body());
            prepare.setString(3, exam.getOptionA());
            prepare.setString(4, exam.getOptionB());
            prepare.setString(5, exam.getOptionC());
            prepare.setString(6, exam.getOptionD());
            prepare.setString(7, exam.getCorrectOption());
            prepare.setString(8,  examCode);
            
            ch = prepare.executeUpdate();
            System.out.println("fff " + ch);
           
            if(ch==1){
                addMessage2("question " + counter + " successfully submitted");
               int count = Integer.valueOf(exam.getCounter());
               count = ++count;
               exam.setCounter(String.valueOf(count));
               return "yes";
            }
         }
         catch(Exception e){
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
    
    @Override
    public void assignExamtakerToProctor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    //add message info
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage("mess", message);
    }
    
    public void addMessage2(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage("mess2", message);
    }
    //increment counter
    private void incrementCounter(){
        int count  = Integer.valueOf(counter);
        count = count++;
        //counter = String.valueOf(count);
    }
}
