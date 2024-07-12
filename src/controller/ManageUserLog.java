package controller;

import model.Class.db.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

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

    public static ArrayList<Object[]> getData(){
        conn = new DatabaseHandler();
        conn.connect();

        ArrayList<Object[]> data = new ArrayList<>();

        String query = "SELECT * FROM userlog";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                Object[] tempLog = new Object[3];
                tempLog[0] = rs.getString("user_id");
                tempLog[1] = rs.getString("activity_type");
                tempLog[2] = rs.getTimestamp("timestamp");

                data.add(tempLog);
            }
            conn.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

}
