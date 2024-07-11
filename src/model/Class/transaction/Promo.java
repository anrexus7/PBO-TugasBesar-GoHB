package model.Class.transaction;

import model.Enum.TypeOfService;

import java.util.Date;

public class Promo {
    private int promoID;
    private String code;
    private TypeOfService typeOfService;
    private double discount;
    private Date startDate;
    private Date expiryDate;

    public Promo(int promoID, String code, TypeOfService typeOfService, double discount,Date startDate, Date expiryDate) {
        this.promoID = promoID;
        this.code = code;
        this.typeOfService = typeOfService;
        this.discount = discount;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
    }

    public int getPromoID() {
        return promoID;
    }

    public void setPromoID(int promoID) {
        this.promoID = promoID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TypeOfService getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(TypeOfService typeOfService) {
        this.typeOfService = typeOfService;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
