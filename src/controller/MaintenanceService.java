package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Class.db.DatabaseHandler;
import model.Class.vehicle.Maintenance;

public class MaintenanceService {

    public static Maintenance fetchNextMaintenance(int driverId) throws SQLException {
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();

        String query = "SELECT * FROM vehiclemaintenance WHERE driver_id = ? ORDER BY schedule_date LIMIT 1";
        PreparedStatement stmt = conn.con.prepareStatement(query);
        stmt.setInt(1, driverId);

        ResultSet rs = stmt.executeQuery();
        Maintenance maintenance = null;
        if (rs.next()) {
            maintenance = new Maintenance(
                    rs.getInt("maintenance_id"),
                    rs.getInt("driver_id"),
                    rs.getInt("admin_id"),
                    rs.getDate("schedule_date"),
                    rs.getString("status"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
            );
        }

        conn.disconnect();
        return maintenance;
    }

    public static String getMaintenanceStatusMessage(Maintenance maintenance) {
        if (maintenance == null) {
            return "There are no maintenance records for this vehicle.";
        }

        String status = maintenance.getStatus();
        Date scheduleDate = maintenance.getScheduleDate();
        long daysUntilMaintenance = (scheduleDate.getTime() - System.currentTimeMillis()) / (1000 * 60 * 60 * 24);

        switch (status) {
            case "SCHEDULED":
                return "Maintenance will occur in " + daysUntilMaintenance + " days.";
            case "ONGOING":
                return "Vehicle maintenance in progress.";
            case "COMPLETED":
                return "There are no upcoming maintenance.";
            case "CANCELED":
                return "The upcoming maintenance is canceled.";
            default:
                return "Unknown maintenance status.";
        }
    }
}
