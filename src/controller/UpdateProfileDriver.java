package controller;

import model.Class.SingletonManagers.SingletonManagerDriver;
import model.Class.db.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateProfileDriver {

    public static boolean update(String name, String email, String phone) {
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();

        String query = "UPDATE users SET "
                + "name = ?, "
                + "phone_number = ?, "
                + "email = ?"
                + "WHERE user_id = '" + SingletonManagerDriver.getInstance().getDriver().getUserID() + "'";

        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.setString(3, email);

            int check = stmt.executeUpdate();
            conn.disconnect();

            if(check==0){
                return (false);
            }

            SingletonManagerDriver.getInstance().getDriver().setName(name);
            SingletonManagerDriver.getInstance().getDriver().setPhoneNumber(phone);
            SingletonManagerDriver.getInstance().getDriver().setEmail(email);

            return (true);
        }catch(SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

}
