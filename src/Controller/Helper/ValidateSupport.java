/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Helper;

import static java.awt.Color.pink;
import static java.awt.Color.white;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author LENOVO
 */
public class ValidateSupport {

    public static boolean isNull(JTextField txt) {
        return txt.getText().trim().isEmpty();
    }

    public static boolean isNull(JTextPane txp) {
        return txp.getText().trim().isEmpty();
    }

    public static boolean isNumber(JTextField txt) {
        if (!txt.getText().trim().matches("(\\d+.\\d+)|\\d+")) {
            Mgsbox.error(txt.getRootPane(), "Please enter a number....");
            return false;
        } else {
            return true;
        }
    }
    public static boolean checkSDT(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = "(086|096|097|098|032|033|034|035|036|037|038|039|089|090|093|070|079|077|078|076|088|091|094|083|084|085|081|082|092|056|058|099|059)[0-9]{7}";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(pink);
            Mgsbox.error(txt.getRootPane(),"Number phone invalid.");
            return false;
        }
    }

    public static boolean checkEmail(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = "^[a-zA-Z][a-zA-Z0-9_\\.]{2,32}@[a-zA-Z0-9]{2,10}(\\.[a-zA-Z0-9]{2,4}){1,2}$";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(pink);
            Mgsbox.error(txt.getRootPane(), "Email invalid...");
            return false;
        }
    }
}
