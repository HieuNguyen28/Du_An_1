/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Helper;

import static java.awt.Color.pink;
import static java.awt.Color.white;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author LENOVO
 */
public class ValidateSupport {

    public static boolean isNull(JTextField txt) {
        txt.setBackground(white);
        if (txt.getText().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isNumber(JTextField txt) {
        if (!txt.getText().trim().matches("(\\d+.\\d+)|\\d+")) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkSDT(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = "(086|096|097|098|032|033|034|035|036|037|038|039|089|090|093|070|079|077|078|076|088|091|094|083|084|085|081|082|092|056|058|099|059)[0-9]{7}";
        return id.matches(rgx);
    }

    public static boolean isEmail(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = "^[a-zA-Z][a-zA-Z0-9_\\.]{2,32}@[a-zA-Z0-9]{2,10}(\\.[a-zA-Z0-9]{2,4}){1,2}$";
       return id.matches(rgx);
    }

    public static boolean checkEmployeeID(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = "[A-Z0-9]{5}";
         return id.matches(rgx);
    }

    public static boolean isCCCD(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = "[0-9]{12}";
        return id.matches(rgx);
    }
    
    public static boolean checkPrice(JTextField txt) {
        try {
            double hp = Double.parseDouble(txt.getText());
            if (hp >= 0) {
                return true;
            } 
                return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean checkRate(JTextField txt) {
        try {
            Float rt = Float.parseFloat(txt.getText());
            if (rt >= 0) {
                return true;
            } 
                return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public boolean isNullImage(JLabel label) {
        if (label.getToolTipText() != null) {
            return false;
        } else {
            Mgsbox.error(label.getRootPane(), "Image can't be empty");
            return true;
        }
    }

    
    
    public static boolean isNull(JTextField txt) {
        txt.setBackground(white);
        if (txt.getText().trim().length() > 0) {
            return false;
        } else {
            txt.setBackground(pink);
            return true;
        }
    }

    public static boolean checkPrice(JTextField txt) {
        try {
            float hp = Float.parseFloat(txt.getText());
            if (hp >= 0) {
                return true;
            } else {
                txt.setBackground(pink);
                return false;
            }
        } catch (NumberFormatException e) {
            txt.setBackground(pink);
        }
        return false;

    public static boolean isNull(JTextPane txp) {
        return txp.getText().trim().isEmpty();
    }
    
    public static boolean isNull(JTextArea txt) {
        return txt.getText().trim().isEmpty();
    }
    
    public static boolean isSeleted(JRadioButton rdo) {
        return rdo.isSelected();  

    }

}
