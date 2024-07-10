package controller;

import model.Class.db.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterDrive {
    public RegisterDrive(String username) {
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();
        int ID=0;
        try {
            Statement st = conn.con.createStatement();
            ResultSet rs = st.executeQuery("SELECT user_id FROM users WHERE username = '" + username + "'");
            while (rs.next()) {
                ID = rs.getInt("user_id");
            }

            String query = "INSERT INTO drivers(user_id, status) VALUES("+ID+", 'ONLINE')";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.executeUpdate();
            conn.disconnect();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
