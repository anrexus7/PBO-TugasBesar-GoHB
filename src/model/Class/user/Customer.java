package model.Class.user;

import model.Class.transaction.GoPay;
import model.Class.transaction.GoPlus;
import model.Class.order.Order;
import model.Class.transaction.Promo;
import model.Enum.UserType;

public class Customer extends User{
    private GoPay wallet;
    private Promo promo;
    private Order order;
    private GoPlus gojekPlus;

    public Customer() {
    }

    public Customer(int userID, String username, String name, String password, String phoneNumber, String email, UserType userType, GoPay wallet) {
        super(userID, username, name, password, phoneNumber, email, userType);
        this.wallet = wallet;
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
