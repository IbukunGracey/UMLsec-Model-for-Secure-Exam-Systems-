/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import interfaces.IExamTaker;
import iusers.Examtaker;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.imageio.stream.FileImageOutputStream;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.CaptureEvent;
import person.ExamTaker;
import person.Person;

/**
 *
 * @author Tunde
 */
@Named(value = "examtaker_Reg")
@ViewScoped
public class Examtaker_Reg implements Serializable{
    
    /**
     * Creates a new instance of Examtaker_Reg
     */
    
    
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
    @Getter @Setter private String address;
    @Getter @Setter private String password;
    @Getter @Setter private String email;
    @Getter @Setter private String examTakerId; //e.g adam1212
    @Getter @Setter private String userType;
    //@Getter @Setter private String photoId;
   

    ExamTaker examtaker = new ExamTaker();
    public Examtaker_Reg() {
      
    }
    
    
    
   
    public String createAccount(){
        examtaker.setExamTakerId(examTakerId);
        examtaker.setFirstName(firstName);
        examtaker.setLastName(lastName);
        examtaker.setAddress(address);
        examtaker.setPassword(password);
        examtaker.setEmail(email);
        examtaker.setUserType("examtaker");
        System.out.println(getExamTakerId() + " " + examtaker.getAddress());
     String dd = new Examtaker().createAccount(examtaker);
     if(dd.equals("yes")){
         setExamTakerId("");
         setAddress("");
         setEmail("");
         setLastName("");
         setFirstName("");
         setPassword("");
         setUserType("");
     }
     //d.createAccount(examtaker);
    return "";
      }
    
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
