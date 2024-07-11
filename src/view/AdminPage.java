package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage {

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 500;
    private static final int LEFT_MARGIN = 10;
    private static final int RIGHT_MARGIN = 20;
    private static final int TOP_MARGIN = 10;
    private static final int BOTTOM_MARGIN = 30;

    public AdminPage() {
        showAdminPage();
    }

    private void showAdminPage() {

        JFrame frame = createFrame();
        frame.setLayout(null);

        JLabel titleLabel = createLabel("Admin Dashboard", LEFT_MARGIN, TOP_MARGIN, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(titleLabel);

        JButton manageUsersButton = createButton("Manage Users", LEFT_MARGIN, TOP_MARGIN + 50, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        manageUsersButton.addActionListener(e -> {
            new ManageCustomerPage();
            frame.dispose();
        });
        frame.add(manageUsersButton);

        JButton manageDriversButton = createButton("Manage Drivers", LEFT_MARGIN, TOP_MARGIN + 100, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        manageDriversButton.addActionListener(e -> {
            new ManageDriverPage();
            frame.dispose();
        });
        frame.add(manageDriversButton);

        JButton manageProductsButton = createButton("Manage Products", LEFT_MARGIN, TOP_MARGIN + 150, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        manageProductsButton.addActionListener(e -> {
                    String id = JOptionPane.showInputDialog(null, "Insert Resto ID");
                    try{
                        int idResto = Integer.parseInt(id);
                        frame.dispose();
                        new ManageItemsPage(idResto);
                    }catch(NumberFormatException x){
                        JOptionPane.showMessageDialog(null, "ID tidak valid", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
        );
        frame.add(manageProductsButton);

        JButton manageVouchersButton = createButton("Manage Vouchers", LEFT_MARGIN, TOP_MARGIN + 200, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        manageVouchersButton.addActionListener(e -> {
            new ManagePromoPage();
            frame.dispose();
        });
        frame.add(manageVouchersButton);

        JButton manageBlacklistButton = createButton("Manage Blacklist", LEFT_MARGIN, TOP_MARGIN + 250, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        manageBlacklistButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Manage Blacklist functionality is not implemented yet."));
        frame.add(manageBlacklistButton);

        JButton manageRestaurantButton = createButton("Manage Restaurant", LEFT_MARGIN, TOP_MARGIN + 300, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        manageRestaurantButton.addActionListener(e -> {
            frame.dispose();
            new ManageRestaurantPage();
        });
        frame.add(manageRestaurantButton);

        JButton generateReportsButton = createButton("Generate Reports", LEFT_MARGIN, TOP_MARGIN + 350, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        generateReportsButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Generate Reports functionality is not implemented yet."));
        frame.add(generateReportsButton);

        JButton settingsButton = createButton("Settings", LEFT_MARGIN, FRAME_HEIGHT - BOTTOM_MARGIN - 50, FRAME_WIDTH / 2 - LEFT_MARGIN - RIGHT_MARGIN / 2, 30);
        settingsButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Settings functionality is not implemented yet."));
        frame.add(settingsButton);

        JButton logoutButton = createButton("Logout", FRAME_WIDTH / 2 + RIGHT_MARGIN / 2, FRAME_HEIGHT - BOTTOM_MARGIN - 50, FRAME_WIDTH / 2 - LEFT_MARGIN - RIGHT_MARGIN / 2 - 10, 30);
        logoutButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                System.exit(0); // Exit the application
            }
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

        JFrame frame = new JFrame("Admin Dashboard");
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
        button.addActionListener(new AdminButtonListener());

        return button;

    }

    private static class AdminButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
        }

    }

}
