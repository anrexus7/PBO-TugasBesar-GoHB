package model.Class.restaurant;

import java.time.LocalTime;
import java.util.List;

import model.Class.location.Location;

public class Restaurant {
    private String name;
    private String phoneNumber;
    private int restoID;
    private Location location;
    private List<Item> listItem;
    private float rating;

    public Restaurant(String name, int restoID,String phoneNumber, Location location, float rating) {
        this.name = name;
        this.restoID = restoID;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.rating = rating;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
