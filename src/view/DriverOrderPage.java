package view;

import controller.DriverOrderController;
import model.Class.SingletonManagers.SingletonManagerDriver;
import model.Class.order.Order;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class DriverOrderPage implements DriverOrderController.OrderView {

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 400;

    private DriverOrderController controller;
    private JList<Order> orderList;
    private JTextArea orderDetailsArea;

    public DriverOrderPage() {
        controller = new DriverOrderController(this);
        showOrderPage();
    }

    private void showOrderPage() {
        JFrame frame = createFrame();
        frame.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(200, FRAME_HEIGHT));

        orderList = new JList<>();
        orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderList.setCellRenderer(new OrderListCellRenderer());
        orderList.addListSelectionListener(e -> displayOrderDetails(orderList.getSelectedValue()));
        JScrollPane listScrollPane = new JScrollPane(orderList);
        leftPanel.add(listScrollPane, BorderLayout.CENTER);

        frame.add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        orderDetailsArea = new JTextArea();
        orderDetailsArea.setEditable(false);
        JScrollPane detailsScrollPane = new JScrollPane(orderDetailsArea);
        rightPanel.add(detailsScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton confirmButton = new JButton("Confirm Order");
        confirmButton.addActionListener(e -> {
            try {
                Order currentOrder = controller.getCurrentOrder(SingletonManagerDriver.getInstance().getDriver().getDriverId());
                if (currentOrder == null) {
                    Order selectedOrder = orderList.getSelectedValue();
                    if (selectedOrder != null) {
                        controller.confirmOrder(selectedOrder.getOrderID());
                        frame.dispose();
                        new DriverPage();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "There's Ongoing Order", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                displayError("Error loading current order: " + ex.getMessage());
            }

        });
        buttonPanel.add(confirmButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose();
            new DriverPage();
        });
        buttonPanel.add(backButton);

        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(rightPanel, BorderLayout.CENTER);

        frame.setVisible(true);

        controller.loadOrders();
    }

    private JFrame createFrame() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2);
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2);

        JFrame frame = new JFrame("View Orders");
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private void displayOrderDetails(Order order) {
        if (order != null) {
            orderDetailsArea.setText("Service Type: " + order.getServiceType() + "\n"
                    + "Amount: $" + order.getAmount() + "\n"
                    + "Transaction Date: " + order.getTransactionDate() + "\n"
                    + "Order Status: " + order.getOrderStatus());
        } else {
            orderDetailsArea.setText("");
        }
    }

    @Override
    public void displayOrders(List<Order> orders) {
        DefaultListModel<Order> listModel = new DefaultListModel<>();
        for (Order order : orders) {
            listModel.addElement(order);
        }
        orderList.setModel(listModel);
    }

    @Override
    public void displayError(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    public void displayIncomes(String incomes) {

    }

    private static class OrderListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Order) {
                Order order = (Order) value;
                String displayText = String.format("%s - $%.2f", order.getServiceType(), order.getAmount());
                label.setText(displayText);
            }
            return label;
        }
    }
}
