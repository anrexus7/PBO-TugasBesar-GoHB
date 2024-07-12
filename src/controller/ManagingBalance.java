package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Class.SingletonManagers.SingletonManagerCustomer;
import model.Class.db.DatabaseHandler;

public class ManagingBalance {
    public static boolean TopUpBalance(double amount) {
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();

        String selectQuery = "SELECT balance FROM userbalances WHERE user_id = ?";
        String updateQuery = "UPDATE userbalances SET balance = balance + ?, updated_at = CURRENT_TIMESTAMP WHERE user_id = ?";
        
        try {
            PreparedStatement selectStmt = conn.con.prepareStatement(selectQuery);
            PreparedStatement updateStmt = conn.con.prepareStatement(updateQuery);

            int userId = SingletonManagerCustomer.getInstance().getCustomer().getUserID();

            selectStmt.setInt(1, userId);
            ResultSet rs = selectStmt.executeQuery();
            if (!rs.next()) {
                System.out.println("User not found.");
                return false;
            }

            updateStmt.setDouble(1, amount);
            updateStmt.setInt(2, userId);
            updateStmt.executeUpdate();

            conn.disconnect();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            conn.disconnect();
            return (false);
        }
    }
}