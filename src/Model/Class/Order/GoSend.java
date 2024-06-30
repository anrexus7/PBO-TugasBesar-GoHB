package Model.Class.Order;

import Model.Class.Location;
import Model.Class.Promo;
import Model.Enum.StatusOrder;
import Model.Enum.TipeBarang;
import Model.Enum.TypeOfService;
import Model.Enum.VehicleType;

import java.util.Date;

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
