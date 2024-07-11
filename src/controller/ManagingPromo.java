package controller;

import model.Class.SingletonManagerDriver;
import model.Class.db.DatabaseHandler;
import model.Class.transaction.Promo;
import model.Enum.TypeOfService;
import org.jdatepicker.DateModel;

import java.sql.*;
import java.util.ArrayList;

public class ManagingPromo {
    private static DatabaseHandler conn;
    public static ArrayList<Promo> getData(){
        ArrayList<Promo> data = new ArrayList<>();

        conn = new DatabaseHandler();
        conn.connect();
        String query = "SELECT * FROM promos";

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                TypeOfService ts = TypeOfService.valueOf(rs.getString("service_type"));
                Promo temp = new Promo(rs.getInt("promo_id"), rs.getString("promo_code"), ts, rs.getDouble("discount"),rs.getDate("valid_from"), rs.getDate("valid_to"));

                data.add(temp);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static boolean validatingInput(String kode, String discount, Object validFrom, Object validTo) {
        return (kode.isEmpty() || discount.isEmpty() || validFrom == null || validTo == null);
    }

    public static boolean registeringPromo(String kode, String discount,Object serviceType, Object validFrom, Object validTo){
        conn = new DatabaseHandler();
        conn.connect();

        String queryInsertV = "INSERT INTO promos(promo_code,discount,service_type,valid_from,valid_to) VALUES(?,?,?,?,?) ";
        java.util.Date from = (java.util.Date) validFrom;
        java.util.Date to = (java.util.Date) validTo;

        try{
            PreparedStatement stmt = conn.con.prepareStatement(queryInsertV);
            stmt.setString(1, kode);
            stmt.setFloat(2, Float.parseFloat(discount));
            stmt.setString(3, serviceType.toString());
            stmt.setDate(4, new java.sql.Date(from.getTime()) );
            stmt.setDate(5, new java.sql.Date(to.getTime()) );
            stmt.executeUpdate();
            conn.disconnect();
        }catch(SQLException e){
            e.printStackTrace();
            conn.disconnect();
            return (false);
        }

        return (true);
    }

    public static boolean deletePromo(int id){
        conn = new DatabaseHandler();
        conn.connect();

        String query = "DELETE FROM promos WHERE promo_id = "+id;
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

    public static boolean updatingPromo(int id, String kode, String discount,Object serviceType, Object validFrom, Object validTo){
        conn = new DatabaseHandler();
        conn.connect();

        String queryUpdate = "UPDATE promos SET " +
                "promo_code =?," +
                "discount =?," +
                "service_type =?," +
                "valid_from =?," +
                "valid_to =?" +
                "WHERE promo_id="+id;

        java.util.Date from = (java.util.Date) validFrom;
        java.util.Date to = (java.util.Date) validTo;

        try{
            PreparedStatement stmt = conn.con.prepareStatement(queryUpdate);

            stmt.setString(1, kode);
            stmt.setFloat(2, Float.parseFloat(discount));
            stmt.setString(3, serviceType.toString());
            stmt.setDate(4, new java.sql.Date(from.getTime()) );
            stmt.setDate(5, new java.sql.Date(to.getTime()) );

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
