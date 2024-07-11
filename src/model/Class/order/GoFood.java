package model.Class.order;

import java.util.Date;
import java.util.List;

import model.Class.location.Location;
import model.Class.restaurant.Item;
import model.Class.restaurant.Restaurant;
import model.Class.transaction.Promo;
import model.Enum.StatusOrder;
import model.Enum.TypeOfService;
import model.Enum.VehicleType;

public class GoFood extends Order{
    private Location locationDelivery;
    private List<Item> cart;
    private List<Restaurant> listResto;

    public GoFood(StatusOrder orderStatus, Date transactionDate, double amount, TypeOfService serviceType, VehicleType vehicleType, Promo promo, int driverID, int customerID, int orderID, Location locationDelivery, List<Item> cart, List<Restaurant> listResto) {
        super(orderStatus, transactionDate, amount, serviceType, vehicleType, promo, driverID, customerID, orderID);
        this.locationDelivery = locationDelivery;
        this.cart = cart;
        this.listResto = listResto;
    }

    public Location getLocationDelivery() {
        return locationDelivery;
    }

    public void setLocationDelivery(Location locationDelivery) {
        this.locationDelivery = locationDelivery;
    }

    public List<Item> getCart() {
        return cart;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
    }

    public List<Restaurant> getListResto() {
        return listResto;
    }

    public void setListResto(List<Restaurant> listResto) {
        this.listResto = listResto;
    }
}
