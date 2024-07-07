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
        if (plat.isEmpty()) {
            return false;
        } else {
            if (tipeVehicle.equals(VehicleType.BIKE)) {
                if (engine.isEmpty()) {
                    return false;
                }
            } else {
                if (seat.isEmpty()) {
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

        String queryInsertV = "INSERT INTO vehicle VALUES(?,?,?) ";
        String queryGet = "SELECT vehicle_id FROM vehicle ORDER BY vehicle_id DESC LIMIT 1";
        String queryInsertD = "INSERT INTO drivers(vehicle_id) VALUES(?)";

        try{
            conn.con.setAutoCommit(false);
            PreparedStatement stmt = conn.con.prepareStatement(queryInsertV);
            stmt.setInt(1, 0);
            stmt.setString(2, tipeVehicle.toString());
            stmt.setString(3, plat);
            stmt.executeUpdate();

            Statement usernameStatement = conn.con.createStatement();
            ResultSet rs = usernameStatement.executeQuery(queryGet);
            while(rs.next()){
                vehicleID = rs.getInt("vehicle_id");
            }

            PreparedStatement stmt2 = conn.con.prepareStatement(queryInsertD);
            stmt2.setInt(1, vehicleID);
            stmt2.executeUpdate();

            conn.con.commit();
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
