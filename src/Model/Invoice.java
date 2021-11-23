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
public class Invoice {
    private String ivID;
    private String ivCumtomerID;
    private String ivEmployeeSale;
    private String ivVoucherID;
    private String ivName;
    private Date ivTimeBuy;
    private double ivTotalPricesBefore;
    private double ivPriceDiscount;
    private double ivToltalPricesAfter;

    public Invoice() {
    }

    public Invoice(String ivID, String ivCumtomerID, String ivEmployeeSale, String ivVoucherID, String ivName, Date ivTimeBuy, double ivTotalPricesBefore, double ivPriceDiscount, double ivToltalPricesAfter) {
        this.ivID = ivID;
        this.ivCumtomerID = ivCumtomerID;
        this.ivEmployeeSale = ivEmployeeSale;
        this.ivVoucherID = ivVoucherID;
        this.ivName = ivName;
        this.ivTimeBuy = ivTimeBuy;
        this.ivTotalPricesBefore = ivTotalPricesBefore;
        this.ivPriceDiscount = ivPriceDiscount;
        this.ivToltalPricesAfter = ivToltalPricesAfter;
    }

    public String getIvID() {
        return ivID;
    }

    public void setIvID(String ivID) {
        this.ivID = ivID;
    }

    public String getIvCumtomerID() {
        return ivCumtomerID;
    }

    public void setIvCumtomerID(String ivCumtomerID) {
        this.ivCumtomerID = ivCumtomerID;
    }

    public String getIvEmployeeSale() {
        return ivEmployeeSale;
    }

    public void setIvEmployeeSale(String ivEmployeeSale) {
        this.ivEmployeeSale = ivEmployeeSale;
    }

    public String getIvVoucherID() {
        return ivVoucherID;
    }

    public void setIvVoucherID(String ivVoucherID) {
        this.ivVoucherID = ivVoucherID;
    }

    public String getIvName() {
        return ivName;
    }

    public void setIvName(String ivName) {
        this.ivName = ivName;
    }

    public Date getIvTimeBuy() {
        return ivTimeBuy;
    }

    public void setIvTimeBuy(Date ivTimeBuy) {
        this.ivTimeBuy = ivTimeBuy;
    }

    public double getIvTotalPricesBefore() {
        return ivTotalPricesBefore;
    }

    public void setIvTotalPricesBefore(double ivTotalPricesBefore) {
        this.ivTotalPricesBefore = ivTotalPricesBefore;
    }

    public double getIvPriceDiscount() {
        return ivPriceDiscount;
    }

    public void setIvPriceDiscount(double ivPriceDiscount) {
        this.ivPriceDiscount = ivPriceDiscount;
    }

    public double getIvToltalPricesAfter() {
        return ivToltalPricesAfter;
    }

    public void setIvToltalPricesAfter(double ivToltalPricesAfter) {
        this.ivToltalPricesAfter = ivToltalPricesAfter;
    }

    @Override
    public String toString() {
        return "Invoice{" + "ivID=" + ivID + ", ivCumtomerID=" + ivCumtomerID + ", ivEmployeeSale=" + ivEmployeeSale + ", ivVoucherID=" + ivVoucherID + ", ivName=" + ivName + ", ivTimeBuy=" + ivTimeBuy + ", ivTotalPricesBefore=" + ivTotalPricesBefore + ", ivPriceDiscount=" + ivPriceDiscount + ", ivToltalPricesAfter=" + ivToltalPricesAfter + '}';
    }
    
    
}
