package model.Class.transaction;

import java.util.Date;

public class GoPlus {
    private int subsID;
    private String paket;
    private Date validFrom, validTo;
    private double harga;
    private double potongan;
    private boolean status;

    public GoPlus(int subsID, String paket, Date validFrom, Date validTo, double harga,double potongan, boolean status) {
        this.subsID = subsID;
        this.paket = paket;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.harga = harga;
        this.potongan = potongan;
        this.status = status;
    }

    public int getSubsID() {
        return subsID;
    }

    public void setSubsID(int subsID) {
        this.subsID = subsID;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public double getPotongan() {
        return potongan;
    }

    public void setPotongan(double potongan) {
        this.potongan = potongan;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
