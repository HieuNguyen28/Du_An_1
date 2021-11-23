/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Helper;

import Model.Employee;
import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author LENOVO
 */
public class Image_Auth {

    public static final Image APP_ICON;
    public static final ImageIcon APP_ICON_1;

    static {
        String file = "/Icons/fpt.png";
        APP_ICON = new ImageIcon(Image_Auth.class.getResource(file)).getImage();
        APP_ICON_1 = new ImageIcon(Image_Auth.class.getResource(file));
    }

    public static boolean saveImage(File file) {
        File dir = new File("Image");

        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, file.getName());
        try {
            Path source = Paths.get(file.getAbsolutePath());
            Path destination = Paths.get(newFile.getAbsolutePath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static ImageIcon readImage(String fileName) {
        File path = new File("Image", fileName);
        return new ImageIcon(new ImageIcon(path.getAbsolutePath()).getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT));
    }

    public static Employee USER = null;

    public static void logOff() {
        Image_Auth.USER = null;
    }

    public static boolean authenticated() {
        return Image_Auth.USER != null;
    }
}
