package controller;

import model.Class.db.DatabaseHandler;
import model.Class.transaction.Promo;
import model.Enum.TypeOfService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ShowPromoCustomer {
    public static ArrayList<Promo> getData(String service) {
        ArrayList<Promo> data = new ArrayList<>();

        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();
        String query = "SELECT * FROM promos WHERE service_type = '"+ service +"'";

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                TypeOfService ts = TypeOfService.valueOf(rs.getString("service_type"));
                Promo temp = new Promo(rs.getInt("promo_id"), rs.getString("promo_code"), ts, rs.getDouble("discount"), rs.getDate("valid_from"), rs.getDate("valid_to"));

                data.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
}
