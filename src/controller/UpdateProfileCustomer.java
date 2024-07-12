package controller;
import model.Class.SingletonManagers.SingletonManagerCustomer;
import model.Class.db.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateProfileCustomer {
    public static boolean validatingInput(String name, String phone, String email){
        if(name.isEmpty() || phone.isEmpty() || email.isEmpty()){
            return false;
        }

        return true;
    }

    public static boolean update(String name, String phone, String email) {
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();

        String query = "UPDATE users SET "
                + "name = ?, "
                + "phone_number = ?, "
                + "email = ?"
                + "WHERE user_id = " + SingletonManagerCustomer.getInstance().getCustomer().getUserID();

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

            SingletonManagerCustomer.getInstance().getCustomer().setName(name);
            SingletonManagerCustomer.getInstance().getCustomer().setPhoneNumber(phone);
            SingletonManagerCustomer.getInstance().getCustomer().setEmail(email);

            return (true);
        }catch(SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
}
