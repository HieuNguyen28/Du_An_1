/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author LENOVO
 */
public class Medicine {
    private String mdcID;
    private String mdcTypeOfMedicineID;
    private String mdcBatchID;
    private String mdcProducerID;
    private String mdcName;
    private String mdcIngredient;
    private String mdcConcentration;
    private String mdcUnit;
    private String mdcUserManual;
    private double mdcPriceSale;
    private double mdcRate;
    private double mdcPriceImport;
    private String mdcImage;

    public Medicine() {
    }

    public Medicine(String mdcID, String mdcTypeOfMedicineID, String mdcBatchID, String mdcProducerID, String mdcName, String mdcIngredient, String mdcConcentration, String mdcUnit, String mdcUserManual, double mdcPriceSale, double mdcRate, double mdcPriceImport, String mdcImage) {
        this.mdcID = mdcID;
        this.mdcTypeOfMedicineID = mdcTypeOfMedicineID;
        this.mdcBatchID = mdcBatchID;
        this.mdcProducerID = mdcProducerID;
        this.mdcName = mdcName;
        this.mdcIngredient = mdcIngredient;
        this.mdcConcentration = mdcConcentration;
        this.mdcUnit = mdcUnit;
        this.mdcUserManual = mdcUserManual;
        this.mdcPriceSale = mdcPriceSale;
        this.mdcRate = mdcRate;
        this.mdcPriceImport = mdcPriceImport;
        this.mdcImage = mdcImage;
    }

    public String getMdcID() {
        return mdcID;
    }

    public void setMdcID(String mdcID) {
        this.mdcID = mdcID;
    }

    public String getMdcTypeOfMedicineID() {
        return mdcTypeOfMedicineID;
    }

    public void setMdcTypeOfMedicineID(String mdcTypeOfMedicineID) {
        this.mdcTypeOfMedicineID = mdcTypeOfMedicineID;
    }

    public String getMdcBatchID() {
        return mdcBatchID;
    }

    public void setMdcBatchID(String mdcBatchID) {
        this.mdcBatchID = mdcBatchID;
    }

    public String getMdcProducerID() {
        return mdcProducerID;
    }

    public void setMdcProducerID(String mdcProducerID) {
        this.mdcProducerID = mdcProducerID;
    }

    public String getMdcName() {
        return mdcName;
    }

    public void setMdcName(String mdcName) {
        this.mdcName = mdcName;
    }

    public String getMdcIngredient() {
        return mdcIngredient;
    }

    public void setMdcIngredient(String mdcIngredient) {
        this.mdcIngredient = mdcIngredient;
    }

    public String getMdcConcentration() {
        return mdcConcentration;
    }

    public void setMdcConcentration(String mdcConcentration) {
        this.mdcConcentration = mdcConcentration;
    }

    public String getMdcUnit() {
        return mdcUnit;
    }

    public void setMdcUnit(String mdcUnit) {
        this.mdcUnit = mdcUnit;
    }

    public String getMdcUserManual() {
        return mdcUserManual;
    }

    public void setMdcUserManual(String mdcUserManual) {
        this.mdcUserManual = mdcUserManual;
    }

    public double getMdcPriceSale() {
        return mdcPriceSale;
    }

    public void setMdcPriceSale(double mdcPriceSale) {
        this.mdcPriceSale = mdcPriceSale;
    }

    public double getMdcRate() {
        return mdcRate;
    }

    public void setMdcRate(double mdcRate) {
        this.mdcRate = mdcRate;
    }

    public double getMdcPriceImport() {
        return mdcPriceImport;
    }

    public void setMdcPriceImport(double mdcPriceImport) {
        this.mdcPriceImport = mdcPriceImport;
    }

    public String getMdcImage() {
        return mdcImage;
    }

    public void setMdcImage(String mdcImage) {
        this.mdcImage = mdcImage;
    }

    @Override
    public String toString() {
        return "Medicine{" + "mdcID=" + mdcID + ", mdcTypeOfMedicineID=" + mdcTypeOfMedicineID + ", mdcBatchID=" + mdcBatchID + ", mdcProducerID=" + mdcProducerID + ", mdcName=" + mdcName + ", mdcIngredient=" + mdcIngredient + ", mdcConcentration=" + mdcConcentration + ", mdcUnit=" + mdcUnit + ", mdcUserManual=" + mdcUserManual + ", mdcPriceSale=" + mdcPriceSale + ", mdcRate=" + mdcRate + ", mdcPriceImport=" + mdcPriceImport + ", mdcImage=" + mdcImage + '}';
    }
    
}
