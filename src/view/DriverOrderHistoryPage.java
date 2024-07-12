package view;

import controller.DriverOrderController;
import model.Class.SingletonManagers.SingletonManagerDriver;
import model.Class.order.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DriverOrderHistoryPage implements DriverOrderController.OrderView {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;

    private DriverOrderController controller;
    private JTable orderHistoryTable;
    private JTextArea incomeTextArea;

    public DriverOrderHistoryPage() {
        controller = new DriverOrderController(this);
        showOrderHistoryPage();
    }

    private void showOrderHistoryPage() {
        JFrame frame = createFrame();
        frame.setLayout(new BorderLayout());

        JPanel orderHistoryPanel = new JPanel(new BorderLayout());
        orderHistoryTable = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(orderHistoryTable);
        orderHistoryPanel.add(new JLabel("Order History"), BorderLayout.NORTH);
        orderHistoryPanel.add(tableScrollPane, BorderLayout.CENTER);

        JPanel incomePanel = new JPanel(new BorderLayout());
        incomeTextArea = new JTextArea(10, 30);
        incomeTextArea.setEditable(false);
        JScrollPane incomeScrollPane = new JScrollPane(incomeTextArea);
        incomePanel.add(new JLabel("Incomes"), BorderLayout.NORTH);
        incomePanel.add(incomeScrollPane, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, orderHistoryPanel, incomePanel);
        frame.add(splitPane, BorderLayout.CENTER);

        splitPane.setDividerLocation(FRAME_HEIGHT / 2);
        splitPane.setResizeWeight(0.5);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose();
            new DriverPage();
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        int driverId = SingletonManagerDriver.getInstance().getDriver().getDriverId();
        controller.loadOrderHistory(driverId);
        controller.calculateDriverIncomes(driverId);
    }

    private JFrame createFrame() {
        JFrame frame = new JFrame("Order History and Incomes");
        return frame;
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
    public void displayIncomes(String incomes) {
        incomeTextArea.setText(incomes);
    }

    @Override
    public void displayError(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

}
