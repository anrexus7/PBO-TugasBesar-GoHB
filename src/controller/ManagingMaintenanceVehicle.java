package controller;

import model.Class.db.DatabaseHandler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class ManagingMaintenanceVehicle {
    private static DatabaseHandler conn;

    public static ArrayList<Object[]> getData(){
        ArrayList<Object[]> data = new ArrayList<>();

        conn = new DatabaseHandler();
        conn.connect();
        String query = "SELECT * FROM vehiclemaintenance JOIN vehicle ON vehiclemaintenance.vehicle_id = vehicle.vehicle_id";

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Object[] temp = new Object[5];
                temp[0] = rs.getInt("maintenance_id");
                temp[1] = rs.getInt("vehicle_id");
                temp[2] = rs.getInt("driver_id");
                temp[3] = rs.getDate("schedule_date");
                temp[4] = rs.getString("status");

                data.add(temp);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static boolean validatingInput(String id, Object date){
        return (id.isEmpty()|| date == null);
    }

    public static boolean registeringMaintenance(int id, Object dateMaintenance){
        conn = new DatabaseHandler();
        conn.connect();

        String queryInsertS = "INSERT INTO vehiclemaintenance (vehicle_id,admin_id, schedule_date,status) VALUES(?,?,?,?) ";
        Date mainte = (Date) dateMaintenance;

        try{
            PreparedStatement stmt = conn.con.prepareStatement(queryInsertS);
            stmt.setInt(1, id);
            stmt.setInt(2, 1);
            stmt.setDate(3, new java.sql.Date(mainte.getTime()));
            stmt.setString(4, "SCHEDULED");
            stmt.executeUpdate();

            conn.disconnect();
        }catch(SQLException e){
            e.printStackTrace();
            conn.disconnect();
            return (false);
        }

        return (true);
    }

    public static boolean deleteMaintenance(int id){
        conn = new DatabaseHandler();
        conn.connect();

        String query = "DELETE FROM vehiclemaintenance WHERE maintenance_id = "+id;
        try{
            Statement stmt = conn.con.createStatement();
            int check = stmt.executeUpdate(query);
            conn.disconnect();

            if(check==0){
                return (false);
            }
            return (true);
        }catch(SQLException e) {
            e.printStackTrace();
            conn.disconnect();
            return (false);
        }
    }

    public static boolean updatingMainte(int idMainte, String status, Object date){
        conn = new DatabaseHandler();
        conn.connect();

        String queryUpdate = "UPDATE vehiclemaintenance " +
                "schedule_date = ?, " +
                "status = ?, " +
                "WHERE maintenance_id = "+idMainte;

        Date tempDate = (Date) date;

        try{
            PreparedStatement stmt = conn.con.prepareStatement(queryUpdate);

            stmt.setDate(1,  new java.sql.Date(tempDate.getTime()));
            stmt.setString(2, status);
            int check = stmt.executeUpdate();
            conn.disconnect();

            if(check==0){
                return (false);
            }
            return (true);

        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
}
