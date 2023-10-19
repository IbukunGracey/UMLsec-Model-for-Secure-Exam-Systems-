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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.event.Event;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Tunde
 */
@Named(value = "examPageBean")
@ViewScoped
public class ExamPageBean implements Serializable{

    /**
     * Creates a new instance of ExamPageBean
     */
    Connection conn;
    PreparedStatement prepare;
    ResultSet rset;

    
    @Getter @Setter private String counter, selectedOption, question_body, optionA,
            optionB, optionC, optionD, totalQuestionss, time, minutes = "", seconds = "";
    @Getter
    @Setter
    private int m, s, holdMinutes;
    //private Timeline timeline;
    private int count;
    private int indexx = 0;
    @Setter @Getter private static String[] correctOptions;
    @Setter @Getter private static String[] optionsTaken;
    @Setter @Getter private String examCodee;
    
//    HttpSession sessionQuestionNumber = Util.getSession();
//    HttpSession sessionLastQuestion = Util.getSession();
//    HttpSession sessionAnswersChoosen = Util.getSession();
    
   
  
    public ExamPageBean() {
        initializeee();
    }

    public void initializeee() {
        //   if(counter.equals("1")){
        //counter = "1";
        
        examCodee = FaceAuthentification2Bean.storeExamCode;
        getTotalQuestions();
        System.out.println("total questions is " + getTotalQuestionss());
        correctOptions = new String[Integer.parseInt(getTotalQuestionss())];
        optionsTaken = new String[Integer.parseInt(getTotalQuestionss())];
        for (int i = 0; i < optionsTaken.length; i++) {
            optionsTaken[i] = "";
            correctOptions[i] = "";
        }
        

        //if there is interruption
        if(!returnQuestionNumber().equals("")){
            setCounter(returnQuestionNumber());
            bringFirstQuestion();
        }
        if(!returnQuestionNumber().equals("") && !returnIndexAndAnswers().equals("")){
            System.out.println("i'm herereererererer");
            setCounter(returnQuestionNumber());
            bringFirstQuestion();
            indexx = Integer.valueOf(getCounter()) - 1;
            setSelectedOption(optionsTaken[indexx]);
            System.out.println("the option seletcted is " + getSelectedOption());
        }
       else{
        insertintoStoreQuestion();
        setCounter("1");
        bringFirstQuestion();
        indexx = Integer.valueOf(getCounter()) - 1;
        
        getTimeFromDB();

        minutes = getTime().substring(0, 2);
        System.out.println("the substring is " + minutes);

        seconds = getTime().substring(3, 5);
        }
        getAllCorrectOptions();
        //  timee();

       // }
        //else{
        //nextButton();
        // }
    }
    public void initializeee2() {
        //   if(counter.equals("1")){
        //counter = "1";
        examCodee = FaceAuthentification2Bean.storeExamCode;
        getTotalQuestions();
        System.out.println("total questions is " + getTotalQuestionss());
        correctOptions = new String[Integer.parseInt(getTotalQuestionss())];
        optionsTaken = new String[Integer.parseInt(getTotalQuestionss())];
        for (int i = 0; i < optionsTaken.length; i++) {
            optionsTaken[i] = "";
            correctOptions[i] = "";
        }
       // bringFirstQuestion();
        //indexx = Integer.valueOf(getCounter()) - 1;
        //getTimeFromDB();

        //minutes = getTime().substring(0, 2);
       // System.out.println("the substring is " + minutes);

        //seconds = getTime().substring(3, 5);
        getAllCorrectOptions();
        //  timee();

        // }
        //else{
        //nextButton();
        // }
    }
    
    public void bringBackOptionSelected(){
        getSelectedOption();
        
    }

    public void getTotalQuestions() {
        try {
            conn = Db.connect();
            String query = "select count(examcode) from examquestions";
            prepare = conn.prepareStatement(query);
            rset = prepare.executeQuery();

            while (rset.next()) {
                setTotalQuestionss(rset.getString(1));

            }
            getTotalQuestionss();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                prepare.close();
                rset.close();
                conn.close();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }

    public void bringFirstQuestion() {
        try {
            System.out.println("i need examcode " + FaceAuthentification2Bean.storeExamCode);
            // count = Integer.valueOf(counter);
            conn = Db.connect();
            String query = "select * from examquestions where counter=" + "'" + getCounter() + "'" + " and examcode=" + "'" + FaceAuthentification2Bean.storeExamCode + "'";
            prepare = conn.prepareStatement(query);
            rset = prepare.executeQuery();

            while (rset.next()) {
                setCounter(rset.getString(1));
               // sessionQuestionNumber.setAttribute("lastQuestionNumber", rset.getString(1));
                setQuestion_body(rset.getString(2));
                //sessionLastQuestion.setAttribute("lastQuestion", rset.getString(2));
                setOptionA(rset.getString(3));
                setOptionB(rset.getString(4));
                setOptionC(rset.getString(5));
                setOptionD(rset.getString(6));

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                prepare.close();
                rset.close();
                conn.close();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }

    public void changeValue() {
        //   System.out.println("hhh " + ev.getNewValue());

        optionsTaken[indexx] = getSelectedOption();
        inserrtIntoStoreIndexAndAnswers(String.valueOf(indexx), getSelectedOption());
        System.out.println("this position " + indexx + " is " + optionsTaken[indexx]);
    }

    public void nextButton() {
        updateStoreQuestion(getCounter());
        // ArrayList<String> list = new ArrayList<>();
        System.out.println("from start it is " + getCounter());
        int countt = Integer.valueOf(getCounter());
        countt = ++countt;
        //index = countt;
        counter = String.valueOf(countt);
        //sessionQuestionNumber.setAttribute("lastQuestionNumber", counter);
        if (Integer.valueOf(counter) >= 1 && Integer.valueOf(counter) <= Integer.valueOf(getTotalQuestionss())) {
            indexx = Integer.valueOf(getCounter()) - 1;
            updateStoreQuestion(getCounter());
            
            try {
                conn = Db.connect();
                String query = "select * from examquestions where counter=" + "'" + counter + "'" + " and examcode=" + "'" + FaceAuthentification2Bean.storeExamCode + "'";
                prepare = conn.prepareStatement(query);
                rset = prepare.executeQuery();
                System.out.println("ghtytytyty");

                while (rset.next()) {
                    setCounter(rset.getString(1));
                    setQuestion_body(rset.getString(2));
                    setOptionA(rset.getString(3));
                    setOptionB(rset.getString(4));
                    setOptionC(rset.getString(5));
                    setOptionD(rset.getString(6));

                }

                setSelectedOption(optionsTaken[indexx]);
                System.out.println("on next " + getSelectedOption());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    prepare.close();
                    rset.close();
                    conn.close();
                } catch (Exception ex1) {
                    ex1.printStackTrace();
                }
            }
        } else {
            setCounter(getTotalQuestionss());
            updateStoreQuestion(getCounter());
            //index = Integer.valueOf(getCounter()) - 1;
            //sessionQuestionNumber.setAttribute("lastQuestionNumber", getCounter());
            addMessage("no more questions");
        }

    }

    public void previousButton() {
        int countt = Integer.valueOf(getCounter());
        countt = --countt;
        //index = countt;
        counter = String.valueOf(countt);
        //sessionQuestionNumber.setAttribute("lastQuestionNumber", counter);
        if (Integer.valueOf(counter) >= 1 && Integer.valueOf(counter) <= Integer.valueOf(getTotalQuestionss())) {
            indexx = Integer.valueOf(getCounter()) - 1;
            updateStoreQuestion(getCounter());
            try {
                conn = Db.connect();
                String query = "select * from examquestions where counter=" + "'" + counter + "'" + " and examcode=" + "'" + FaceAuthentification2Bean.storeExamCode + "'";
                prepare = conn.prepareStatement(query);
                rset = prepare.executeQuery();

                while (rset.next()) {
                    setCounter(rset.getString(1));
                    setQuestion_body(rset.getString(2));
                    setOptionA(rset.getString(3));
                    setOptionB(rset.getString(4));
                    setOptionC(rset.getString(5));
                    setOptionD(rset.getString(6));

                }

                setSelectedOption(optionsTaken[indexx]);
                System.out.println("on back " + getSelectedOption());

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    prepare.close();
                    rset.close();
                    conn.close();
                } catch (Exception ex1) {
                    ex1.printStackTrace();
                }
            }
        } else {
            setCounter("1");
            updateStoreQuestion(getCounter());
            //sessionQuestionNumber.setAttribute("lastQuestionNumber", getCounter());
            //index = Integer.valueOf(getCounter()) - 1;
            addMessage("no more questions");
        }
    }

    public String submitButton() {
        //updateStoreQuestion("n");
        return "result_page?faces-redirect=true";
    }

    public static void cal(){
        int countt = 0;
        LoginBean bean = new LoginBean();
        for(int i=0; i<optionsTaken.length; i++){
            if(optionsTaken[i].equals(correctOptions[i])){
                countt = countt + 1;
            }
        }
        System.out.println("the score is " + countt);
        //new ExamPageBean().updateStoreQuestion("n");
        deleteAll1();
       deleteAll2();
        bean.logout2();
    }
    public void getAllCorrectOptions() {
        //initialize correct options;
//        correctOptions = new String[Integer.parseInt(getTotalQuestionss())];
//        for(int i=0; i<correctOptions.length; i++){
//            correctOptions[i] = "";
//        }

        try {
            conn = Db.connect();
            String query = "select correctanswer from examquestions where examcode=" + "'" + FaceAuthentification2Bean.storeExamCode + "'";
            prepare = conn.prepareStatement(query);
            rset = prepare.executeQuery();

            int i = 0;
            while (rset.next()) {
                correctOptions[i] = rset.getString(1);
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                prepare.close();
                rset.close();
                conn.close();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }

    }

    public void getTimeFromDB() {
        try {
            conn = Db.connect();
            String query = "select examduration from examdetails where examcode=" + "'" + FaceAuthentification2Bean.storeExamCode + "'";
            prepare = conn.prepareStatement(query);
            rset = prepare.executeQuery();

            while (rset.next()) {
                setTime(rset.getString(1));

            }
            System.out.println("the time is " + getTime());

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                prepare.close();
                rset.close();
                conn.close();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }

    }
    
    public void insertintoStoreQuestion(){
        try{
            HttpSession hs = Util.getSession();
         conn = Db.connect();
         String query = "insert into storequestionnumber values (?,?)";
         prepare = conn.prepareStatement(query);
         
         prepare.setString(1, String.valueOf(hs.getAttribute("userid")));
         prepare.setString(2, "");
         
         
         prepare.executeUpdate();
        }
        catch(Exception e){
           e.printStackTrace();
        }
        finally {
            try {
                prepare.close();
               // rset.close();
                conn.close();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }
    
    public static void deleteAll1(){
         Connection connn = null;
              PreparedStatement preparee = null;
              
              try{
              
        HttpSession hs = Util.getSession();
         connn = Db.connect();
         String query = "delete from storequestionnumber where userid="+ "'" + String.valueOf(hs.getAttribute("userid")) + "'"; 
         preparee = connn.prepareStatement(query);
        
         //prepare.setString(2, String.valueOf(hs.getAttribute("userid")));
         preparee.executeUpdate();
            System.out.println("updated successfullyj");
        }
        catch(Exception e){
           e.printStackTrace();
            }
        
        finally {
            try {
                preparee.close();
               // rset.close();
                connn.close();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }
    public static void deleteAll2(){
         Connection connn = null;
              PreparedStatement preparee = null;
              try{
        HttpSession hs = Util.getSession();
         connn = Db.connect();
         String query = "delete  from storeindexxandanswers where userid="+ "'" + String.valueOf(hs.getAttribute("userid")) + "'"; 
         preparee = connn.prepareStatement(query);
        
         //prepare.setString(2, String.valueOf(hs.getAttribute("userid")));
         preparee.executeUpdate();
            System.out.println("updated successfully");
        }
        catch(Exception e){
           e.printStackTrace();
            }
        
        finally {
            try {
                preparee.close();
               // rset.close();
                connn.close();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }
    public void updateStoreQuestion(String counter){
        try{
             HttpSession hs = Util.getSession();
        //hs.setAttribute("userid", "");
         conn = Db.connect();
         String query = "update storequestionnumber set questionNumber=? where userid=" + "'" + String.valueOf(hs.getAttribute("userid")) + "'"; 
         prepare = conn.prepareStatement(query);
         prepare.setString(1, counter);
         //prepare.setString(2, String.valueOf(hs.getAttribute("userid")));
         prepare.executeUpdate();
            System.out.println("updated successfullyjj");
        }
        catch(Exception e){
//            if(String.valueOf(e.getMessage().charAt(0)).equals("D") || !(e.getMessage().equals(""))){
//                updateStoreQuestion(counter);
//            }
            e.printStackTrace();
        }
        finally {
            try {
                prepare.close();
               // rset.close();
                conn.close();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }
    
    public void checkForIndex(String index){
         try{
         conn = Db.connect();
         String query = "select indexx from storeindexxandanswers where indexx=" + "'" + index + "'"; 
         prepare = conn.prepareStatement(query);
         rset = prepare.executeQuery();
         
         while(rset.next()){
             rset.getString(1);
         }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                prepare.close();
                rset.close();
                conn.close();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }
    public void inserrtIntoStoreIndexAndAnswers(String index, String answer){
        try{
            HttpSession hs = Util.getSession();
         conn = Db.connect();
         String query = "insert into storeindexxandanswers values (?,?,?)";
         prepare = conn.prepareStatement(query);
         
         prepare.setString(1, String.valueOf(hs.getAttribute("userid")));
         prepare.setString(2, index);
         prepare.setString(3, answer);
         
         prepare.executeUpdate();
        }
        catch(Exception e){
            if(String.valueOf(e.getMessage().charAt(0)).equals("D")){
                updateStoreIndexAndAnswers(index, answer);
            }
        }
        finally {
            try {
                prepare.close();
               // rset.close();
                conn.close();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }
    
    public void selectActionoptionsAndIndex(){
        
    }
    public void updateStoreIndexAndAnswers(String index, String answer){
        try{
            HttpSession hs = Util.getSession();
         conn = Db.connect();
         String query = "update storeindexxandanswers set answers=?  where indexx=" + "'"+index+"'" + " and userid=" + "'" + String.valueOf(hs.getAttribute("userid")) + "'"; 
         prepare = conn.prepareStatement(query);
         //prepare.setString(1, String.valueOf(hs.getAttribute("userid")));
         prepare.setString(1, answer);
         //prepare.setString(3, index);
         prepare.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                prepare.close();
               // rset.close();
                conn.close();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }
    
    public String returnQuestionNumber(){
     try{
         HttpSession hs = Util.getSession();
         conn = Db.connect();
         String a="";
         String query = "select questionNumber from storequestionnumber where userid="+ "'" + String.valueOf(hs.getAttribute("userid")) + "'";
         prepare = conn.prepareStatement(query);
         rset = prepare.executeQuery();
         
         while(rset.next()){
             a = rset.getString(1);
         }
         return a;
     }   
     catch(Exception e){
         e.printStackTrace();
     }
      finally {
            try {
                prepare.close();
                rset.close();
                conn.close();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    return "";
    }
    
    public String returnIndexAndAnswers(){
        try{
           // Map<String, String> a = new HashMap<>();
            HttpSession hs = Util.getSession();
         conn = Db.connect();
         String query = "select * from storeindexxandanswers where userid="+ "'" + String.valueOf(hs.getAttribute("userid")) + "'";
         prepare = conn.prepareStatement(query);
         rset = prepare.executeQuery();
         
         
         while(rset.next()){
            
            optionsTaken[Integer.valueOf(rset.getString(2))] = rset.getString(3);
                
         }
         for(int i=0; i<2; i++){
             System.out.println("gggggggggggggggggggggggg " + optionsTaken[i]);
         }
         for(String e: optionsTaken){
             System.out.println("this is is sis " + e );   
         }
         return optionsTaken[0];
        }
        catch(Exception e){
            e.printStackTrace();
        }
         finally {
            try {
                prepare.close();
              rset.close();
                conn.close();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
   return "";
    }
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage("mess22", message);
    }

}
