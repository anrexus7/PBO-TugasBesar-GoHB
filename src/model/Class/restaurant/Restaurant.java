package model.Class.restaurant;

import java.time.LocalTime;
import java.util.List;

import model.Class.location.Location;

public class Restaurant {
    private String name;
    private int restoID;
    private Location location;
    private List<Item> listItem;
    private float rating;
    private Item recommended;
    private LocalTime openTime;
    private LocalTime closeTime;

    public Restaurant(String name, int restoID, Location location, List<Item> listItem, float rating, Item recommended, LocalTime openTime, LocalTime closeTime) {
        this.name = name;
        this.restoID = restoID;
        this.location = location;
        this.listItem = listItem;
        this.rating = rating;
        this.recommended = recommended;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRestoID() {
        return restoID;
    }

    public void setRestoID(int restoID) {
        this.restoID = restoID;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Item> getListItem() {
        return listItem;
    }

    public void setListItem(List<Item> listItem) {
        this.listItem = listItem;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Item getRecommended() {
        return recommended;
    }

    public void setRecommended(Item recommended) {
        this.recommended = recommended;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }
}
