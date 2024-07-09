package view;

import controller.CreateOrder;
import model.Class.user.Customer;
import model.Enum.TypeOfService;

import javax.swing.*;
import java.awt.*;

public class GoCarPage {

    private static final int FRAME_WIDTH = 450;
    private static final int FRAME_HEIGHT = 300;
    private static final int LEFT_MARGIN = 10;
    private static final int RIGHT_MARGIN = 20;

    public GoCarPage(Customer customer) {
        showGoCarPage(customer);
    }

    private void showGoCarPage(Customer customer) {
        JFrame frame = createFrame();
        frame.setLayout(null);

        JLabel titleLabel = createLabel("Go Car Service", LEFT_MARGIN, 10, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        frame.add(titleLabel);

        JLabel currentLocationLabel = createLabel("Current Location:", LEFT_MARGIN, 50, 150, 30);
        frame.add(currentLocationLabel);

        JTextField currentLocationField = createTextField(LEFT_MARGIN + 150, 50, FRAME_WIDTH - 2 * LEFT_MARGIN - 180, 30);
        frame.add(currentLocationField);

        JLabel destinationLabel = createLabel("Destination:", LEFT_MARGIN, 100, 150, 30);
        frame.add(destinationLabel);

        JTextField destinationField = createTextField(LEFT_MARGIN + 150, 100, FRAME_WIDTH - 2 * LEFT_MARGIN - 180, 30);
        frame.add(destinationField);

        JButton orderButton = createButton("Make an Order", LEFT_MARGIN, 150, FRAME_WIDTH - 2 * LEFT_MARGIN - 30, 30);
        orderButton.addActionListener( e -> {

            if (CreateOrder.createGoOrder(customer.getUserID(), TypeOfService.GOCAR, currentLocationField.getText(), destinationField.getText())) {
                JOptionPane.showMessageDialog(null, "Order Successfully created", "Notification", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new CustomerPage(customer);
            }

        });
        frame.add(orderButton);

        JButton backButton = createButton("Back to Main Menu", LEFT_MARGIN, 200, FRAME_WIDTH - 2 * LEFT_MARGIN - 30, 30);
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

        JFrame frame = new JFrame("Go Car");
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return frame;
    }

    private JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        return label;
    }

    private JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        return textField;
    }

    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        return button;
    }

}
