package Model.Class.User;

import Model.Class.GoPay;
import Model.Class.GoPlus;
import Model.Class.Order.Order;
import Model.Class.Promo;
import Model.Enum.UserType;

public class Customer extends User{
    private int customerID;
    private GoPay wallet;
    private Promo promo;
    private Order order;
    private GoPlus gojekPlus;

    public Customer(int userID, String username, String name, String password, String phoneNumber, String email, UserType userType, int customerID, GoPay wallet, Promo promo, Order order, GoPlus gojekPlus) {
        super(userID, username, name, password, phoneNumber, email, userType);
        this.customerID = customerID;
        this.wallet = wallet;
        this.promo = promo;
        this.order = order;
        this.gojekPlus = gojekPlus;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public GoPay getWallet() {
        return wallet;
    }

    public void setWallet(GoPay wallet) {
        this.wallet = wallet;
    }

    public Promo getPromo() {
        return promo;
    }

    public void setPromo(Promo promo) {
        this.promo = promo;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public GoPlus getGojekPlus() {
        return gojekPlus;
    }

    public void setGojekPlus(GoPlus gojekPlus) {
        this.gojekPlus = gojekPlus;
    }
}
