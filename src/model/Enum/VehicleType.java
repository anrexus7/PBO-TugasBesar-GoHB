package model.Enum;

public enum VehicleType {
    BIKE(5000), 
    CAR(10000);

    private double fareKm;

    VehicleType(double fareKm) {
        this.fareKm = fareKm;
    }

    public double getFareKm() {
        return fareKm;
    }
}
