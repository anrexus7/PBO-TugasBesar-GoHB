package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Class.db.DatabaseHandler;
import model.Class.location.Region;

public class FetchDataRegion {
    private static DatabaseHandler conn = new DatabaseHandler();

    public static ArrayList<Region> getRegions() {
        ArrayList<Region> regions = new ArrayList<>();

        try {
            conn.connect();
            String query = "SELECT * FROM regions";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Region region = new Region(rs.getInt("region_id"), rs.getString("village"), rs.getString("district"), rs.getDouble("latitude"), rs.getDouble("longitude"));
                regions.add(region);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return regions;
    }

    public static Region getRegion(int regionID) {
        Region region = new Region();

        try {
            conn.connect();
            String query = "SELECT * FROM regions WHERE region_id = ?";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                region.setRegionID(regionID);
                region.setVillage(rs.getString("village"));
                region.setDistrict(rs.getString("district"));
                region.setLatitude(rs.getDouble("latitude"));
                region.setLongitude(rs.getDouble("longitude"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return region;
    }

    public static ArrayList<String> getVillages(){
        conn.connect();
        String query = "SELECT * FROM regions";
        ArrayList<String> regions = new ArrayList<>();

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Region temp = new Region(rs.getInt("region_id"), rs.getString("village"),
                        rs.getString("district"), rs.getDouble("latitude"), rs.getDouble("longitude") );

                regions.add(temp.getVillage());
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return regions;
    }
}
