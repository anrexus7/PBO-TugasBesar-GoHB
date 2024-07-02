package model.Class.order;

import model.Class.Promo;
import model.Enum.StatusOrder;
import model.Enum.TypeOfService;
import model.Enum.VehicleType;

import java.util.Date;

public class Order {
    private int orderID;
    private int customerID;
    private int driverID;
    private Promo promo;
    private VehicleType vehicleType;
    private TypeOfService serviceType;
    private double amount;
    private Date transactionDate;
    private StatusOrder orderStatus;

    public Order() {
    }

    public Order(StatusOrder orderStatus, Date transactionDate, double amount, TypeOfService serviceType, VehicleType vehicleType, Promo promo, int driverID, int customerID, int orderID) {
        this.orderStatus = orderStatus;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.serviceType = serviceType;
        this.vehicleType = vehicleType;
        this.promo = promo;
        this.driverID = driverID;
        this.customerID = customerID;
        this.orderID = orderID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public Promo getPromo() {
        return promo;
    }

    public void setPromo(Promo promo) {
        this.promo = promo;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public TypeOfService getServiceType() {
        return serviceType;
    }

    public void setServiceType(TypeOfService serviceType) {
        this.serviceType = serviceType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public StatusOrder getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(StatusOrder orderStatus) {
        this.orderStatus = orderStatus;
    }
}
