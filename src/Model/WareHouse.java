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
public class WareHouse {
    private String whBatchID;
    private String whEmployeeID;
    private Date whImportDay;
    private String whDistributor;
    private Date whDateOfManufacture;
    private Date whExpiryDate;
    private int whQuantity;
    private int whRemainingAmout;
    private String whNote;

    public WareHouse() {
    }

    public WareHouse(String whBatchID, String whEmployeeID, Date whImportDay, String whDistributor, Date whDateOfManufacture, Date whExpiryDate, int whQuantity, int whRemainingAmout, String whNote) {
        this.whBatchID = whBatchID;
        this.whEmployeeID = whEmployeeID;
        this.whImportDay = whImportDay;
        this.whDistributor = whDistributor;
        this.whDateOfManufacture = whDateOfManufacture;
        this.whExpiryDate = whExpiryDate;
        this.whQuantity = whQuantity;
        this.whRemainingAmout = whRemainingAmout;
        this.whNote = whNote;
    }

    public String getWhBatchID() {
        return whBatchID;
    }

    public void setWhBatchID(String whBatchID) {
        this.whBatchID = whBatchID;
    }

    public String getWhEmployeeID() {
        return whEmployeeID;
    }

    public void setWhEmployeeID(String whEmployeeID) {
        this.whEmployeeID = whEmployeeID;
    }

    public Date getWhImportDay() {
        return whImportDay;
    }

    public void setWhImportDay(Date whImportDay) {
        this.whImportDay = whImportDay;
    }

    public String getWhDistributor() {
        return whDistributor;
    }

    public void setWhDistributor(String whDistributor) {
        this.whDistributor = whDistributor;
    }

    public Date getWhDateOfManufacture() {
        return whDateOfManufacture;
    }

    public void setWhDateOfManufacture(Date whDateOfManufacture) {
        this.whDateOfManufacture = whDateOfManufacture;
    }

    public Date getWhExpiryDate() {
        return whExpiryDate;
    }

    public void setWhExpiryDate(Date whExpiryDate) {
        this.whExpiryDate = whExpiryDate;
    }

    public int getWhQuantity() {
        return whQuantity;
    }

    public void setWhQuantity(int whQuantity) {
        this.whQuantity = whQuantity;
    }

    public int getWhRemainingAmout() {
        return whRemainingAmout;
    }

    public void setWhRemainingAmout(int whRemainingAmout) {
        this.whRemainingAmout = whRemainingAmout;
    }

    public String getWhNote() {
        return whNote;
    }

    public void setWhNote(String whNote) {
        this.whNote = whNote;
    }

    @Override
    public String toString() {
        return "WareHouse{" + "whBatchID=" + whBatchID + ", whEmployeeID=" + whEmployeeID + ", whImportDay=" + whImportDay + ", whDistributor=" + whDistributor + ", whDateOfManufacture=" + whDateOfManufacture + ", whExpiryDate=" + whExpiryDate + ", whQuantity=" + whQuantity + ", whRemainingAmout=" + whRemainingAmout + ", whNote=" + whNote + '}';
    }
    
    
}
