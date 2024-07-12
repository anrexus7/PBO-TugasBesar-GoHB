package controller;

import model.Class.SingletonManagers.SingletonManagerDriver;
import model.Class.db.DatabaseHandler;
import model.Class.location.Location;
import model.Class.order.GoRide;
import model.Class.order.Order;
import model.Enum.StatusOrder;
import model.Enum.TypeOfService;
import model.Enum.VehicleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DriverOrderService {

    public static Order fetchCurrentOrder(int driverId) throws SQLException {
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();

        String query = "SELECT * FROM orders WHERE driver_id = ? AND order_status IN ('ASSIGNED', 'PICKING', 'DROPPING')";
        PreparedStatement stmt = conn.con.prepareStatement(query);
        stmt.setInt(1, driverId);
        ResultSet rs = stmt.executeQuery();

        Order currentOrder = null;
        if (rs.next()) {
            int regionIdCurrent = rs.getInt("region_id_current");
            int regionIdDestination = rs.getInt("region_id_destination");

            // Fetch current location details
            Location currentLocation = fetchRegionLocation(conn, regionIdCurrent, rs.getString("current_location"));

            // Fetch destination location details
            Location destinationLocation = fetchRegionLocation(conn, regionIdDestination, rs.getString("destination"));

            if (currentLocation != null && destinationLocation != null) {
                currentOrder = new GoRide(
                        StatusOrder.valueOf(rs.getString("order_status")),
                        new Date(rs.getTimestamp("created_at").getTime()),
                        rs.getDouble("cost"),
                        TypeOfService.valueOf(rs.getString("service_type")),
                        VehicleType.valueOf(rs.getString("vehicle_type")),
                        null,  // Assuming you need to update this with actual user or driver details
                        rs.getInt("driver_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("order_id"),
                        currentLocation,
                        destinationLocation
                );
            }
        }

        conn.disconnect();
        return currentOrder;
    }

    public static void completeOrder(int orderId) throws SQLException {
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();

        String query = "UPDATE orders SET order_status = 'COMPLETED' WHERE order_id = ?";
        PreparedStatement stmt = conn.con.prepareStatement(query);
        stmt.setInt(1, orderId);
        stmt.executeUpdate();

        conn.disconnect();
    }

    public static List<Order> fetchOrders(VehicleType driverVehicleType) throws SQLException {
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();

        String query = "SELECT * FROM orders WHERE order_status = 'ASSIGNED' AND ";

        if (VehicleType.BIKE.equals(driverVehicleType)) {
            query += "(service_type = 'GOBIKE' OR service_type = 'GOSEND' OR service_type = 'GOFOOD')";
        } else if (VehicleType.CAR.equals(driverVehicleType)) {
            query += "service_type = 'GOCAR'";
        }

        Statement stmt = conn.con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<Order> orders = new ArrayList<>();

        while (rs.next()) {

            int regionIdCurrent = rs.getInt("region_id_current");
            int regionIdDestination = rs.getInt("region_id_destination");

            Location currentLocation = fetchRegionLocation(conn, regionIdCurrent, rs.getString("current_location"));
            Location destinationLocation = fetchRegionLocation(conn, regionIdDestination, rs.getString("destination"));

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

        conn.disconnect();
        return orders;
    }

    private static Location fetchRegionLocation(DatabaseHandler conn, int regionId, String specificLocation) throws SQLException {
        String locationQuery = "SELECT * FROM regions WHERE region_id = ?";
        PreparedStatement stmtLocation = conn.con.prepareStatement(locationQuery);
        stmtLocation.setInt(1, regionId);
        ResultSet rsLocation = stmtLocation.executeQuery();

        if (rsLocation.next()) {
            return new Location(
                    rsLocation.getInt("region_id"),
                    rsLocation.getString("village"),
                    rsLocation.getString("district"),
                    rsLocation.getDouble("latitude"),
                    rsLocation.getDouble("longitude"),
                    specificLocation
            );
        }
        return null;
    }


    public static void confirmOrder(int orderId) throws SQLException {
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();

        String query = "UPDATE orders SET order_status = 'PICKING' WHERE order_id = ?";
        PreparedStatement stmt = conn.con.prepareStatement(query);
        stmt.setInt(1, orderId);
        stmt.executeUpdate();

        String query2 = "UPDATE orders SET driver_id = ? WHERE order_id = ?";
        PreparedStatement stmt2 = conn.con.prepareStatement(query2);
        stmt2.setInt(1, SingletonManagerDriver.getInstance().getDriver().getDriverId());
        stmt2.setInt(2, orderId);
        stmt2.executeUpdate();

        conn.disconnect();
    }

    public static float[] getLocationCoordinates(String location) throws SQLException {

        float[] coordinates = new float[2];

        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();

        String query = "SELECT * FROM regions WHERE village = '" + location + "'";
        PreparedStatement stmt = conn.con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {

            coordinates[0] = rs.getFloat("latitude");
            coordinates[1] = rs.getFloat("longitude");

        }

        conn.disconnect();

        return coordinates;

    }

    public static List<Order> fetchCompletedOrders(int driverId) throws SQLException {
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();

        String query = "SELECT * FROM orders WHERE driver_id = ? AND order_status = 'COMPLETED'";
        PreparedStatement stmt = conn.con.prepareStatement(query);
        stmt.setInt(1, driverId);
        ResultSet rs = stmt.executeQuery();

        List<Order> orders = new ArrayList<>();
        while (rs.next()) {
            Order order = new Order();
            order.setOrderID(rs.getInt("order_id"));
            order.setCustomerID(rs.getInt("customer_id"));
            order.setDriverID(rs.getInt("driver_id"));
            order.setAmount(rs.getDouble("cost"));
            order.setTransactionDate(rs.getDate("created_at"));
            order.setOrderStatus(StatusOrder.valueOf(rs.getString("order_status")));
            order.setServiceType(TypeOfService.valueOf(rs.getString("service_type")));
            order.setVehicleType(VehicleType.valueOf(rs.getString("vehicle_type")));

            orders.add(order);

        }

        conn.disconnect();
        return orders;
    }

    public static double calculateDriverIncomes(int driverId) throws SQLException {
        double totalIncomes = 0.0;
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();

        String query = "SELECT SUM(cost) AS total_income FROM orders WHERE driver_id = ? AND order_status = 'COMPLETED'";
        PreparedStatement stmt = conn.con.prepareStatement(query);
        stmt.setInt(1, driverId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            totalIncomes = rs.getDouble("total_income");
        }

        conn.disconnect();
        return totalIncomes;
    }

}
