package controller;

import model.Class.db.DatabaseHandler;
import model.Class.location.Location;
import model.Class.restaurant.Restaurant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManagingResto {
    private static DatabaseHandler conn;

    public static ArrayList<Restaurant> getData(){
        ArrayList<Restaurant> data = new ArrayList<>();

        conn = new DatabaseHandler();
        conn.connect();
        String query = "SELECT * FROM storelocation JOIN stores ON storelocation.store_id = stores.store_id JOIN regions ON storelocation.region_id = regions.region_id";

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Location loc = new Location(rs.getInt("region_id"), rs.getString("village"), rs.getString("district"), rs.getDouble("latitude") , rs.getDouble("longitude"), rs.getString("address"));

                Restaurant temp = new Restaurant(rs.getString("store_name"), rs.getInt("store_id"), rs.getString("phone_number"),loc, rs.getFloat("rating"));

                data.add(temp);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static boolean validatingInput(String name, String addres, String phone){
        return (name.isEmpty() ||addres.isEmpty() || phone == null);
    }

    public static boolean registeringRestaurant(String name, String address,String phone_number, int villId){
        conn = new DatabaseHandler();
        conn.connect();

        int restoID=0;
        String queryInsertS = "INSERT INTO stores (store_name,address,phone_number,rating) VALUES(?,?,?,?) ";
        String queryInsertL = "INSERT INTO storelocation VALUES (?,?,?)";
        String queryGetId = "SELECT store_id FROM stores ORDER BY store_id DESC LIMIT 1";

        try{
            PreparedStatement stmt = conn.con.prepareStatement(queryInsertS);
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, phone_number);
            stmt.setFloat(4, 0 );
            stmt.executeUpdate();

            Statement stmtGet = conn.con.createStatement();
            ResultSet rs = stmtGet.executeQuery(queryGetId);
            while (rs.next()) {
                restoID = rs.getInt("store_id");
            }

            PreparedStatement stmt2 = conn.con.prepareStatement(queryInsertL);
            stmt2.setInt(1, restoID);
            stmt2.setInt(2, villId);
            stmt2.setString(3, "Bandung");
            stmt2.executeUpdate();

            conn.disconnect();
        }catch(SQLException e){
            e.printStackTrace();
            conn.disconnect();
            return (false);
        }

        return (true);
    }

    public static boolean deleteRestaurant(int id){
        conn = new DatabaseHandler();
        conn.connect();

        String query = "DELETE FROM storelocation JOIN stores ON storelocation.store_id = stores.store_id WHERE store_id = "+id;
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

    public static boolean updatingResto(int idResto, String name, String address,String phone_number,float rating ,int villId){
        conn = new DatabaseHandler();
        conn.connect();

        String queryUpdate = "UPDATE storelocation JOIN stores ON storelocation.store_id = stores.store_id SET " +
                "store_name = ?, " +
                "address = ?, " +
                "phone_number = ?, " +
                "rating = ?, " +
                "region_id = ? " +
                "WHERE stores.store_id = "+idResto;

        try{
            PreparedStatement stmt = conn.con.prepareStatement(queryUpdate);

            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, phone_number);
            stmt.setFloat(4, rating);
            stmt.setInt(5, villId);
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
