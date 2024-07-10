package model.Class.user;

import model.Class.order.Order;
import model.Class.transaction.GoPay;
import model.Class.vehicle.Vehicle;
import model.Enum.DriverAvailability;
import model.Enum.UserType;

public class Driver extends User{
    private int driverId;
    private Vehicle vehicle;
    private DriverAvailability availability;
    private Order order;
    private float rating;
    private GoPay wallet;

    public Driver(int userID, String username, String name, String password, String phoneNumber, String email, UserType userType, GoPay wallet, int driverId, Vehicle vehicle, DriverAvailability availability, float rating) {
        super(userID, username, name, password, phoneNumber, email, userType);
        this.driverId = driverId;
        this.vehicle = vehicle;
        this.availability = availability;
        this.rating = rating;
        this.wallet = wallet;
    }

    public Driver() {

    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public DriverAvailability getAvailability() {
        return availability;
    }

    public void setAvailability(DriverAvailability availability) {
        this.availability = availability;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public GoPay getWallet() {
        return wallet;
    }

    public void setWallet(GoPay wallet) {
        this.wallet = wallet;
    }
}
