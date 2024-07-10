package controller;

import model.Class.db.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Register {
    public static boolean validatingRegister(HashMap<String, String> tempInputs, char[] password){

        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();

        String tempPassword = "";
        for (char ch : password) {
            tempPassword += ch;
        }

        String encryptPass = Encryptor.hash(tempPassword);

        String query = "INSERT INTO users(username,name, password, email, phone_number, user_type) VALUES(?,?,?,?,?,?)";
        String query2 = "INSERT INTO userbalances(user_id, balance) VALUES(?,?)";
        int userId=0;
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, tempInputs.get("username"));
            stmt.setString(2, tempInputs.get("name"));
            stmt.setString(3, encryptPass);
            stmt.setString(4, tempInputs.get("email"));
            stmt.setString(5, tempInputs.get("phone"));
            stmt.setString(6, tempInputs.get("role"));
            stmt.executeUpdate();

            Statement st = conn.con.createStatement();
            ResultSet rs = st.executeQuery("SELECT user_id FROM users WHERE username = '" + tempInputs.get("username") + "'");
            while (rs.next()) {
                userId = rs.getInt("user_id");
            }

            PreparedStatement stmt2 = conn.con.prepareStatement(query2);
            stmt2.setInt(1, userId);
            stmt2.setDouble(2, 5000000);
            stmt2.executeUpdate();

            if(tempInputs.get("role").equals("Driver")){
                new RegisterDrive(tempInputs.get("username"));
            }

            conn.disconnect();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            conn.disconnect();
            return (false);
        }
    }
}
