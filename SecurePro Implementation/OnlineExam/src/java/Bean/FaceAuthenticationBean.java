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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Tunde
 */
@Named(value = "faceAuthenticationBean")
@ViewScoped
public class FaceAuthenticationBean implements Serializable {

    Connection conn;
    PreparedStatement prepare;
    ResultSet rset;

     @Setter @Getter private String date, time, script;
    @Setter
    @Getter
    private static boolean status;

    /**
     * Creates a new instance of FaceAuthenticationBean
     */
    public FaceAuthenticationBean() {
    }

    public void check_for_date_schedule() {
//        String[] examDateAndTime = new String[2];
//        examDateAndTime[0] = getDateAndTimeFromDB()[0];
//        System.out.println(examDateAndTime[0]);
//        examDateAndTime[1] = getDateAndTimeFromDB()[1];
getDateAndTimeFromDB();
        String currentTime = getCurrentTime();
        String currentDate = getCurrentDate();
        
        System.out.println(currentTime + "  " + currentDate);

        if (!(getDate().equals(currentDate))) {
            status = false;
        } else {
            status = true;
        }

    }

    public void addMessage(String summary) {
        FacesContext context = FacesContext.getCurrentInstance();
       // context.addMessage("message", summary);
    }

    private void doRedirect(String url) {
        try {
            //   personDAO.updateCheckkColumn2(userId);

            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().redirect(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        Date time = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
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

    public String  eb() {
        check_for_date_schedule();
        if (status) {
            return "face_authentification?faces-redirect=true";
        } else {
            //addMessage("This is not the specified date and time");
//            script = "alert('This is not the specified date and time');";
//RequestContext.getCurrentInstance().execute(script);

            return "examtaker_dashboard?faces-redirect=true";
        }
    }
//getDateAndTimeFromDB();

    //return "examtaker_dashboard?faces-redirect=true";}
    
    public void startTeamViewer(){
         try{
            System.out.println("Opening TeamViwer");
            Runtime runtime = Runtime.getRuntime();
            //Process process = runtime.exec("C:\\Program Files (x86)\\Notepad++\\notepad++.exe");
            Process process = runtime.exec("C:\\Users\\Tunde\\Documents\\NetBeansProjects\\OnlineExam\\build\\web\\assets\\app\\TeamViewerQS.exe");
//            try{
//                Thread.sleep(5000);
//                        
//            }
//            catch(Exception e){
//                e.printStackTrace();
//            }
            //System.out.println("Closing Teamviewer");
            //process.destroy();
            //runtime.exit(0);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
