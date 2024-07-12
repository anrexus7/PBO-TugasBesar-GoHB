package controller;

import model.Class.db.DatabaseHandler;
import model.Class.transaction.GoPlus;
import model.Class.user.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManagingCustomer {
    public static ArrayList<Customer> getData(){
        ArrayList<Customer> customers = new ArrayList<>();
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();
        String query = "SELECT * FROM users WHERE user_type = 'CUSTOMER' ";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Customer temp = new Customer();
                temp.setUserID(rs.getInt("user_id"));
                temp.setUsername(rs.getString("username"));
                temp.setEmail(rs.getString("email"));
                temp.setPhoneNumber(rs.getString("phone_number"));

                String querySubs = "SELECT * FROM subscription WHERE user_id ="+temp.getUserID();
                Statement stmt2 = conn.con.createStatement();
                ResultSet rs2 = stmt2.executeQuery(querySubs);
                boolean statusSubs;
                GoPlus subs = null;

                while(rs2.next()){
                    if(rs2.getByte("status")==0){
                        statusSubs = false;
                    }else{
                        statusSubs = true;
                    }
                    subs = new GoPlus(rs2.getInt("subs_id"),rs2.getString("subs_type"),
                            rs2.getDate("valid_from"), rs2.getDate("valid_to"),
                            rs2.getDouble("price"),rs2.getDouble("discount"),statusSubs);
                }

                temp.setGojekPlus(subs);
                customers.add(temp);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }
}
