package controller;

import model.Class.SingletonManagerDriver;
import model.Class.db.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SubmitDriverReport {

    public static boolean insertReport(String text) {

        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();

        String queryReport = "INSERT INTO report(user_id, complaint, status, created_at, updated_at) VALUES(?,?,?,?,?) ";

        try{
            PreparedStatement stmt = conn.con.prepareStatement(queryReport);
            stmt.setInt(1, SingletonManagerDriver.getInstance().getDriver().getDriverId());
            stmt.setString(2, text);
            stmt.setString(3, "CLOSED");
            stmt.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
            stmt.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));

            stmt.executeUpdate();

            conn.disconnect();
        }catch(SQLException e){
            e.printStackTrace();
            conn.disconnect();
            return (false);
        }

        return true;

    }

}
