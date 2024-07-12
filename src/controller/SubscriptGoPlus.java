package controller;

import model.Class.SingletonManagerCustomer;
import model.Class.db.DatabaseHandler;
import model.Class.transaction.GoPlus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class SubscriptGoPlus {
    public static boolean subscribe(String type){
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();
        int id=0;
        String desc="";
        GoPlus subs;
        double harga,potongan;

//        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        Date now = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        if(type.equalsIgnoreCase("weekly")){
            calendar.add(Calendar.DAY_OF_YEAR, 7);
            desc = "save up to 4.000.000";
            harga = 10000;
            potongan = 5000;
        }else if(type.equalsIgnoreCase("monthly")){
            calendar.add(Calendar.DAY_OF_YEAR, 30);
            desc = "save up to 8.260.000";
            harga = 15000;
            potongan = 10000;
        }else{
            calendar.add(Calendar.DAY_OF_YEAR, 360);
            desc = "save up to 24.800.000";
            harga = 34000;
            potongan = 20000;
        }

        Date expDate = calendar.getTime();

        String queryInsert = "INSERT INTO subscription VALUES(?,?,?,?,?,?,?,?)";
        String queryGet = "SELECT subs_id FROM subscription ORDER BY subs_id DESC LIMIT 1";

        try{
            conn.con.setAutoCommit(false);
            PreparedStatement stmt = conn.con.prepareStatement(queryInsert);
            stmt.setInt(1, 0);
            stmt.setInt(2, SingletonManagerCustomer.getInstance().getCustomer().getUserID());
            stmt.setString(3, type);
            stmt.setDouble(4, harga);
            stmt.setDouble(5, potongan);
            stmt.setDate(6, new java.sql.Date(now.getTime()));
            stmt.setDate(7, new java.sql.Date(expDate.getTime()));
            stmt.setInt(8,1);
            stmt.executeUpdate();

            Statement usernameStatement = conn.con.createStatement();
            ResultSet rs = usernameStatement.executeQuery(queryGet);
            while(rs.next()){
                id = rs.getInt("subs_id");
            }

            conn.con.commit();
            conn.disconnect();
        }catch(SQLException e){
            e.printStackTrace();
            conn.disconnect();
            return (false);
        }

        subs = new GoPlus(id, type, now, expDate, harga,potongan, true);
        SingletonManagerCustomer.getInstance().getCustomer().setGojekPlus(subs);
        return true;
    }
}
