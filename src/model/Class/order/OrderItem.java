package model.Class.order;

import model.Class.restaurant.Item;

public class OrderItem {
    private int jumlah;
    private Item item;
    
    public OrderItem(int jumlah, Item item) {
        this.jumlah = jumlah;
        this.item = item;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
