package controller;

import model.Class.db.DatabaseHandler;
import model.Class.order.Order;
import model.Class.transaction.GoPlus;
import model.Class.user.Customer;
import model.Class.user.Driver;
import model.Enum.StatusOrder;
import model.Enum.TypeOfService;
import model.Enum.VehicleType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManagingDriver {
    private static DatabaseHandler conn = new DatabaseHandler();
    public static ArrayList<Driver> getData() {
        ArrayList<Driver> drivers = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM drivers JOIN users ON drivers.user_id = users.user_id WHERE user_type = 'DRIVER' ";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Driver temp = new Driver();
                temp.setDriverId(rs.getInt("driver_id"));
                temp.setUsername(rs.getString("username"));
                temp.setEmail(rs.getString("email"));
                temp.setPhoneNumber(rs.getString("phone_number"));
                temp.setRating(rs.getFloat("rating"));
                drivers.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return drivers;
    }

    public static double fetchEarningDriver(int driverId){
        conn.connect();

        String query = "SELECT * FROM orders WHERE driver_id = ? AND order_status = 'COMPLETED' AND YEAR(created_at) = YEAR(NOW()) AND MONTH(created_at) = MONTH(NOW());";
        double earnings=0;
        try{
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, driverId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                earnings += rs.getDouble("cost");
            }
            conn.disconnect();
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return earnings;
    }
}
