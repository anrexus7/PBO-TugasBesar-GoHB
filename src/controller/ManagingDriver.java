package controller;

import model.Class.db.DatabaseHandler;
import model.Class.transaction.GoPlus;
import model.Class.user.Customer;
import model.Class.user.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManagingDriver {
    public static ArrayList<Driver> getData() {
        ArrayList<Driver> drivers = new ArrayList<>();
        DatabaseHandler conn = new DatabaseHandler();
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
}
