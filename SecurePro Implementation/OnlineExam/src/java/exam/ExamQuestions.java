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
public class ExamQuestions {
    @Getter @Setter private static String counter;
    @Getter @Setter private static String question_body;
    @Getter @Setter private static String optionA;
    @Getter @Setter private static String optionB;
    @Getter @Setter private static String optionC;
    @Getter @Setter private static String optionD;
    @Getter @Setter private static String correctOption;
}
