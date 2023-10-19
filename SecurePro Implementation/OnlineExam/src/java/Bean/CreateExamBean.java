/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.Db;
import exam.Exam;
import iusers.Assessor_Actions;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
@Named(value = "createexamBean")
@ViewScoped
public class CreateExamBean implements Serializable{

    Connection conn;
    PreparedStatement prepare;
    ResultSet rset;
    
    /**Creates a new instance of CreateExamBean**/
    @Getter @Setter 
    private String examId, examTitle, examCode, examCode2, duration;
    @Getter @Setter
    private ArrayList<String> categoryExamName = new ArrayList<>();
    @Getter @Setter
    private String counter="1", question_body, optionA, optionB, optionC, optionD, correctOption;
    Assessor_Actions assessor  = new Assessor_Actions();
    Exam exam = new Exam();
    public CreateExamBean() {
    }
 
    // create exam details
    public String createExamDetails(){
       //exam.setExamId(examId);
       exam.setExamCode(examCode);
       exam.setExamTitle(examTitle);
       exam.setDuration(duration);
       
      String ch = assessor.createExamDetails(exam);
      if(ch.equals("yes")){
          //getCategoryExamName();
          setExamCode("");
          setExamTitle("");
          setDuration("");
          System.out.println("good");
      }
      else{
          
      }
    return "";
    }
    
    //set questions
    public void setExamQuestions(){
       exam.setCounter(counter);
       exam.setQuestion_body(question_body);
       exam.setOptionA(optionA);
       exam.setOptionB(optionB);
       exam.setOptionC(optionC);
       exam.setOptionD(optionD);
       exam.setCorrectOption(correctOption);
       String g = assessor.setExamQuestions(examCode2);
       
       if(g.equals("yes")){
          int count = Integer.valueOf(counter);
          count = ++count;
          counter = String.valueOf(count);
          setCounter(counter);
          exam.setCounter(counter);
      //  exam.setCounter(counter);
       setQuestion_body("");
       setOptionA("");
       setOptionB("");
       setOptionC("");
       setOptionD("");
       setCorrectOption("");
       }
    }
    
    public void verifyLogin() {
        HttpSession hs = Util.getSession();
        String d = String.valueOf(hs.getAttribute("userid"));
        System.out.println("gggggg " + d);
        if (!LoginBean.isLoginExamtaker()) {
            //return "index?faces-redirect=true";
            doRedirect("index.xhtml");
        }
//        else{
//         //   return "examtaker_dashboard?faces-redirect=true";
//         examtakerDAO.updateCheckkColumn(userId);
//        }
    }
    
    // private UserService userService;
    private void doRedirect(String url) {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().redirect(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


public ArrayList<String> getCategoryExamName() {

        try {
            ArrayList<SelectItem> exName = new ArrayList<>();
            conn = Db.connect();
            String query = "select * from examcodelist";
            prepare = conn.prepareStatement(query);
            rset = prepare.executeQuery();
            categoryExamName.removeAll(categoryExamName);

            while (rset.next()) {
                categoryExamName.add(rset.getString(1));
               
            }
           // categoryExamName.addAll(categoryExamName);

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
        return categoryExamName;
    }


public void reloadCourses(){
    getCategoryExamName();
    setCounter("1");
    addMessage("updated successfully");
}

 public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage("mess2", message);
    }
 
 
}


