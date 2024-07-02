package model.Class.vehicle;

import model.Enum.CarTypeOfCarService;
import model.Enum.VehicleType;

public class Car extends Vehicle{
    private int seatCount;
    private CarTypeOfCarService typeOfCarService;

    public Car(int driverID, String plateNumber, int vehicleID, VehicleType vehicleType, int seatCount, CarTypeOfCarService typeOfCarService) {
        super(driverID, plateNumber, vehicleID, vehicleType);
        this.seatCount = seatCount;
        this.typeOfCarService = typeOfCarService;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public CarTypeOfCarService getTypeOfCarService() {
        return typeOfCarService;
    }

    public void setTypeOfCarService(CarTypeOfCarService typeOfCarService) {
        this.typeOfCarService = typeOfCarService;
    }
}
