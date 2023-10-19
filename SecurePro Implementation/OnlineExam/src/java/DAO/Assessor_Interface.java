/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Tunde
 */
public interface Assessor_Interface {
 
    public boolean createAccount(String email, String name, String address, String phonenumber,
                              String password, String image);
}
