package model.Class.vehicle;

import java.sql.Date;
import java.sql.Timestamp;

public class Maintenance {
    private int maintenanceId;
    private int driverId;
    private int adminId;
    private Date scheduleDate;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Maintenance(int maintenanceId, int driverId, int adminId, Date scheduleDate, String status, Timestamp createdAt, Timestamp updatedAt) {
        this.maintenanceId = maintenanceId;
        this.driverId = driverId;
        this.adminId = adminId;
        this.scheduleDate = scheduleDate;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getMaintenanceId() {
        return maintenanceId;
    }

    public int getDriverId() {
        return driverId;
    }

    public int getAdminId() {
        return adminId;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
}
