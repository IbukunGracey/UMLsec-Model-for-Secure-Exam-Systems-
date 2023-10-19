/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.Db;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Tunde
 */
@Named(value = "faceAuthentification2Bean")
@ViewScoped
public class FaceAuthentification2Bean implements Serializable{

    /**
     * Creates a new instance of FaceAuthentification2Bean
     */
    
      @Setter @Getter private String date, time, script, examTitle, examCode, examDuration;
       Connection conn;
    PreparedStatement prepare;
    ResultSet rset;
    
    @Setter @Getter public static String storeExamCode;
    
    public FaceAuthentification2Bean() {
    }
    
     private void doRedirect(String url){
          try {
           //   personDAO.updateCheckkColumn2(userId);
          
          FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().redirect(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     
     public void check_for_date_schedule() {
         
         HttpSession hs = Util.getSession();
        String d = String.valueOf(hs.getAttribute("userid"));
        System.out.println("gggggg " + d);
        //personDAO.updateCheckkColumn2(userId);
        if (d.equals("")) {
            //return "index?faces-redirect=true";
            doRedirect("index.xhtml");
        }
        else{
getDateAndTimeFromDB();
        String currentTime = getCurrentTime();
        String currentDate = getCurrentDate();
            
        if(FaceAuthenticationBean.isStatus()==false){
        doRedirect("examtaker_dashboard.xhtml");
        }
        }
        

    }

    public void addMessage(String summary) {
        FacesContext context = FacesContext.getCurrentInstance();
       // context.addMessage("message", summary);
    }


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

    private void getDateAndTimeFromDB() {
HttpSession hs = Util.getSession();
        String dd = String.valueOf(hs.getAttribute("userid"));
        try {
            
            conn = Db.connect();
            String sql = "select * from scheduleexam where userid=" + "'" + dd + "'";
            prepare = conn.prepareStatement(sql);
            rset = prepare.executeQuery();
            System.out.println("sdfds");

            while (rset.next()) {
                date = rset.getString(3);
                //System.out.println(examDateAndTime[0]);
                time = rset.getString(4);
            }
            System.out.println(date);
            System.out.println(time);

           // return examDateAndTime;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                prepare.close();
                conn.close();
                rset.close();
                // examtaker.setExamTakerId(null);
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }

       // return null;
    }
    
    
    public String getExamTitle(){
HttpSession hs = Util.getSession();
        String dd = String.valueOf(hs.getAttribute("userid"));
             try {
            
            conn = Db.connect();
            String sql = "select * from scheduleexam where userid=" + "'" + dd + "'";
            prepare = conn.prepareStatement(sql);
            rset = prepare.executeQuery();
            System.out.println("sdfds");

            while (rset.next()) {
                examCode = rset.getString(2);
                //System.out.println(examDateAndTime[0]);
               break;
            }
            
            sql = "select * from examdetails where examcode=" + "'" + examCode + "'";
            prepare = conn.prepareStatement(sql);
            rset = prepare.executeQuery();
            System.out.println("sdfds");
            
           while (rset.next()) {
                examTitle = rset.getString(2);
                //System.out.println(examDateAndTime[0]);
               break;
            }
           
           return examTitle;
           // return examDateAndTime;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                prepare.close();
                conn.close();
                rset.close();
                // examtaker.setExamTakerId(null);
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    return "";
    }
    
    public String getExamCode(){
    HttpSession hs = Util.getSession();
        String dd = String.valueOf(hs.getAttribute("userid"));
             try {
            
            conn = Db.connect();
            String sql = "select * from scheduleexam where userid=" + "'" + dd + "'";
            prepare = conn.prepareStatement(sql);
            rset = prepare.executeQuery();
            System.out.println("sdfds");

            while (rset.next()) {
                examCode = rset.getString(2);
                storeExamCode = examCode;
                //System.out.println(examDateAndTime[0]);
               break;
            }
            
            return examCode;
           
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                prepare.close();
                conn.close();
                rset.close();
                // examtaker.setExamTakerId(null);
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    return "";
    }
    
    public String getExamDuration(){
    HttpSession hs = Util.getSession();
        String dd = String.valueOf(hs.getAttribute("userid"));
             try {
            
            conn = Db.connect();
            String sql = "select * from scheduleexam where userid=" + "'" + dd + "'";
            prepare = conn.prepareStatement(sql);
            rset = prepare.executeQuery();
            System.out.println("sdfds");

            while (rset.next()) {
                examCode = rset.getString(2);
                //System.out.println(examDateAndTime[0]);
               break;
            }
            
            sql = "select * from examdetails where examcode=" + "'" + examCode + "'";
            prepare = conn.prepareStatement(sql);
            rset = prepare.executeQuery();
            System.out.println("sdfds");
            
           while (rset.next()) {
                examDuration = rset.getString(3);
                //System.out.println(examDateAndTime[0]);
               break;
            }
           
           return examDuration;
           // return examDateAndTime;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                prepare.close();
                conn.close();
                rset.close();
                // examtaker.setExamTakerId(null);
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    return "";
    }

//public String start(){
////    ExamPageBean exampage = new ExamPageBean();
////    exampage.initialize();
////    return "exam_page?faces-redirect=true";
//}    
}
