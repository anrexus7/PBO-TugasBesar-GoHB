package model.Class.user;

import model.Class.order.Order;
import model.Class.vehicle.Vehicle;
import model.Enum.DriverAvailability;
import model.Enum.UserType;

public class Driver extends User{
    private Vehicle vehicle;
    private DriverAvailability availability;
    private Order order;
    private float rating;

    public Driver(int userID, String username, String name, String password, String phoneNumber, String email, UserType userType, int driverID, Vehicle vehicle, DriverAvailability availability, Order order, float rating) {
        super(userID, username, name, password, phoneNumber, email, userType);
        this.vehicle = vehicle;
        this.availability = availability;
        this.order = order;
        this.rating = rating;
    }

    public Driver() {

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
}