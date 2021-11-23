/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author LENOVO
 */
public class Salary {
    private String slrEmployeeID;
    private double slrBasicSalary;
    private double slrCommission;
    private double slrSalesBonus;
    private int slrDaysOffOfMonth;

    public Salary() {
    }

    public Salary(String slrEmployeeID, double slrBasicSalary, double slrCommission, double slrSalesBonus, int slrDaysOffOfMonth) {
        this.slrEmployeeID = slrEmployeeID;
        this.slrBasicSalary = slrBasicSalary;
        this.slrCommission = slrCommission;
        this.slrSalesBonus = slrSalesBonus;
        this.slrDaysOffOfMonth = slrDaysOffOfMonth;
    }

    public String getSlrEmployeeID() {
        return slrEmployeeID;
    }

    public void setSlrEmployeeID(String slrEmployeeID) {
        this.slrEmployeeID = slrEmployeeID;
    }

    public double getSlrBasicSalary() {
        return slrBasicSalary;
    }

    public void setSlrBasicSalary(double slrBasicSalary) {
        this.slrBasicSalary = slrBasicSalary;
    }

    public double getSlrCommission() {
        return slrCommission;
    }

    public void setSlrCommission(double slrCommission) {
        this.slrCommission = slrCommission;
    }

    public double getSlrSalesBonus() {
        return slrSalesBonus;
    }

    public void setSlrSalesBonus(double slrSalesBonus) {
        this.slrSalesBonus = slrSalesBonus;
    }

    public int getSlrDaysOffOfMonth() {
        return slrDaysOffOfMonth;
    }

    public void setSlrDaysOffOfMonth(int slrDaysOffOfMonth) {
        this.slrDaysOffOfMonth = slrDaysOffOfMonth;
    }

    @Override
    public String toString() {
        return "Salary{" + "slrEmployeeID=" + slrEmployeeID + ", slrBasicSalary=" + slrBasicSalary + ", slrCommission=" + slrCommission + ", slrSalesBonus=" + slrSalesBonus + ", slrDaysOffOfMonth=" + slrDaysOffOfMonth + '}';
    }
    
    
}
