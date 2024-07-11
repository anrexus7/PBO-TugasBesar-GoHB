package model.Class.restaurant;


import model.Enum.KategoriItem;

public class Item {
    private int itemID;
    private String name;
    private double harga;
    private int stock;
    private KategoriItem kategori;

    public Item(String name, double harga, int stock, KategoriItem kategori) {
        this.name = name;
        this.harga = harga;
        this.stock = stock;
        this.kategori = kategori;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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
