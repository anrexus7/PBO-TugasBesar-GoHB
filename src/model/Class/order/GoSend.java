package model.Class.order;

import java.util.Date;

import model.Class.location.Location;
import model.Class.transaction.Promo;
import model.Enum.StatusOrder;
import model.Enum.TipeBarang;
import model.Enum.TypeOfService;
import model.Enum.VehicleType;

public class GoSend extends Order{
    private Location locationPickup;
    private Location locationDropoff;
    private TipeBarang tipeBarang;
    private float weight;

    public GoSend(StatusOrder orderStatus, Date transactionDate, double amount, TypeOfService serviceType, VehicleType vehicleType, Promo promo, int driverID, int customerID, int orderID, Location locationPickup, Location locationDropoff, TipeBarang tipeBarang, float weight) {
        super(orderStatus, transactionDate, amount, serviceType, vehicleType, promo, driverID, customerID, orderID);
        this.locationPickup = locationPickup;
        this.locationDropoff = locationDropoff;
        this.tipeBarang = tipeBarang;
        this.weight = weight;
    }

    public Location getLocationPickup() {
        return locationPickup;
    }

    public void setLocationPickup(Location locationPickup) {
        this.locationPickup = locationPickup;
    }

    public Location getLocationDropoff() {
        return locationDropoff;
    }

    public void setLocationDropoff(Location locationDropoff) {
        this.locationDropoff = locationDropoff;
    }

    public TipeBarang getTipeBarang() {
        return tipeBarang;
    }

    public void setTipeBarang(TipeBarang tipeBarang) {
        this.tipeBarang = tipeBarang;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
