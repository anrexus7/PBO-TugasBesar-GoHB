package model.Class.order;

import java.util.Date;
import java.util.List;

import model.Class.location.Location;
import model.Class.restaurant.Restaurant;
import model.Class.transaction.Promo;
import model.Enum.StatusOrder;
import model.Enum.TypeOfService;
import model.Enum.VehicleType;

public class GoFood extends Order{
    private Location locationDelivery;
    private List<OrderItem> cart;
    private Restaurant restaurant;
    
    public GoFood(StatusOrder orderStatus, Date transactionDate, double amount, TypeOfService serviceType,
            VehicleType vehicleType, Promo promo, int driverID, int customerID, int orderID, Location locationDelivery,
            List<OrderItem> cart, Restaurant restaurant) {
        super(orderStatus, transactionDate, amount, serviceType, vehicleType, promo, driverID, customerID, orderID);
        this.locationDelivery = locationDelivery;
        this.cart = cart;
        this.restaurant = restaurant;
    }

    public Location getLocationDelivery() {
        return locationDelivery;
    }

    public void setLocationDelivery(Location locationDelivery) {
        this.locationDelivery = locationDelivery;
    }

    public List<OrderItem> getCart() {
        return cart;
    }

    public void setCart(List<OrderItem> cart) {
        this.cart = cart;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
