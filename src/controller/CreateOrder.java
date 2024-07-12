package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Class.SingletonManagers.SingletonManagerCustomer;
import model.Class.db.DatabaseHandler;
import model.Enum.StatusOrder;
import model.Enum.TypeOfService;
import model.Enum.VehicleType;

public class CreateOrder {

    public static boolean createOrder(TypeOfService service, VehicleType vehicle, String currLoc, int currLocRegionID, String destination, int destinationRegionID, double cost) {

        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();

        String query = "INSERT INTO orders(customer_id, driver_id, service_type, vehicle_type, "
                + "current_location, region_id_current, destination, region_id_destination, cost, "
                + "order_status, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 

        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, SingletonManagerCustomer.getInstance().getCustomer().getUserID());
            stmt.setInt(2, 0);
            stmt.setString(3, service.toString());
            stmt.setString(4, vehicle.toString());
            stmt.setString(5, currLoc);
            stmt.setInt(6, currLocRegionID);
            stmt.setString(7, destination);
            stmt.setInt(8, destinationRegionID);
            stmt.setDouble(9, cost);
            stmt.setString(10, StatusOrder.ASSIGNED.toString());
            stmt.setTimestamp(11, new java.sql.Timestamp(System.currentTimeMillis()));
            stmt.setTimestamp(12, new java.sql.Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
            conn.disconnect();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            conn.disconnect();
            return (false);
        }
    }
}
