/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Tunde
 */
public class ProctorDAO {
 
    Connection conn;
    PreparedStatement prepare;
    ResultSet rset;

    //private String userId, password, userType;
    public String doLogin(String userId, String password, String userType) {
        try {
            int ch = 0;
            conn = Db.connect();
            String query = "select userid, password, userType from login_table where userid=? and password=? and userType=?";
            prepare = conn.prepareStatement(query);
            prepare.setString(1, userId);
            prepare.setString(2, password);
            prepare.setString(3, userType);

            rset = prepare.executeQuery();

            if (rset.next()) {
                ///System.out.println(rset.getString(1));

                return rset.getString(1);

            } else {
                //return "no";
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
       // return "no";
    return "";
    }
    
}
