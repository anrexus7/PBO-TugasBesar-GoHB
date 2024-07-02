package controller;

import model.Class.db.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login {
    public static boolean validatingLogin(String username, char[] password) {

        Encryptor encryptor = new Encryptor();
        DatabaseHandler conn = new DatabaseHandler();

        String tempPassword = "";
        for (char ch : password) {
            tempPassword += ch;
        }

        String encryptPass = encryptor.encrypt(tempPassword);

        conn.connect();

        try {
            Statement st = conn.con.createStatement();
            ResultSet rs = st.executeQuery("SELECT username, password FROM users");

            while (rs.next()) {
                if (username.equals(rs.getString("username")) && rs.getString("password").equals(encryptPass.trim())) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
