/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author LENOVO
 */
public class DetailedInvoice {
    private String diInvoiceID;
    private String diMedicineID;
    private int diMedicineQuantity;
    private double diPrices;
    private String diNote;

    public DetailedInvoice() {
    }

    public DetailedInvoice(String diInvoiceID, String diMedicineID, int diMedicineQuantity, double diPrices, String diNote) {
        this.diInvoiceID = diInvoiceID;
        this.diMedicineID = diMedicineID;
        this.diMedicineQuantity = diMedicineQuantity;
        this.diPrices = diPrices;
        this.diNote = diNote;
    }

    public String getDiInvoiceID() {
        return diInvoiceID;
    }

    public void setDiInvoiceID(String diInvoiceID) {
        this.diInvoiceID = diInvoiceID;
    }

    public String getDiMedicineID() {
        return diMedicineID;
    }

    public void setDiMedicineID(String diMedicineID) {
        this.diMedicineID = diMedicineID;
    }

    public int getDiMedicineQuantity() {
        return diMedicineQuantity;
    }

    public void setDiMedicineQuantity(int diMedicineQuantity) {
        this.diMedicineQuantity = diMedicineQuantity;
    }

    public double getDiPrices() {
        return diPrices;
    }

    public void setDiPrices(double diPrices) {
        this.diPrices = diPrices;
    }

    public String getDiNote() {
        return diNote;
    }

    public void setDiNote(String diNote) {
        this.diNote = diNote;
    }

    @Override
    public String toString() {
        return "DetailedInvoice{" + "diInvoiceID=" + diInvoiceID + ", diMedicineID=" + diMedicineID + ", diMedicineQuantity=" + diMedicineQuantity + ", diPrices=" + diPrices + ", diNote=" + diNote + '}';
    }
    
}
