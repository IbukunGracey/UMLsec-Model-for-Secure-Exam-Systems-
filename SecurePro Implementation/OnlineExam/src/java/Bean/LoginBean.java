/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.AssessorDAO;
import DAO.ExamTakerDAO;
import DAO.PersonDAO;
import DAO.ProctorDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Tunde
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    /**
     * Creates a new instance of LoginBean
     */
    @Setter
    @Getter
    private String user_reg, userId, password, user_reg1;
    
    @Setter @Getter private static String holdUser, holdUserId;
    //@Setter @Getter private static String userId;
    
    @Setter @Getter private static boolean isLoginExamtaker = false;
    @Setter @Getter private static boolean isLoginProctor = false;
    @Setter @Getter private static boolean isLoginAssessor = false;
    
    @Setter @Getter private static String c;

    ExamTakerDAO examtakerDAO = new ExamTakerDAO();
    ProctorDAO proctorDAO = new ProctorDAO();
    AssessorDAO assessorDAO = new AssessorDAO();
    PersonDAO personDAO = new PersonDAO();

    public LoginBean() {
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
    private void doRedirect2(String url) {
        try {
            personDAO.updateCheckkColumn2(userId);
             if(c.equals("examtaker")){
            this.isLoginExamtaker = false;
        }
        else if(c.equals("proctor")){
            this.isLoginProctor = false;
        }
        else if(c.equals("proctor")) {
            this.isLoginAssessor = false;
        }
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().redirect(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verifyExamtaker() {
        HttpSession hs = Util.getSession();
        String d = String.valueOf(hs.getAttribute("userid"));
        System.out.println("gggggg " + d);
        //personDAO.updateCheckkColumn2(userId);
        if (!this.isLoginExamtaker) {
            //return "index?faces-redirect=true";
            doRedirect2("index.xhtml");
        }
//        else{
//         //   return "examtaker_dashboard?faces-redirect=true";
//         examtakerDAO.updateCheckkColumn(userId);
//        }
    }
    
    public void verifyProctor() {
        HttpSession hs = Util.getSession();
        String d = String.valueOf(hs.getAttribute("userid"));
        System.out.println("gggggg " + d);
        //personDAO.updateCheckkColumn2(userId);
        if (!this.isLoginProctor) {
            //return "index?faces-redirect=true";
            doRedirect2("index.xhtml");
        }
        

    }
    
    public void verifyAssessor() {
       HttpSession hs = Util.getSession();
        String d = String.valueOf(hs.getAttribute("userid"));
        System.out.println("gggggg " + d);
        //personDAO.updateCheckkColumn2(userId);
        if (!this.isLoginAssessor) {
            //return "index?faces-redirect=true";
            doRedirect2("index.xhtml");
        }

    }
    
    public void verifyCheck_For_WebCam() {
        if (CheckForWebCam.getFf().equals("")) {
            //return "index?faces-redirect=true";
            doRedirect("index.xhtml");
        }

    }

    public void login() {

        if (!(userId.equals("") && password.equals("") && user_reg1.equals("")) || !(userId.equals("") || password.equals("") || user_reg1.equals(""))) {
            System.out.println("ghgh " + user_reg1);
            c = user_reg1;
             
            if (c.equals("examtaker")) {
                String u = examtakerDAO.doLogin(userId, password, user_reg1);
                if (!u.equals("")){
                    userId = u;
                    personDAO.updateCheckkColumn(userId);
                    HttpSession hs = Util.getSession();
                    hs.setAttribute("userid", userId);
                    String h = String.valueOf(hs.getAttribute("userid"));
                    System.out.println("hhhh " + h);
                    this.isLoginExamtaker = true;
                    //return "examtaker_dashboard?faces-redirect=true";
                    doRedirect("examtaker_dashboard.xhtml");
                }
                else{
                    addMessage("please enter a valid userid, password and usertype");
                }
            } else if (c.equals("proctor")) {
                String u = proctorDAO.doLogin(userId, password, user_reg1);
                if(!u.equals("")){
                    personDAO.updateCheckkColumn(userId);
                    HttpSession hs = Util.getSession();
                    hs.setAttribute("userid", userId);
                    String h = String.valueOf(hs.getAttribute("userid"));
                    System.out.println("hhhh " + h);
                    this.isLoginProctor = true;
                    //return "examtaker_dashboard?faces-redirect=true";
                    doRedirect("proctor_dashboard.xhtml");
                }
                else{
                    addMessage("please enter a valid userid, password and usertype");
                }
            } else if (c.equals("assessor")) {
                String u = assessorDAO.doLogin(userId, password, user_reg1);
                if(!u.equals("")){
                    personDAO.updateCheckkColumn(userId);
                    HttpSession hs = Util.getSession();
                    hs.setAttribute("userid", userId);
                    String h = String.valueOf(hs.getAttribute("userid"));
                    System.out.println("hhhh " + h);
                    this.isLoginAssessor = true;
                    //return "examtaker_dashboard?faces-redirect=true";
                    doRedirect("assessor_dashboard.xhtml");
                }
                else{
                    addMessage("please enter a valid userid, password and usertype");
                }
            } else {
                addMessage("please enter correct userId, password and user type");
                // return "index?faces-redirect=true";
            }
        } else {
            addMessage("please enter correct userId, password and user type");
        }
        //return "";
    }

    public void logout() {
        HttpSession hs = Util.getSession();
        hs.setAttribute("userid", "");
        personDAO.updateCheckkColumn2(userId);
        
        hs.invalidate();
        // String h = String.valueOf(hs.getAttribute("userid"));
        //System.out.println("hhhh " + h );
        if(c.equals("examtaker")){
            this.isLoginExamtaker = false;
        }
        else if(c.equals("proctor")){
            this.isLoginProctor = false;
        }
        else if(c.equals("proctor")) {
            this.isLoginAssessor = false;
        }
        doRedirect("index.xhtml");
        //return "index?faces-redirect=true";
    }
    public void logout2() {
        HttpSession hs = Util.getSession();
        hs.setAttribute("userid", "");
        personDAO.updateCheckkColumn2(userId);
        
        hs.invalidate();
       
        if(c.equals("examtaker")){
            this.isLoginExamtaker = false;
        }
       
    }

//    public String login(){
//        System.out.println("rrrrrrrrrrrr");
//        //System.out.println("this is" + pp);
//        return "1";
//    }
    //go to registran page base on type of user
    public String gotoRegistrationPage() {

        System.out.println(user_reg);

        if (user_reg.equals("examtaker-reg")) {
            holdUser = user_reg;
            return "check_for_webcam?faces-redirect=true";
            // doRedirect("check_for_webcam.xhtml");
        } else if (user_reg.equals("proctor-reg")) {
            holdUser = user_reg;
            return "check_for_webcam?faces-redirect=true";
        } else {

            return user_reg + "?faces-redirect=true";
        }
        // doRedirect(user_reg);
        //}

    }

    //do redirect
//    public void doRedirect(String url){
//        try{
//            FacesContext fc = FacesContext.getCurrentInstance();
//            fc.getExternalContext().redirect(url);
//        }
//        catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage("message", message);
    }
}
