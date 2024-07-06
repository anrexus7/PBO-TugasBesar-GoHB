package model.Class.transaction;

import model.Enum.TypeOfService;

import java.util.Date;

public class Promo {
    private int promoID;
    private String name;
    private String code;
    private TypeOfService typeOfService;
    private double discount;
    private Date expiryDate;

    public Promo(int promoID, String name, String code, TypeOfService typeOfService, double discount, Date expiryDate) {
        this.promoID = promoID;
        this.name = name;
        this.code = code;
        this.typeOfService = typeOfService;
        this.discount = discount;
        this.expiryDate = expiryDate;
    }

    public int getPromoID() {
        return promoID;
    }

    public void setPromoID(int promoID) {
        this.promoID = promoID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
