/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.registration.view.util.tblmodel;


import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author SSNLIVE
 */
public class RegisterTM {
    private String stID;
    private String cID;
    private Date date;
    private BigDecimal fee;

    public RegisterTM() {
    }

    public RegisterTM(String stID, String cID, Date date, BigDecimal fee) {
        this.stID = stID;
        this.cID = cID;
        this.date = date;
        this.fee = fee;
    }

    /**
     * @return the stID
     */
    public String getStID() {
        return stID;
    }

    /**
     * @param stID the stID to set
     */
    public void setStID(String stID) {
        this.stID = stID;
    }

    /**
     * @return the cID
     */
    public String getcID() {
        return cID;
    }

    /**
     * @param cID the cID to set
     */
    public void setcID(String cID) {
        this.cID = cID;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the fee
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * @param fee the fee to set
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
    
}
