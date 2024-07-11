package controller;

import model.Class.db.DatabaseHandler;
import model.Class.restaurant.Item;
import model.Class.user.User;
import model.Enum.KategoriItem;
import model.Enum.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SearchFood {

    private static DatabaseHandler conn;

    public static ArrayList<Item> searchFood(String restaurant) {

        ArrayList<Item> arrFoods = new ArrayList<>();

        int id = getStoreId(restaurant);

        conn = new DatabaseHandler();
        conn.connect();

        String query = "SELECT * FROM products WHERE store_id = '" + id + "'";

        try {
            Statement statement = conn.con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {

                Item item = new Item(rs.getString("product_name"), rs.getDouble("price"), rs.getInt("stock"), KategoriItem.valueOf(rs.getString("product_type")));
                item.setItemID(rs.getInt("product_id"));
                arrFoods.add(item);

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        if (arrFoods.size() == 0) {

            return null;

        }

        return arrFoods;

    }

    public static int getStoreId(String restaurant) {

        int id = -1;

        conn = new DatabaseHandler();
        conn.connect();

        String query = "SELECT store_id FROM stores WHERE store_name = '" + restaurant + "'";

        try {
            Statement statement = conn.con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {

                id = rs.getInt("store_id");

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return id;

    }

}
