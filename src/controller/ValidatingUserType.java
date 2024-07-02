package controller;

import model.Class.db.DatabaseHandler;
import model.Enum.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ValidatingUserType {
    public static UserType validating(String username) {
        DatabaseHandler conn = new DatabaseHandler();
        String type = "";

        conn.connect();
        try {
            Statement st = conn.con.createStatement();
            ResultSet rs = st.executeQuery("SELECT user_type FROM users WHERE username = '" + username + "'");
            while(rs.next()) {
                type = rs.getString("user_type");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        switch (type) {
            case "CUSTOMER":
                return UserType.CUSTOMER;
            case "DRIVER":
                return UserType.DRIVER;
            default:
                return UserType.ADMIN;
        }
    }
}
