package model.Class.SingletonManagers;

import model.Class.order.Order;
import model.Class.user.Customer;

import java.util.ArrayList;

public class SingletonManagerCustomer {
    private static SingletonManagerCustomer instance;
    private Customer user;

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

    public boolean isSubs(){
        return (user.getGojekPlus() != null);
    }

    public void logout() {
        user = null;
        instance = null;
    }
}
