/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import person.Assessor;
import person.Proctor;

/**
 *
 * @author Tunde
 */
public interface IProctor {
    public String createAccount(Proctor proc);
    public void superviseExam();
}
