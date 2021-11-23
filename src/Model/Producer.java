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
public class Producer {
    private String pdcID;
    private String pdcCompanyName;
    private Date pdcFoundingDate;
    private String pdcEmail;
    private String pdcHotline;

    public Producer() {
    }

    public Producer(String pdcID, String pdcCompanyName, Date pdcFoundingDate, String pdcEmail, String pdcHotline) {
        this.pdcID = pdcID;
        this.pdcCompanyName = pdcCompanyName;
        this.pdcFoundingDate = pdcFoundingDate;
        this.pdcEmail = pdcEmail;
        this.pdcHotline = pdcHotline;
    }

    public String getPdcID() {
        return pdcID;
    }

    public void setPdcID(String pdcID) {
        this.pdcID = pdcID;
    }

    public String getPdcCompanyName() {
        return pdcCompanyName;
    }

    public void setPdcCompanyName(String pdcCompanyName) {
        this.pdcCompanyName = pdcCompanyName;
    }

    public Date getPdcFoundingDate() {
        return pdcFoundingDate;
    }

    public void setPdcFoundingDate(Date pdcFoundingDate) {
        this.pdcFoundingDate = pdcFoundingDate;
    }

    public String getPdcEmail() {
        return pdcEmail;
    }

    public void setPdcEmail(String pdcEmail) {
        this.pdcEmail = pdcEmail;
    }

    public String getPdcHotline() {
        return pdcHotline;
    }

    public void setPdcHotline(String pdcHotline) {
        this.pdcHotline = pdcHotline;
    }

    @Override
    public String toString() {
        return "Producer{" + "pdcID=" + pdcID + ", pdcCompanyName=" + pdcCompanyName + ", pdcFoundingDate=" + pdcFoundingDate + ", pdcEmail=" + pdcEmail + ", pdcHotline=" + pdcHotline + '}';
    }
    
    
}
