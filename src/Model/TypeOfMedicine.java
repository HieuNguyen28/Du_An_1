/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author LENOVO
 */
public class TypeOfMedicine {
    private String tomID;
    private String tomName;
    private boolean tomIsPrecription;
    private String tomUserObject;
    private String tomNote;

    public TypeOfMedicine() {
    }

    public TypeOfMedicine(String tomID, String tomName, boolean tomIsPrecription, String tomUserObject, String tomNote) {
        this.tomID = tomID;
        this.tomName = tomName;
        this.tomIsPrecription = tomIsPrecription;
        this.tomUserObject = tomUserObject;
        this.tomNote = tomNote;
    }

    public String getTomID() {
        return tomID;
    }

    public void setTomID(String tomID) {
        this.tomID = tomID;
    }

    public String getTomName() {
        return tomName;
    }

    public void setTomName(String tomName) {
        this.tomName = tomName;
    }

    public boolean isTomIsPrecription() {
        return tomIsPrecription;
    }

    public void setTomIsPrecription(boolean tomIsPrecription) {
        this.tomIsPrecription = tomIsPrecription;
    }

    public String getTomUserObject() {
        return tomUserObject;
    }

    public void setTomUserObject(String tomUserObject) {
        this.tomUserObject = tomUserObject;
    }

    public String getTomNote() {
        return tomNote;
    }

    public void setTomNote(String tomNote) {
        this.tomNote = tomNote;
    }

    @Override
    public String toString() {
        return "TypeOfMedicine{" + "tomID=" + tomID + ", tomName=" + tomName + ", tomIsPrecription=" + tomIsPrecription + ", tomUserObject=" + tomUserObject + ", tomNote=" + tomNote + '}';
    }

   
    
}
