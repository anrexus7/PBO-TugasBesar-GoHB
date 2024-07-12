package model.Enum;

public enum CarTypeOfCarService {
    HEMAT(0), 
    PRIORITAS(3000), 
    COMFORT(2000), 
    BIASA(1000);

    private double addFareKm;

    CarTypeOfCarService(double addFareKm) {
        this.addFareKm = addFareKm;
    }

    public double getAddFareKm() {
        return addFareKm;
    }
}
