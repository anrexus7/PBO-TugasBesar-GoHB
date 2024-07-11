package model.Class.order;

import java.util.Date;

import model.Class.location.Location;
import model.Class.transaction.Promo;
import model.Enum.StatusOrder;
import model.Enum.TypeOfService;
import model.Enum.VehicleType;

public class GoRide extends Order{
    private Location locationPickup;
    private Location locationDropoff;

    public GoRide(StatusOrder orderStatus, Date transactionDate, double amount, TypeOfService serviceType, VehicleType vehicleType, Promo promo, int driverID, int customerID, int orderID, Location locationPickup, Location locationDropoff) {
        super(orderStatus, transactionDate, amount, serviceType, vehicleType, promo, driverID, customerID, orderID);
        this.locationPickup = locationPickup;
        this.locationDropoff = locationDropoff;
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
}
