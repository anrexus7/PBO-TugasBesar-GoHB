package controller;

import model.Class.db.DatabaseHandler;
import model.Class.restaurant.Item;
import model.Enum.KategoriItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManagingItems {
    private static DatabaseHandler conn;
    
    public static ArrayList<Item> getData(int restoId){
        ArrayList<Item> data = new ArrayList<>();

        conn = new DatabaseHandler();
        conn.connect();
        String query = "SELECT * FROM products Where store_id = " + restoId;

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                KategoriItem typeI = KategoriItem.valueOf(rs.getString("product_type"));
                Item temp = new Item(rs.getString("product_name"), rs.getDouble("price"),rs.getInt("stock"), typeI);
                temp.setItemID(rs.getInt("product_id"));

                data.add(temp);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static boolean validatingInput(String name,String price, String stock) {
        return (name.isEmpty() || price.isEmpty() || stock.isEmpty());
    }

    public static boolean registeringItem(int storeId, String name, String type, double price, int stock){
        conn = new DatabaseHandler();
        conn.connect();

        String queryInsert = "INSERT INTO products(store_id,product_name,product_type,price,stock) VALUES(?,?,?,?,?) ";
        KategoriItem typeI = KategoriItem.valueOf(type);
        Item item = new Item(name, price, stock, typeI);

        try{
            PreparedStatement stmt = conn.con.prepareStatement(queryInsert);
            stmt.setInt(1, storeId);
            stmt.setString(2, item.getName());
            stmt.setString(3, item.getKategori().toString());
            stmt.setDouble(4, item.getHarga());
            stmt.setInt(5, item.getStock());
            stmt.executeUpdate();

            conn.disconnect();
        }catch(SQLException e){
            e.printStackTrace();
            conn.disconnect();
            return (false);
        }

        return (true);
    }

    public static boolean deleteItem(int id){
        conn = new DatabaseHandler();
        conn.connect();

        String query = "DELETE FROM products WHERE product_id = "+id;
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

    public static boolean updatingItem(int id, String name, String type, double price, int stock){
        conn = new DatabaseHandler();
        conn.connect();

        String queryUpdate = "UPDATE products SET " +
                "product_name = ?, " +
                "product_type = ?, " +
                "price = ?, " +
                "stock = ? " +
                "WHERE product_id = "+id;

        Item temp = new Item(name, price, stock, KategoriItem.valueOf(type));

        try{
            PreparedStatement stmt = conn.con.prepareStatement(queryUpdate);

            stmt.setString(1, temp.getName());
            stmt.setString(2, temp.getKategori().toString());
            stmt.setDouble(3, temp.getHarga());
            stmt.setInt(4, temp.getStock());

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
