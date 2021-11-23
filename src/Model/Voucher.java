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
public class Voucher {
    private String vcID;
    private String vcMedicineID;
    private String vcEmployeeAddVoucher;
    private Date vcStartDate;
    private Date vcEndDate;
    private double vcTotalBillCanAdd;
    private double vcPriceDiscount;

    public Voucher() {
    }

    public Voucher(String vcID, String vcMedicineID, String vcEmployeeAddVoucher, Date vcStartDate, Date vcEndDate, double vcTotalBillCanAdd, double vcPriceDiscount) {
        this.vcID = vcID;
        this.vcMedicineID = vcMedicineID;
        this.vcEmployeeAddVoucher = vcEmployeeAddVoucher;
        this.vcStartDate = vcStartDate;
        this.vcEndDate = vcEndDate;
        this.vcTotalBillCanAdd = vcTotalBillCanAdd;
        this.vcPriceDiscount = vcPriceDiscount;
    }

    public String getVcID() {
        return vcID;
    }

    public void setVcID(String vcID) {
        this.vcID = vcID;
    }

    public String getVcMedicineID() {
        return vcMedicineID;
    }

    public void setVcMedicineID(String vcMedicineID) {
        this.vcMedicineID = vcMedicineID;
    }

    public String getVcEmployeeAddVoucher() {
        return vcEmployeeAddVoucher;
    }

    public void setVcEmployeeAddVoucher(String vcEmployeeAddVoucher) {
        this.vcEmployeeAddVoucher = vcEmployeeAddVoucher;
    }

    public Date getVcStartDate() {
        return vcStartDate;
    }

    public void setVcStartDate(Date vcStartDate) {
        this.vcStartDate = vcStartDate;
    }

    public Date getVcEndDate() {
        return vcEndDate;
    }

    public void setVcEndDate(Date vcEndDate) {
        this.vcEndDate = vcEndDate;
    }

    public double getVcTotalBillCanAdd() {
        return vcTotalBillCanAdd;
    }

    public void setVcTotalBillCanAdd(double vcTotalBillCanAdd) {
        this.vcTotalBillCanAdd = vcTotalBillCanAdd;
    }

    public double getVcPriceDiscount() {
        return vcPriceDiscount;
    }

    public void setVcPriceDiscount(double vcPriceDiscount) {
        this.vcPriceDiscount = vcPriceDiscount;
    }

    @Override
    public String toString() {
        return "Voucher{" + "vcID=" + vcID + ", vcMedicineID=" + vcMedicineID + ", vcEmployeeAddVoucher=" + vcEmployeeAddVoucher + ", vcStartDate=" + vcStartDate + ", vcEndDate=" + vcEndDate + ", vcTotalBillCanAdd=" + vcTotalBillCanAdd + ", vcPriceDiscount=" + vcPriceDiscount + '}';
    }
    
    
}
