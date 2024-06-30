package Model.Class;

import java.util.Date;

public class GoPlus {
    private int subsID;
    private String paket;
    private Date periode;
    private int jumlahVoucher;
    private double harga;
    private boolean status;

    public GoPlus(int subsID, String paket, Date periode, int jumlahVoucher, double harga, boolean status) {
        this.subsID = subsID;
        this.paket = paket;
        this.periode = periode;
        this.jumlahVoucher = jumlahVoucher;
        this.harga = harga;
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

    public Date getPeriode() {
        return periode;
    }

    public void setPeriode(Date periode) {
        this.periode = periode;
    }

    public int getJumlahVoucher() {
        return jumlahVoucher;
    }

    public void setJumlahVoucher(int jumlahVoucher) {
        this.jumlahVoucher = jumlahVoucher;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
