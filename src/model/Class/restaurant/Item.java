package model.Class.restaurant;


import model.Enum.KategoriItem;

public class Item {
    private int itemID;
    private String name;
    private double harga;
    private KategoriItem kategori;

    public Item(int itemID, String name, double harga, KategoriItem kategori) {
        this.itemID = itemID;
        this.name = name;
        this.harga = harga;
        this.kategori = kategori;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public KategoriItem getKategori() {
        return kategori;
    }

    public void setKategori(KategoriItem kategori) {
        this.kategori = kategori;
    }
}
