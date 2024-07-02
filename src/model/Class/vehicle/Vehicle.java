package model.Class.vehicle;

import model.Enum.VehicleType;

public class Vehicle {
    private int driverID;
    private String plateNumber;
    private int vehicleID;
    private VehicleType vehicleType;

    public Vehicle() {
    }

    public Vehicle(int driverID, String plateNumber, int vehicleID, VehicleType vehicleType) {
        this.driverID = driverID;
        this.plateNumber = plateNumber;
        this.vehicleID = vehicleID;
        this.vehicleType = vehicleType;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
