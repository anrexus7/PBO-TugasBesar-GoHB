package model.Class;

import model.Class.order.Order;
import model.Class.user.Customer;

import java.util.ArrayList;

public class SingletonManagerCustomer {
    private static SingletonManagerCustomer instance;
    private Customer user;
    private ArrayList<Order> orders;

    SingletonManagerCustomer() {
    }

    public static SingletonManagerCustomer getInstance() {
        if (instance == null) {
            instance = new SingletonManagerCustomer();
        }
        return instance;
    }

    public Customer getCustomer() {
        return user;
    }

    public void setCustomer(Customer user) {
        this.user = user;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
