package controller;

import model.Class.db.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class Register {
    public static boolean validatingRegister(HashMap<String, String> tempInputs, char[] password){
//        Encryptor encryptor = new Encryptor();
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();

        String tempPassword = "";
        for (char ch : password) {
            tempPassword += ch;
        }

        String encryptPass = Encryptor.hash(tempPassword);

        String query = "INSERT INTO users(username,name, password, email, phone_number, user_type, balance, coins) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, tempInputs.get("username"));
            stmt.setString(2, tempInputs.get("name"));
            stmt.setString(3, encryptPass);
            stmt.setString(4, tempInputs.get("email"));
            stmt.setString(5, tempInputs.get("phone"));
            stmt.setString(6, tempInputs.get("role"));
            stmt.setDouble(7, 0);
            stmt.setDouble(8, 0);
            stmt.executeUpdate();
            conn.disconnect();

            if(tempInputs.get("role").equals("Driver")){
                new RegisterDrive(tempInputs.get("username"));
            }

            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            conn.disconnect();
            return (false);
        }
    }
}
