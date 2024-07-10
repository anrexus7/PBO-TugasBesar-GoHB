package model;

import java.time.LocalDate;

public class Maintenance {
    private int maintenanceId;
    private int driverId;
    private String status;
    private LocalDate scheduleDate;

    public Maintenance(int maintenanceId, int driverId, String status, LocalDate scheduleDate) {
        this.maintenanceId = maintenanceId;
        this.driverId = driverId;
        this.status = status;
        this.scheduleDate = scheduleDate;
    }

    public int getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(int maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }
}
