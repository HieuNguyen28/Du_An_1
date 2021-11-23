/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Helper;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Laxus
 */
public class Mgsbox {
    public static void alert(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message,
                "Pharmacy Management System", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean comfirm(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message,
                "Pharmacy Management System", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }

    public static String prompt(Component parent, String message) {
        return JOptionPane.showInputDialog(parent, message, "Pharmacy Management System", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void error(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Pharmacy Management System", JOptionPane.ERROR_MESSAGE);
    }
}
