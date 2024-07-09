package view;

import controller.CreateOrder;
import model.Class.user.Customer;
import model.Enum.TipeBarang;
import model.Enum.TypeOfService;

import javax.swing.*;
import java.awt.*;

public class GoSendPage {

    private static final int FRAME_WIDTH = 450;
    private static final int FRAME_HEIGHT = 300;
    private static final int LEFT_MARGIN = 10;
    private static final int RIGHT_MARGIN = 20;

    public GoSendPage(Customer customer) {
        showGoSendPage(customer);
    }

    private void showGoSendPage(Customer customer) {
        JFrame frame = createFrame();
        frame.setLayout(null);

        JLabel titleLabel = createLabel("Go Send Service", LEFT_MARGIN, 10, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        frame.add(titleLabel);

        JLabel locationLabel = createLabel("Current Location:", LEFT_MARGIN, 50, 120, 20);
        frame.add(locationLabel);
        JTextField locationTextField = new JTextField();
        locationTextField.setBounds(140, 50, 200, 20);
        frame.add(locationTextField);

        JLabel destinationLabel = createLabel("Destination:", LEFT_MARGIN, 80, 120, 20);
        frame.add(destinationLabel);
        JTextField destinationTextField = new JTextField();
        destinationTextField.setBounds(140, 80, 200, 20);
        frame.add(destinationTextField);

        JLabel typeLabel = createLabel("Type of Goods:", LEFT_MARGIN, 110, 120, 20);
        frame.add(typeLabel);
        TipeBarang[] types = {TipeBarang.NORMAL, TipeBarang.FRAGILE, TipeBarang.RADIOACTIVE, TipeBarang.CORROSIVE, TipeBarang.FLAMMABLE, TipeBarang.HAZARD};
        JComboBox<TipeBarang> typeComboBox = new JComboBox<>(types);
        typeComboBox.setBounds(140, 110, 200, 20);
        frame.add(typeComboBox);

        JButton orderButton = new JButton("Make Order");
        orderButton.setBounds(150, 150, 150, 30);
        orderButton.addActionListener( e -> {

            if (CreateOrder.createGoOrder(customer.getUserID(), TypeOfService.GOSEND, locationTextField.getText(), destinationTextField.getText())) {
                JOptionPane.showMessageDialog(null, "Order Successfully created", "Notification", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new CustomerPage(customer);
            }

        });
        frame.add(orderButton);

        JButton backButton = new JButton("Back to Main Menu");
        backButton.setBounds(150, 190, 150, 30);
        backButton.addActionListener(e -> {
            frame.dispose();
            new CustomerPage(customer);
        });
        frame.add(backButton);

        frame.setVisible(true);

    }

    private JFrame createFrame() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2);
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2);

        JFrame frame = new JFrame("Go Send");
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return frame;
    }

    private JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        return label;
    }

}
