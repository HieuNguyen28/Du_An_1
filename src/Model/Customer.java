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
public class Customer {
    private String ctmID;
    private String ctmName;
    private String ctmNumberPhone;
    private Date ctmStartDateBuy;
    private Date ctmLastDateBuy;
    private int ctmTimesOfBuy;
    private double ctmTotalMoneyBought;

    public Customer() {
    }

    public Customer(String ctmID, String ctmName, String ctmNumberPhone, Date ctmStartDateBuy, Date ctmLastDateBuy, int ctmTimesOfBuy, double ctmTotalMoneyBought) {
        this.ctmID = ctmID;
        this.ctmName = ctmName;
        this.ctmNumberPhone = ctmNumberPhone;
        this.ctmStartDateBuy = ctmStartDateBuy;
        this.ctmLastDateBuy = ctmLastDateBuy;
        this.ctmTimesOfBuy = ctmTimesOfBuy;
        this.ctmTotalMoneyBought = ctmTotalMoneyBought;
    }

    public String getCtmID() {
        return ctmID;
    }

    public void setCtmID(String ctmID) {
        this.ctmID = ctmID;
    }

    public String getCtmName() {
        return ctmName;
    }

    public void setCtmName(String ctmName) {
        this.ctmName = ctmName;
    }

    public String getCtmNumberPhone() {
        return ctmNumberPhone;
    }

    public void setCtmNumberPhone(String ctmNumberPhone) {
        this.ctmNumberPhone = ctmNumberPhone;
    }

    public Date getCtmStartDateBuy() {
        return ctmStartDateBuy;
    }

    public void setCtmStartDateBuy(Date ctmStartDateBuy) {
        this.ctmStartDateBuy = ctmStartDateBuy;
    }

    public Date getCtmLastDateBuy() {
        return ctmLastDateBuy;
    }

    public void setCtmLastDateBuy(Date ctmLastDateBuy) {
        this.ctmLastDateBuy = ctmLastDateBuy;
    }

    public int getCtmTimesOfBuy() {
        return ctmTimesOfBuy;
    }

    public void setCtmTimesOfBuy(int ctmTimesOfBuy) {
        this.ctmTimesOfBuy = ctmTimesOfBuy;
    }

    public double getCtmTotalMoneyBought() {
        return ctmTotalMoneyBought;
    }

    public void setCtmTotalMoneyBought(double ctmTotalMoneyBought) {
        this.ctmTotalMoneyBought = ctmTotalMoneyBought;
    }

    @Override
    public String toString() {
        return "Customer{" + "ctmID=" + ctmID + ", ctmName=" + ctmName + ", ctmNumberPhone=" + ctmNumberPhone + ", ctmStartDateBuy=" + ctmStartDateBuy + ", ctmLastDateBuy=" + ctmLastDateBuy + ", ctmTimesOfBuy=" + ctmTimesOfBuy + ", ctmTotalMoneyBought=" + ctmTotalMoneyBought + '}';
    }
    
    
}
