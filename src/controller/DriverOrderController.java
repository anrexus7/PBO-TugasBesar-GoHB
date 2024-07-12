package controller;

import model.Class.SingletonManagers.SingletonManagerDriver;
import model.Class.order.Order;

import java.sql.SQLException;
import java.util.List;

public class DriverOrderController {

    public interface OrderView {
        void displayOrders(List<Order> orders);
        void displayIncomes(String incomes);
        void displayError(String message);
    }

    private OrderView view;

    public DriverOrderController(OrderView view) {
        this.view = view;
    }

    public void loadOrders() {
        try {
            List<Order> orders = DriverOrderService.fetchOrders(SingletonManagerDriver.getInstance().getDriver().getVehicle().getVehicleType());
            view.displayOrders(orders);
        } catch (SQLException ex) {
            ex.printStackTrace();
            view.displayError("Error loading orders: " + ex.getMessage());
        }
    }

    public void confirmOrder(int orderId) {
        try {
            DriverOrderService.confirmOrder(orderId);
            view.displayError("Order confirmed!");
            loadOrders();
        } catch (SQLException ex) {
            ex.printStackTrace();
            view.displayError("Error confirming order: " + ex.getMessage());
        }
    }

    public void completeOrder(int orderId) {
        try {
            DriverOrderService.completeOrder(orderId);
            view.displayError("Order completed!");
            loadOrders();
        } catch (SQLException ex) {
            ex.printStackTrace();
            view.displayError("Error completing order: " + ex.getMessage());
        }
    }

    public static Order getCurrentOrder(int driverId) throws SQLException {
        return DriverOrderService.fetchCurrentOrder(driverId);
    }

    public void loadOrderHistory(int driverId) {
        try {
            List<Order> orders = DriverOrderService.fetchCompletedOrders(driverId);
            view.displayOrders(orders);
        } catch (SQLException ex) {
            ex.printStackTrace();
            view.displayError("Error loading order history: " + ex.getMessage());
        }
    }

    public void calculateDriverIncomes(int driverId) {
        try {
            double totalIncomes = DriverOrderService.calculateDriverIncomes(driverId);
            view.displayIncomes("Total Incomes: $" + totalIncomes);
        } catch (SQLException ex) {
            ex.printStackTrace();
            view.displayError("Error calculating incomes: " + ex.getMessage());
        }
    }

}
