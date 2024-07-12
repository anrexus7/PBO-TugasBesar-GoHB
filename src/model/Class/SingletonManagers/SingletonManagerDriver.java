package model.Class.SingletonManagers;

import model.Class.order.Order;
import model.Class.user.Driver;
import model.Class.vehicle.Vehicle;

import java.util.ArrayList;

public class SingletonManagerDriver {
    private static SingletonManagerDriver instance;
    private Driver user;
    private Vehicle vehicle;
    private ArrayList<Order> orders;

    SingletonManagerDriver() {
    }

    public static SingletonManagerDriver getInstance() {
        if (instance == null) {
            instance = new SingletonManagerDriver();
        }
        return instance;
    }

    public Driver getDriver() {
        return user;
    }

    public void setDriver(Driver user) {
        this.user = user;
        if (user.getVehicle() != null) {
            this.vehicle = user.getVehicle();
        }
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void logout() {
        user = null;
        vehicle = null;
        orders = null;
        instance = null;
    }
}
