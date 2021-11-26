/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Helper;

import java.awt.Color;
import static java.awt.Color.pink;
import static java.awt.Color.white;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class BackgroundC1 {

    public static void ChangeTxt(JTextField txt) {
        txt.setBackground(Color.decode("#9badf2"));
    }
    
    public static void ChangeBtn(JButton btn) {
        btn.setBackground(Color.decode("#9badf2"));
       
    }
    
    public static void ChangeLbl(JLabel lbl) {
//        lbl.setForeground(Color.BLACK);
    }
    
     public static void ChangeRdo(JRadioButton rdo) {
        rdo.setBackground(Color.decode("#9badf2"));
    }
}
