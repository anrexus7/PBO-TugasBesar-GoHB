package Model.Class.Order;

import Model.Class.Location;
import Model.Class.Promo;
import Model.Class.Restaurant.Item;
import Model.Class.Restaurant.Restaurant;
import Model.Enum.StatusOrder;
import Model.Enum.TypeOfService;
import Model.Enum.VehicleType;

import java.util.Date;
import java.util.List;

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
