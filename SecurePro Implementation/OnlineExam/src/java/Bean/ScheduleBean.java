/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.Db;
import iusers.Examtaker;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;
//import org.primefaces.event.SelectEvent;

//import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import person.ExamTaker;

/**
 *
 * @author Tunde
 */
@Named(value = "scheduleBean")
@ViewScoped
public class ScheduleBean implements Serializable {

    Connection conn;
    PreparedStatement prepare;
    ResultSet rset;

    /**
     * Creates a new instance of ScheduleBean
     */
    @Getter
    @Setter
    private String examId;
    @Getter
    @Setter
    private String date;
    @Getter
    @Setter
    private String time;
    @Getter
    @Setter
    private String checkExamStatus;
    @Getter
    @Setter
    private ArrayList<SelectItem> categoryExamName = new ArrayList<>();
    @Getter
    @Setter
    private Calendar dateAndTime;

    Examtaker examtaker = new Examtaker();

    public ScheduleBean() {
    }

    public ArrayList<SelectItem> getCategoryExamName() {

        try {
            ArrayList<SelectItem> exName = new ArrayList<>();
            conn = Db.connect();
            String query = "select examcode from examdetails";
            prepare = conn.prepareStatement(query);
            rset = prepare.executeQuery();
            categoryExamName.removeAll(categoryExamName);

            while (rset.next()) {
                categoryExamName.add(new SelectItem(rset.getString(1)));
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
        return categoryExamName;
    }

    public void scheduleExam() {
        HttpSession hs = Util.getSession();
        String userId = String.valueOf(hs.getAttribute("userid"));
        String ch = examtaker.scheduleExam(userId, examId, date, time, checkExamStatus);
        if (ch.equals("yes")) {
            setExamId("");
            setDate("");
            setTime("");
        }
    }

    //private Date date11;
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

//    public void click() {
//        PrimeFaces.current().ajax().update("form:display");
//        PrimeFaces.current().executeScript("PF('dlg').show()");
//    }
}
