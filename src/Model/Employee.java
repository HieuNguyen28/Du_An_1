/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class Employee {
    private String epeID;
    private String epeName;
    private boolean epeIsGender;
    private Date    epeDayOfBirth;
    private String epeAddress;
    private String epeCCCD;
    private String epeNumberPhone;
    private String epeEmail;
    private String epeImage;
    private boolean epeIsRole;
    private String epeUserName;
    private String epePassword;
    private Date epeAccountCreationDate;

    public Employee() {
    }

    public Employee(String epeID, String epeName, boolean epeIsGender, Date epeDayOfBirth, String epeAddress, String epeCCCD, String epeNumberPhone, String epeEmail, String epeImage, boolean epeIsRole, String epeUserName, String epePassword, Date epeAccountCreationDate) {
        this.epeID = epeID;
        this.epeName = epeName;
        this.epeIsGender = epeIsGender;
        this.epeDayOfBirth = epeDayOfBirth;
        this.epeAddress = epeAddress;
        this.epeCCCD = epeCCCD;
        this.epeNumberPhone = epeNumberPhone;
        this.epeEmail = epeEmail;
        this.epeImage = epeImage;
        this.epeIsRole = epeIsRole;
        this.epeUserName = epeUserName;
        this.epePassword = epePassword;
        this.epeAccountCreationDate = epeAccountCreationDate;
    }

    public String getEpeID() {
        return epeID;
    }

    public void setEpeID(String epeID) {
        this.epeID = epeID;
    }

    public String getEpeName() {
        return epeName;
    }

    public void setEpeName(String epeName) {
        this.epeName = epeName;
    }

    public boolean isEpeIsGender() {
        return epeIsGender;
    }

    public void setEpeIsGender(boolean epeIsGender) {
        this.epeIsGender = epeIsGender;
    }

    public Date getEpeDayOfBirth() {
        return epeDayOfBirth;
    }

    public void setEpeDayOfBirth(Date epeDayOfBirth) {
        this.epeDayOfBirth = epeDayOfBirth;
    }

    public String getEpeAddress() {
        return epeAddress;
    }

    public void setEpeAddress(String epeAddress) {
        this.epeAddress = epeAddress;
    }

    public String getEpeCCCD() {
        return epeCCCD;
    }

    public void setEpeCCCD(String epeCCCD) {
        this.epeCCCD = epeCCCD;
    }

    public String getEpeNumberPhone() {
        return epeNumberPhone;
    }

    public void setEpeNumberPhone(String epeNumberPhone) {
        this.epeNumberPhone = epeNumberPhone;
    }

    public String getEpeEmail() {
        return epeEmail;
    }

    public void setEpeEmail(String epeEmail) {
        this.epeEmail = epeEmail;
    }

    public String getEpeImage() {
        return epeImage;
    }

    public void setEpeImage(String epeImage) {
        this.epeImage = epeImage;
    }

    public boolean isEpeIsRole() {
        return epeIsRole;
    }

    public void setEpeIsRole(boolean epeIsRole) {
        this.epeIsRole = epeIsRole;
    }

    public String getEpeUserName() {
        return epeUserName;
    }

    public void setEpeUserName(String epeUserName) {
        this.epeUserName = epeUserName;
    }

    public String getEpePassword() {
        return epePassword;
    }

    public void setEpePassword(String epePassword) {
        this.epePassword = epePassword;
    }

    public Date getEpeAccountCreationDate() {
        return epeAccountCreationDate;
    }

    public void setEpeAccountCreationDate(Date epeAccountCreationDate) {
        this.epeAccountCreationDate = epeAccountCreationDate;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "epeID=" + epeID + ", epeName=" + epeName + ", epeIsGender=" + epeIsGender + ", epeDayOfBirth=" + epeDayOfBirth + ", epeAddress=" + epeAddress + ", epeCCCD=" + epeCCCD + ", epeNumberPhone=" + epeNumberPhone + ", epeEmail=" + epeEmail + ", epeImage=" + epeImage + ", epeIsRole=" + epeIsRole + ", epeUserName=" + epeUserName + ", epePassword=" + epePassword + ", epeAccountCreationDate=" + epeAccountCreationDate + '}';
    }
    
}
