package model.Class;

import model.Class.order.Order;
import model.Class.user.User;

import java.util.ArrayList;

public class SingletonManager {
    private static SingletonManager instance;
    private User user;
    private ArrayList<Order> orders;

    SingletonManager() {
    }

    public static SingletonManager getInstance() {
        if (instance == null) {
            instance = new SingletonManager();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
