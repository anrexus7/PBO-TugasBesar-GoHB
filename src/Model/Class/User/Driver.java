package Model.Class.User;

import Model.Class.Order.Order;
import Model.Class.Vehicle.Vehicle;
import Model.Enum.DriverAvailability;
import Model.Enum.UserType;

public class Driver extends User{
    private int driverID;
    private Vehicle vehicle;
    private DriverAvailability availability;
    private Order order;
    private float rating;

    public Driver(int userID, String username, String name, String password, String phoneNumber, String email, UserType userType, int driverID, Vehicle vehicle, DriverAvailability availability, Order order, float rating) {
        super(userID, username, name, password, phoneNumber, email, userType);
        this.driverID = driverID;
        this.vehicle = vehicle;
        this.availability = availability;
        this.order = order;
        this.rating = rating;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
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
