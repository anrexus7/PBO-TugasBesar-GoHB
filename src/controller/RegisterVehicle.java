package controller;

import model.Class.SingletonManagerDriver;
import model.Class.db.DatabaseHandler;
import model.Class.vehicle.Bike;
import model.Class.vehicle.Car;
import model.Class.vehicle.Vehicle;
import model.Enum.CarTypeOfCarService;
import model.Enum.VehicleType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterVehicle {

    public static boolean validatingInput(String plat, Object tipeVehicle, String engine, String seat) {
        if (plat == null || plat.isEmpty()) {
            return false;
        } else {
            if (tipeVehicle.equals(VehicleType.BIKE)) {
                if (engine == null || engine.isEmpty()) {
                    return false;
                }
            } else {
                if (seat == null || seat.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean registering(String plat, Object tipeVehicle, String engine, String seat) {
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();
        Vehicle vehicle;
        int vehicleID = 0;

        String queryInsertV = "INSERT INTO vehicle(driver_id,vehicle_type,vehicle_plat) VALUES(?,?,?) ";

        try{
            PreparedStatement stmt = conn.con.prepareStatement(queryInsertV);
            stmt.setInt(1, SingletonManagerDriver.getInstance().getDriver().getDriverId());
            stmt.setString(2, tipeVehicle.toString());
            stmt.setString(3, plat);
            stmt.executeUpdate();

            conn.disconnect();
        }catch(SQLException e){
            e.printStackTrace();
            conn.disconnect();
            return (false);
        }

        if(tipeVehicle.equals(VehicleType.BIKE)){
            vehicle = new Bike(SingletonManagerDriver.getInstance().getDriver().getUserID(),plat,vehicleID,VehicleType.BIKE,Integer.parseInt(engine),false);
        }else{
            vehicle = new Car(SingletonManagerDriver.getInstance().getDriver().getUserID(),plat,vehicleID,VehicleType.CAR,Integer.parseInt(seat), CarTypeOfCarService.BIASA);
        }

        SingletonManagerDriver.getInstance().setVehicle(vehicle);
        return true;
    }
}
