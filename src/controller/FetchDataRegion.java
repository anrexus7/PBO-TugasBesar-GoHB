package controller;

import model.Class.db.DatabaseHandler;
import model.Class.location.Region;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FetchDataRegion {
    public static ArrayList<String> getData(){
        DatabaseHandler conn = new DatabaseHandler();
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
