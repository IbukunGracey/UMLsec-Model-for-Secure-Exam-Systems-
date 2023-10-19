/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import exam.Exam;
import person.Assessor;

/**
 *
 * @author Tunde
 */
public interface IAssessor {
    
    public String createAccount(Assessor assessor);
    public String createExamDetails(Exam exam);
    public void createExamInstructionsAndRules();
    public String setExamQuestions(String examCode);
    public void assignExamtakerToProctor();
}
