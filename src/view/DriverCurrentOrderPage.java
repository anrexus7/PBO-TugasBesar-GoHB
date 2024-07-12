package view;

import controller.DriverOrderController;
import model.Class.SingletonManagers.SingletonManagerDriver;
import model.Class.order.Order;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class DriverCurrentOrderPage implements DriverOrderController.OrderView {

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 400;

    private DriverOrderController controller;
    private JTextArea orderDetailsArea;

    public DriverCurrentOrderPage() {
        controller = new DriverOrderController(this);
        showCurrentOrderPage();
    }

    private void showCurrentOrderPage() {
        JFrame frame = createFrame();
        frame.setLayout(new BorderLayout());

        orderDetailsArea = new JTextArea();
        orderDetailsArea.setEditable(false);
        JScrollPane detailsScrollPane = new JScrollPane(orderDetailsArea);
        frame.add(detailsScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton completeButton = new JButton("Complete Ride");
        completeButton.addActionListener(e -> {
            try {
                Order currentOrder = DriverOrderController.getCurrentOrder(SingletonManagerDriver.getInstance().getDriver().getDriverId());
                if (currentOrder != null) {
                    controller.completeOrder(currentOrder.getOrderID());
                    frame.dispose();
                    new DriverPage();
                }
            }catch (SQLException ex){

            }
        });
        buttonPanel.add(completeButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose();
            new DriverPage();
        });
        buttonPanel.add(backButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        loadCurrentOrder();
    }

    private JFrame createFrame() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2);
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2);

        JFrame frame = new JFrame("Current Order");
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private void loadCurrentOrder() {
        try {
            Order currentOrder = controller.getCurrentOrder(SingletonManagerDriver.getInstance().getDriver().getDriverId());
            displayOrderDetails(currentOrder);
        } catch (SQLException ex) {
            ex.printStackTrace();
            displayError("Error loading current order: " + ex.getMessage());
        }
    }

    private void displayOrderDetails(Order order) {
        if (order != null) {
            orderDetailsArea.setText("Service Type: " + order.getServiceType() + "\n"
                    + "Amount: " + order.getAmount() + "\n"
                    + "Transaction Date: " + order.getTransactionDate() + "\n"
                    + "Order Status: " + order.getOrderStatus());
        } else {
            orderDetailsArea.setText("No current order.");
        }
    }

    @Override
    public void displayOrders(java.util.List<Order> orders) {
        // Not used in this context
    }

    @Override
    public void displayError(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    public void displayIncomes(String incomes) {

    }

}
