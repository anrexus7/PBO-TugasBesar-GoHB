package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Class.SingletonManagers.SingletonManagerCustomer;
import model.Class.db.DatabaseHandler;
import model.Class.location.Location;
import model.Class.location.Region;
import model.Class.order.GoRide;
import model.Class.order.Order;
import model.Enum.StatusOrder;
import model.Enum.TypeOfService;
import model.Enum.VehicleType;

public class CustomerOrder {
    public static boolean createOrder(TypeOfService service, VehicleType vehicle, String currLoc, int currLocRegionID, String destination, int destinationRegionID, double cost) {
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();

        String query = "INSERT INTO orders(customer_id, service_type, vehicle_type, "
                + "current_location, region_id_current, destination, region_id_destination, cost, "
                + "order_status, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, SingletonManagerCustomer.getInstance().getCustomer().getUserID());
            stmt.setString(2, service.toString());
            stmt.setString(3, vehicle.toString());
            stmt.setString(4, currLoc);
            stmt.setInt(5, currLocRegionID);
            stmt.setString(6, destination);
            stmt.setInt(7, destinationRegionID);
            stmt.setDouble(8, cost);
            stmt.setString(9, StatusOrder.ASSIGNED.toString());
            stmt.setTimestamp(10, new java.sql.Timestamp(System.currentTimeMillis()));
            stmt.setTimestamp(11, new java.sql.Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
            conn.disconnect();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            conn.disconnect();
            return (false);
        }
    }

    public interface OrderView {
        void displayOrders(List<Order> orders);
        void displayError(String message);
    }

    private OrderView view;

    public CustomerOrder(OrderView view) {
        this.view = view;
    }

    public void loadOrderHistory(int driverId) {
        try {
            List<Order> orders = fetchOrder(driverId);
            view.displayOrders(orders);
        } catch (SQLException ex) {
            ex.printStackTrace();
            view.displayError("Error loading order history: " + ex.getMessage());
        }
    }

    public static List<Order> fetchOrder(int customerID) throws SQLException {
        List<Order> orders = new ArrayList<>();
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();

        String query = "SELECT * FROM orders WHERE customer_id = "+ customerID;
        Statement stmt = conn.con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int currLocRegionID = rs.getInt("region_id_current");
            int destinationRegionID = rs.getInt("region_id_destination");

            Region currLocRegion = FetchDataRegion.getRegion(currLocRegionID);
            Region destinationRegion = FetchDataRegion.getRegion(destinationRegionID);

            Location currentLocation = new Location(currLocRegionID, currLocRegion.getVillage(),
                    currLocRegion.getDistrict(), currLocRegion.getLatitude(), currLocRegion.getLongitude(),
                    rs.getString("current_location"));
            Location destinationLocation = new Location(destinationRegionID, destinationRegion.getVillage(),
                    destinationRegion.getDistrict(), destinationRegion.getLatitude(), destinationRegion.getLongitude(),
                    rs.getString("destination"));
            
            if (currentLocation != null && destinationLocation != null) {
                Order order = new GoRide(
                        StatusOrder.valueOf(rs.getString("order_status")),
                        new Date(rs.getTimestamp("created_at").getTime()),
                        rs.getDouble("cost"),
                        TypeOfService.valueOf(rs.getString("service_type")),
                        VehicleType.valueOf(rs.getString("vehicle_type")),
                        null,
                        rs.getInt("driver_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("order_id"),
                        currentLocation,
                        destinationLocation
                );

                orders.add(order);
            }
        }

        return orders;
    }


}
