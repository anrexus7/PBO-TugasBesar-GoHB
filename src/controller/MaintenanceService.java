package controller;

import model.Class.db.DatabaseHandler;
import model.Maintenance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MaintenanceService {

    public static Maintenance fetchNextMaintenance(int driverId) throws SQLException {

        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Maintenance maintenance = null;

        try {

            String query = "SELECT * FROM vehiclemaintenance WHERE driver_id = ? AND (status = 'SCHEDULED' OR status = 'ONGOING') ORDER BY schedule_date ASC LIMIT 1";
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, driverId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int maintenanceId = rs.getInt("maintenance_id");
                String status = rs.getString("status");
                LocalDate scheduleDate = rs.getDate("schedule_date").toLocalDate();

                maintenance = new Maintenance(maintenanceId, driverId, status, scheduleDate);
            }

        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.disconnect();
        }

        return maintenance;
    }

    public static String getMaintenanceStatusMessage(Maintenance maintenance) {
        if (maintenance == null) {
            return "There's no upcoming maintenance";
        }

        String status = maintenance.getStatus();
        LocalDate scheduleDate = maintenance.getScheduleDate();

        if (status.equals("SCHEDULED")) {
            long daysUntilMaintenance = LocalDate.now().until(scheduleDate, ChronoUnit.DAYS);
            return String.format("Maintenance will occur in %d days", daysUntilMaintenance);
        } else if (status.equals("ONGOING")) {
            return "Vehicle maintenance in progress";
        } else {
            return "Unknown maintenance status";
        }
    }
}
