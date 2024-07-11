package controller;

import model.Class.db.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GeneratingReport {
    private static DatabaseHandler conn;
    public static ArrayList<String[]> getData(){
        conn = new DatabaseHandler();
        conn.connect();
        ArrayList<String[]> data = new ArrayList<>();

        String query = "SELECT * FROM report WHERE status = 'CLOSED'";
        try{
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String[] temp = new String[4];

                temp[0] = String.valueOf(rs.getInt("report_id"));
                temp[1] = String.valueOf(rs.getInt("user_id"));
                temp[2] = rs.getString("complaint");
                temp[3] = String.valueOf(rs.getDate("created_at"));

                data.add(temp);
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static boolean changeStatus(String id){
        int reportID = Integer.parseInt(id);
        conn = new DatabaseHandler();
        conn.connect();

        String query = "UPDATE report SET status = 'OPENED'" +
                " WHERE report_id = " + reportID;

        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            int check = stmt.executeUpdate();
            conn.disconnect();

            if(check==0){
                return (false);
            }
            return (true);
        }catch(SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
}
