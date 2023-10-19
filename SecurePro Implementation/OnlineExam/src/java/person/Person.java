/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package person;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Tunde
 */
public class Person {
    @Getter @Setter private static String firstName;
    @Getter @Setter private static String lastName;
    @Getter @Setter private static String address;
    @Getter @Setter private static String password;
    @Getter @Setter private static String email;
    @Getter @Setter private static String userType;
}
