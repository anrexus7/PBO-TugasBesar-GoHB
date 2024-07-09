package controller;

import model.Class.db.DatabaseHandler;
import model.Class.transaction.GoPay;
import model.Class.user.Customer;
import model.Class.user.Driver;
import model.Class.user.User;
import model.Enum.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login {
    private static DatabaseHandler conn;
    public static boolean validatingLogin(String username, char[] password) {
        conn = new DatabaseHandler();
//        Encryptor encryptor = new Encryptor();

        String tempPassword = "";
        for (char ch : password) {
            tempPassword += ch;
        }

        String encryptPass = Encryptor.hash(tempPassword);

        conn.connect();

        try {
            Statement st = conn.con.createStatement();
            ResultSet rs = st.executeQuery("SELECT username, password FROM users");

            while (rs.next()) {
                if (username.equals(rs.getString("username")) && rs.getString("password").equals(encryptPass.trim())) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static User fetchingDataDB(String username) {

        double coins = 0;
        double balance = 0;

        conn = new DatabaseHandler();
        conn.connect();
        User dbData = new User();

        String queryUsername = "SELECT * FROM users WHERE username = '" + username + "'";
        try {
            Statement usernameStatement = conn.con.createStatement();
            ResultSet rs = usernameStatement.executeQuery(queryUsername);
            while (rs.next()) {
                UserType userType = UserType.valueOf(rs.getString("user_type"));
                dbData = new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("name"), rs.getString("password"), rs.getString("phone_number"), rs.getString("email"),userType);
                balance = rs.getDouble("balance");
                coins = rs.getDouble("coins");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        User user;
        if(dbData.getUserType().equals(UserType.CUSTOMER)){
            user = new Customer();
            ((Customer) user).setWallet(new GoPay(balance, coins));
        }else if(dbData.getUserType().equals(UserType.DRIVER)){
            user = new Driver();
        }else{
            user = new User();//untuk admin i guess ???
        }

        user.setUserID(dbData.getUserID());
        user.setUsername(dbData.getUsername());
        user.setName(dbData.getName());
        user.setPassword(dbData.getPassword());
        user.setPhoneNumber(dbData.getPhoneNumber());
        user.setEmail(dbData.getEmail());
        user.setUserType(dbData.getUserType());

        return (user);
    }
}
