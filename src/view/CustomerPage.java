package view;

import model.Class.user.Customer;

import javax.swing.*;
import java.awt.*;

public class CustomerPage {

    private static final int FRAME_WIDTH = 450;
    private static final int FRAME_HEIGHT = 400;
    private static final int LEFT_MARGIN = 10;
    private static final int RIGHT_MARGIN = 20;

    public CustomerPage(Customer customer) {
        showCustomerPage(customer);
    }

    private void showCustomerPage(Customer customer) {
        JFrame frame = createFrame();
        frame.setLayout(null);

        JLabel profileLabel = createLabel("Welcome, " + customer.getUsername(), LEFT_MARGIN, 10, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        frame.add(profileLabel);

        JLabel walletLabel = createLabel("Wallet Balance: $" + customer.getWallet().getSaldo(), LEFT_MARGIN, 50, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN - 100, 30);
        frame.add(walletLabel);

        JLabel coinsLabel = createLabel("Coins : " + customer.getWallet().getCoins(), LEFT_MARGIN, 70, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN - 100, 30);
        frame.add(coinsLabel);

        JButton topUpButton = createButton("Top Up", FRAME_WIDTH - RIGHT_MARGIN - 100, 50, 100, 30);
        topUpButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Top-up functionality is not implemented yet."));
        frame.add(topUpButton);

        JButton gorideButton = createButton("Go Ride", LEFT_MARGIN, 110, FRAME_WIDTH / 2 - LEFT_MARGIN - RIGHT_MARGIN, 30);
        frame.add(gorideButton);
        gorideButton.addActionListener(e->{
            frame.dispose();
            new GoRidePage(customer);
        });

        JButton gocarButton = createButton("Go Car", LEFT_MARGIN + FRAME_WIDTH / 2, 110, FRAME_WIDTH / 2 - LEFT_MARGIN - RIGHT_MARGIN, 30);
        frame.add(gocarButton);
        gocarButton.addActionListener(e ->{
            frame.dispose();
            new GoCarPage(customer);
        });

        JButton gofoodButton = createButton("Go Food", LEFT_MARGIN, 150, FRAME_WIDTH / 2 - LEFT_MARGIN - RIGHT_MARGIN, 30);
        frame.add(gofoodButton);
        gofoodButton.addActionListener(e->{
            frame.dispose();
            new GoFoodPage(customer);
        });

        JButton gosendButton = createButton("Go Send", LEFT_MARGIN + FRAME_WIDTH / 2, 150, FRAME_WIDTH / 2 - LEFT_MARGIN - RIGHT_MARGIN, 30);
        frame.add(gosendButton);
        gosendButton.addActionListener(e->{
            frame.dispose();
            new GoSendPage(customer);
        });

        JButton orderHistoryButton = createButton("Order History", LEFT_MARGIN, 200, FRAME_WIDTH / 3 - LEFT_MARGIN - RIGHT_MARGIN, 30);
        orderHistoryButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Order History functionality is not implemented yet."));
        frame.add(orderHistoryButton);

        JButton notificationButton = createButton("Inbox", LEFT_MARGIN + FRAME_WIDTH / 3, 200, FRAME_WIDTH / 3 - LEFT_MARGIN - RIGHT_MARGIN, 30);
        notificationButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Inbox functionality is not implemented yet."));
        frame.add(notificationButton);

        JButton reportButton = createButton("Report", LEFT_MARGIN + 2 * FRAME_WIDTH / 3, 200, FRAME_WIDTH / 3 - LEFT_MARGIN - RIGHT_MARGIN, 30);
        reportButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Report functionality is not implemented yet."));
        frame.add(reportButton);

        JButton viewProfileButton = createButton("View Profile", LEFT_MARGIN + FRAME_WIDTH / 3, 250, FRAME_WIDTH / 3 - LEFT_MARGIN - RIGHT_MARGIN, 30);
        viewProfileButton.addActionListener(e -> {
            frame.dispose();
            new ViewProfileCustomer(customer);
        });
        frame.add(viewProfileButton);

        frame.setVisible(true);

    }

    private JFrame createFrame() {

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2);
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2);

        JFrame frame = new JFrame("GoHB Customer Page");
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
