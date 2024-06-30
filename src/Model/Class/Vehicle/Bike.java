package Model.Class.Vehicle;

import Model.Enum.VehicleType;

public class Bike extends Vehicle {
    private int engineCapacity;
    private boolean rainCoat;

    public Bike(int driverID, String plateNumber, int vehicleID, VehicleType vehicleType, int engineCapacity, boolean rainCoat) {
        super(driverID, plateNumber, vehicleID, vehicleType);
        this.engineCapacity = engineCapacity;
        this.rainCoat = rainCoat;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public boolean isRainCoat() {
        return rainCoat;
    }

    public void setRainCoat(boolean rainCoat) {
        this.rainCoat = rainCoat;
    }
}
