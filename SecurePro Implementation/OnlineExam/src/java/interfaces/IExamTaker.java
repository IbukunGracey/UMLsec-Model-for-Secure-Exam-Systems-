/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import person.ExamTaker;

/**
 *
 * @author Tunde
 */
public interface IExamTaker {
    public String createAccount(ExamTaker examtaker);
    public void TakeExam();
    public String scheduleExam(String userId, String examid, String date, String time, String checkExamStatus);
    public void enrollForBiometric();
    public void takeSystemTest();
}