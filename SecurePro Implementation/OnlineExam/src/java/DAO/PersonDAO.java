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
public class PersonDAO {
     Connection conn;
    PreparedStatement prepare;
    ResultSet rset;
    
  public void updateCheckkColumn(String userId) {
        try {
            int ch = 0;
            conn = Db.connect();
            String query = "update login_table set checkk=? where userid=" + "'" + userId + "'";
            prepare = conn.prepareStatement(query);
            prepare.setString(1, "yes");

            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                prepare.close();
                // rset.close();
                conn.close();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }
  
  
    public void updateCheckkColumn2(String userId) {
        try {
            int ch = 0;
            conn = Db.connect();
            String query = "update login_table set checkk=? where userid=" + "'" + userId + "'";
            prepare = conn.prepareStatement(query);
            prepare.setString(1, "no");

            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
