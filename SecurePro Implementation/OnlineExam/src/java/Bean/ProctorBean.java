/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Tunde
 */
@Named(value = "proctorBean")
@ViewScoped
public class ProctorBean implements Serializable{

    /**
     * Creates a new instance of ProctorBean
     */
    public ProctorBean() {
    }
    
    public void startTeamViewer(){
         try{
            System.out.println("Opening TeamViwer");
            Runtime runtime = Runtime.getRuntime();
            //Process process = runtime.exec("C:\\Program Files (x86)\\Notepad++\\notepad++.exe");
            Process process = runtime.exec("C:\\Program Files (x86)\\TeamViewer\\TeamViewer.exe");
            try{
                Thread.sleep(5000);
                        
            }
            catch(Exception e){
                e.printStackTrace();
            }
            System.out.println("Closing Teamviewer");
            process.destroy();
            runtime.exit(0);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
