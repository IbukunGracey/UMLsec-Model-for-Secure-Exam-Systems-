/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Tunde
 */
public class Exam extends ExamQuestions{
    @Getter @Setter private static String examId;
    @Getter @Setter private static String examTitle;
    @Getter @Setter private static String examCode;
    @Getter @Setter private static String duration;
    //@Getter @Setter private ExamQuestions examQuestions;
}
