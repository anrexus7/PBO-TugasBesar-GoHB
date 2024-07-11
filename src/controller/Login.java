package controller;

import model.Class.db.DatabaseHandler;
import model.Class.transaction.GoPay;
import model.Class.transaction.GoPlus;
import model.Class.user.Customer;
import model.Class.user.Driver;
import model.Class.user.User;
import model.Class.vehicle.Vehicle;
import model.Enum.DriverAvailability;
import model.Enum.UserType;
import model.Enum.VehicleType;

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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        GoPay wallet = new GoPay();
        String queryWallet = "SELECT * FROM userbalances WHERE user_id = '" + dbData.getUserID() + "'";
        try {
            Statement walletStatement = conn.con.createStatement();
            ResultSet rs = walletStatement.executeQuery(queryWallet);
            while (rs.next()) {
                wallet = new GoPay(rs.getInt("wallet_id"),rs.getDouble("balance"),rs.getDouble("coin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        User user;
        if(dbData.getUserType().equals(UserType.CUSTOMER)){

            user = new Customer(dbData.getUserID(),dbData.getUsername(),dbData.getName(),dbData.getPassword(),
                    dbData.getPhoneNumber(),dbData.getEmail(),dbData.getUserType(), wallet);

        }else if(dbData.getUserType().equals(UserType.DRIVER)) {
            Vehicle vehicle = null;
            int driverID = 0;
            DriverAvailability avail = null;
            float rating = 0;
            try {
                conn.con.setAutoCommit(false);

                String getDriverID = "SELECT * FROM drivers WHERE user_id = " + dbData.getUserID();
                Statement driverIdStatement = conn.con.createStatement();
                ResultSet rs = driverIdStatement.executeQuery(getDriverID);
                while (rs.next()) {
                    driverID = rs.getInt("driver_id");
                    rating = rs.getFloat("rating");
                    avail = DriverAvailability.valueOf(rs.getString("status"));
                }
                String driverVehicle = "SELECT * FROM vehicle WHERE driver_id = " + driverID ;
                Statement driverStatement = conn.con.createStatement();
                ResultSet rs2 = driverStatement.executeQuery(driverVehicle);
                while (rs2.next()) {
                    VehicleType vehicleType = VehicleType.valueOf(rs2.getString("vehicle_type"));
                    vehicle = new Vehicle(rs2.getInt("driver_id"), rs2.getString("vehicle_plat"),
                            rs2.getInt("vehicle_id"), vehicleType);
                }
                conn.con.commit();
                conn.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            user = new Driver(dbData.getUserID(), dbData.getUsername(), dbData.getName(), dbData.getPassword(),
                    dbData.getPhoneNumber(), dbData.getEmail(), dbData.getUserType(), wallet,
                    driverID, vehicle, avail, rating);

        }else{
            user = new User();//untuk admin i guess ???
        }

        return (user);
    }
}
