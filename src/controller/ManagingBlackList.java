package controller;

import model.Class.db.DatabaseHandler;
import model.Class.user.User;
import model.Enum.UserType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManagingBlackList {
    private static DatabaseHandler conn;

    public static ArrayList<User> getData(){
        conn = new DatabaseHandler();
        conn.connect();
        ArrayList<User> users = new ArrayList<>();

        String query = "SELECT * FROM users WHERE black_list = 1";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                boolean isBL;
                if(rs.getByte("black_list")==0){
                    isBL = false;
                }else{
                    isBL = true;
                }

                User user = new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("name"), rs.getString("password"),
                        rs.getString("phone_number"), rs.getString("email"), isBL, UserType.valueOf(rs.getString("user_type")));

                users.add(user);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static boolean setBlackList(int userID){
        conn = new DatabaseHandler();
        conn.connect();
        String query = "UPDATE users SET black_list = 1" +
                " WHERE user_id = " + userID;

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

    public static boolean removeBlackList(int userID){
        conn = new DatabaseHandler();
        conn.connect();
        String query = "UPDATE users SET black_list = 0" +
                " WHERE user_id = " + userID;

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
