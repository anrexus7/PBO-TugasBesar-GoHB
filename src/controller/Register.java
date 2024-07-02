package controller;

import model.Class.db.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class Register {
    public static boolean validatingRegister(HashMap<String, String> tempInputs, char[] password){
        Encryptor encryptor = new Encryptor();
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();

        String tempPassword = "";
        for (char ch : password) {
            tempPassword += ch;
        }

        String encryptPass = encryptor.encrypt(tempPassword);

        String query = "INSERT INTO users(username, password, email, phone_number, user_type) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, tempInputs.get("username"));
            stmt.setString(2, encryptPass);
            stmt.setString(3, tempInputs.get("email"));
            stmt.setString(4, tempInputs.get("phone"));
            stmt.setString(5, tempInputs.get("role"));
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
