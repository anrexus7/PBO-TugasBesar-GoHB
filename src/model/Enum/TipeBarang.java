package model.Enum;

public enum TipeBarang {
    NORMAL(10000), 
    FRAGILE(15000), 
    RADIOACTIVE(1500000), 
    CORROSIVE(750000), 
    FLAMMABLE(50000), 
    HAZARD(100000);

    private double delivFee;

    TipeBarang(double delivFee) {
        this.delivFee = delivFee;
    }

    public double getDelivFee() {
        return delivFee;
    }
}
