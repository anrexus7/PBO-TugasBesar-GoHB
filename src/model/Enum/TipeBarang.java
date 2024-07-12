package model.Enum;

public enum TipeBarang {
    NORMAL(10000), 
    FRAGILE(15000), 
    RADIOACTIVE(1500000), 
    CORROSIVE(750000), 
    FLAMMABLE(50000), 
    HAZARD(100000);

    private double fare5Kg;

    TipeBarang(double fare5Kg) {
        this.fare5Kg = fare5Kg;
    }

    public double getFare5Kg() {
        return fare5Kg;
    }
}
