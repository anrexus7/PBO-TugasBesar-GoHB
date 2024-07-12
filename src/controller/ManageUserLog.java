package controller;

import model.Class.db.DatabaseHandler;

import java.sql.SQLException;
import java.sql.PreparedStatement;

public class ManageUserLog {

    private static DatabaseHandler conn;

    public static void logUserActivity(int userId, String activityType) {
        conn = new DatabaseHandler();
        conn.connect();

        String query = "INSERT INTO userlog (user_id, activity_type, timestamp) VALUES (?, ?, CURRENT_TIMESTAMP())";

        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, userId);
            stmt.setString(2, activityType);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

    }


}
