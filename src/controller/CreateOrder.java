package controller;

import model.Class.db.DatabaseHandler;
import model.Enum.StatusOrder;
import model.Enum.TypeOfService;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateOrder {

    public static boolean createGoOrder(int id, TypeOfService service, String current, String destination) {

        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();

        String query = "INSERT INTO orders(customer_id, service_type, current_location, destination, cost, order_status, created_at, updated_at) VALUES(?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setString(2, service.toString());
            stmt.setString(3, current);
            stmt.setString(4, destination);
            stmt.setDouble(5, 1.00);
            stmt.setString(6, StatusOrder.ASSIGNED.toString());
            stmt.setTimestamp(7, new java.sql.Timestamp(System.currentTimeMillis()));
            stmt.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));
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
