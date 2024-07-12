package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.MaintenanceService;
import model.Class.SingletonManagers.SingletonManagerDriver;
import model.Class.vehicle.Maintenance;

public class DriverPage {

    private static final int FRAME_WIDTH = 450;
    private static final int FRAME_HEIGHT = 400;
    private static final int LEFT_MARGIN = 10;
    private static final int RIGHT_MARGIN = 20;

    public DriverPage() {
        showDriverMenu();
    }

    private void showDriverMenu() {
        JFrame frame = createFrame();
        frame.setLayout(null);

        JLabel welcomeLabel = createLabel("Welcome, " + SingletonManagerDriver.getInstance().getDriver().getUsername(), LEFT_MARGIN, 10, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        frame.add(welcomeLabel);

        JLabel ratingLabel = createLabel("Rating: " + SingletonManagerDriver.getInstance().getDriver().getRating(), LEFT_MARGIN, 30, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        frame.add(ratingLabel);

        JButton viewProfileButton = createButton("View Profile", LEFT_MARGIN, 60, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        viewProfileButton.addActionListener(e -> {
            frame.dispose();
            new ViewProfileDriver();
        });
        frame.add(viewProfileButton);

        JButton reportButton = createButton("Report", LEFT_MARGIN, 100, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        reportButton.addActionListener(e -> {
            frame.dispose();
            new ReportPageDriver();
        });
        frame.add(reportButton);

        JButton viewOrderButton = createButton("View Order", LEFT_MARGIN, 140, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        viewOrderButton.addActionListener(e -> {
            try {
                Maintenance maintenance = MaintenanceService.fetchNextMaintenance(SingletonManagerDriver.getInstance().getDriver().getDriverId());
                String message = MaintenanceService.getMaintenanceStatusMessage(maintenance);
                if ("ONGOING".equalsIgnoreCase(maintenance.getStatus())) {
                    JOptionPane.showMessageDialog(null, message, "Maintenance Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    frame.dispose();
                    new DriverOrderPage();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error checking maintenance status: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        frame.add(viewOrderButton);

        JButton viewCurrentOrderButton = createButton("View Current Order", LEFT_MARGIN, 180, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        viewCurrentOrderButton.addActionListener(e -> {
            frame.dispose();
            new DriverCurrentOrderPage();
        });
        frame.add(viewCurrentOrderButton);

        JButton orderHistoryButton = createButton("Order History", LEFT_MARGIN, 220, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        orderHistoryButton.addActionListener(e -> {
            frame.dispose();
            new DriverOrderHistoryPage();
        });
        frame.add(orderHistoryButton);

        JButton checkMaintenanceButton = createButton("Check Maintenance", LEFT_MARGIN, 260, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        checkMaintenanceButton.addActionListener(e -> {
            try {
                Maintenance maintenance = MaintenanceService.fetchNextMaintenance(SingletonManagerDriver.getInstance().getDriver().getDriverId());
                String message = MaintenanceService.getMaintenanceStatusMessage(maintenance);
                JOptionPane.showMessageDialog(null, message, "Maintenance Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error checking maintenance status: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        frame.add(checkMaintenanceButton);

        JButton logoutButton = createButton("Logout", LEFT_MARGIN, 300, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Logging out...");
            SingletonManagerDriver.getInstance().logout();
            new LoginPage();
            frame.dispose();
        });
        frame.add(logoutButton);

        frame.setVisible(true);
    }

    private JFrame createFrame() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2);
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2);

        JFrame frame = new JFrame("Driver Menu");
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        return label;
    }

    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        return button;
    }
}
