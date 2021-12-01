/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Helper;

import Model.Employee;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
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

    public static ImageIcon readImage(File file, int width, int height) {
        return new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    public static Employee USER = null;

    public static void logOff() {
        Image_Auth.USER = null;
    }

    public static boolean authenticated() {
        return Image_Auth.USER != null;
    }

    public static BufferedImage getScreenShot(Component cpn) {
        BufferedImage image = new BufferedImage(cpn.getWidth(), cpn.getHeight(), BufferedImage.TYPE_INT_RGB);
        cpn.paint(image.getGraphics());
        return image;
    }

    public static void saveScreenShot(Component cpn, String filename) throws IOException {
        BufferedImage img = getScreenShot(cpn);
        ImageIO.write(img, "png", new File(filename));
    }
    
    public static File bufferedImageToFile(BufferedImage file, String fileName){
        File dir = new File("cache");
        
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, fileName + ".png");
        try {
            ImageIO.write(file, "PNG", newFile);
        } catch (IOException ex) {
        }
        return newFile;
    }
    
    public static File imageCameraToFile(BufferedImage file, String fileName){
        File dir = new File("Image");
        
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, fileName + ".png");
        try {
            ImageIO.write(file, "PNG", newFile);
        } catch (IOException ex) {
        }
        return newFile;
    }
    
    public static boolean saveVoucher(BufferedImage file, String fileName) {
        File dir = new File("Vouchers");

        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, fileName + ".png");
        try {
            ImageIO.write(file, "PNG", newFile);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

}
