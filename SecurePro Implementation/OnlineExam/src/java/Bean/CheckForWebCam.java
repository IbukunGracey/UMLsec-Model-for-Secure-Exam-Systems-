/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.imageio.stream.FileImageOutputStream;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.primefaces.event.CaptureEvent;
import person.ExamTaker;

/**
 *
 * @author Tunde
 */
@Named(value = "checkForWebCam")
@ViewScoped
public class CheckForWebCam  implements Serializable{

    /**
     * Creates a new instance of CheckForWebCam
     */
    
    @Getter @Setter
    private   String filename ="no_image";
    @Getter @Setter
    private static String ff = "";
    
    public CheckForWebCam() {
    }

    
     private String getRandomImageName() {
        int i = (int) (Math.random() * 10000000);
         
        return String.valueOf(i);
    }
 
    
     
    public void oncapture(CaptureEvent captureEvent) {
        filename = getRandomImageName();
        byte[] data = captureEvent.getData();
 
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        //String newFileName = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "demo" +
          //                          File.separator + "images" + File.separator + "photocam" + File.separator + filename + ".jpeg";
         String newFileName = externalContext.getRealPath("") + File.separator + "images" + 
                 File.separator + "photocam" + File.separator + "examtaker" + File.separator + filename + ".jpeg";
         System.out.println(" this is the path to the image " + newFileName);
         System.out.println("the image is  " + filename);
         
        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        }
        catch(IOException e) {
            throw new FacesException("Error in writing captured image.", e);
        }
        String userFolder = (LoginBean.getHoldUser().equals("examtaker-reg"))? "examtaker" : "proctor";
        File sor = new File(newFileName);
        File des = new File("C:\\Users\\Tunde\\Documents\\NetBeansProjects\\OnlineExam\\web\\images\\photocam\\" + userFolder + "\\" + filename + ".jpeg");
       
        new ExamTaker().setPhotoId(String.valueOf(des));
//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("key", filename+".jpeg");
       //FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("pic", filename + ".jpeg", null);
       //Map<String, Object> requestCookieMap = FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap();
       // System.out.println(requestCookieMap.get("pic").toString()); 
       try {
            FileUtils.copyFile(sor, des);
        } catch (IOException ex) {
            Logger.getLogger(CheckForWebCam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //go to registran page base on type of user
    public String gotoRegistrationPage(){
        
        if(!filename.equals("no_image")){
            System.out.println("gg " + filename);
            ff = filename;
            //ff = "secured/examtaker-reg?faces-redirect=true";
            return LoginBean.getHoldUser()+ "?faces-redirect=true";
        }
        else{
               addMessage("please click on capture button");
       }
      return "";
    }
    
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage("mess", message);
    }
    
    
}
