package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.CustomerOrder;
import model.Class.SingletonManagers.SingletonManagerCustomer;
import model.Class.order.Order;

public class CustomerOrderHistoryPage implements CustomerOrder.OrderView {

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 400;

    private CustomerOrder controller;
    private JTable orderHistoryTable;

    public CustomerOrderHistoryPage() {
        controller = new CustomerOrder(this);
        initCustomerOrderHistoryPage();
    }

    private void initCustomerOrderHistoryPage() {
        JFrame frame = new JFrame("Order History");
        frame.setLayout(new BorderLayout());

        JPanel orderHistoryPanel = new JPanel(new BorderLayout());
        orderHistoryTable = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(orderHistoryTable);
        orderHistoryPanel.add(tableScrollPane, BorderLayout.CENTER);

        frame.add(orderHistoryPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> {
            frame.dispose();
            new CustomerPage();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        int customerID = SingletonManagerCustomer.getInstance().getCustomer().getUserID();
        controller.loadOrderHistory(customerID);
    }

    @Override
    public void displayOrders(List<Order> orders) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Order ID", "Service Type", "Amount", "Transaction Date", "Order Status"});
        for (Order order : orders) {
            model.addRow(new Object[]{order.getOrderID(), order.getServiceType(), order.getAmount(), order.getTransactionDate(), order.getOrderStatus()});
        }
        orderHistoryTable.setModel(model);
    }

    @Override
    public void displayError(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    // public static void main(String[] args) {
    //     new CustomerOrderHistoryPage();
    // }
}
