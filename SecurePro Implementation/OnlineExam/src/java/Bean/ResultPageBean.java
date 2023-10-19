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
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Tunde
 */
@Named(value = "resultPageBean")
@ViewScoped
public class ResultPageBean implements Serializable{

    /**
     * Creates a new instance of ResultPageBean
     */
    
    @Setter @Getter private String examCode;
    Connection conn;
    PreparedStatement prepare;
    ResultSet rset;
    public ResultPageBean() {
    }
    
    
    public void clearAll(){
        examCode = FaceAuthentification2Bean.storeExamCode;
        //deleteAll1();
        ExamPageBean.cal();
//        new ExamPageBean().deleteAll1();
//        new ExamPageBean().deleteAll2();
    
        
    }
    
    public void deleteAll1(){
         try{
        HttpSession hs = Util.getSession();
         conn = Db.connect();
         String query = "delete from storequestionnumber where userid="+ "'" + String.valueOf(hs.getAttribute("userid")) + "'"; 
         prepare = conn.prepareStatement(query);
        
         //prepare.setString(2, String.valueOf(hs.getAttribute("userid")));
         prepare.executeUpdate();
            System.out.println("updated successfullyj");
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
    
    
}
