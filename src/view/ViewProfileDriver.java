package view;

import controller.UpdateProfileDriver;
import model.Class.SingletonManagerDriver;

import javax.swing.*;
import java.awt.*;

public class ViewProfileDriver {

    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 300;
    private static final int LEFT_MARGIN = 10;
    private static final int RIGHT_MARGIN = 20;

    public ViewProfileDriver() {

        showViewProfileDriver();

    }

    private static void showViewProfileDriver() {

        JFrame frame = createFrame();
        frame.setLayout(null);

        JLabel nameLabel = createLabel("Username:", LEFT_MARGIN, 10, 100, 30);
        frame.add(nameLabel);

        JTextField nameField = createTextField(LEFT_MARGIN + 100, 10, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN - 100, 30);
        nameField.setText(SingletonManagerDriver.getInstance().getDriver().getUsername());
        frame.add(nameField);

        JLabel emailLabel = createLabel("Email:", LEFT_MARGIN, 50, 100, 30);
        frame.add(emailLabel);

        JTextField emailField = createTextField(LEFT_MARGIN + 100, 50, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN - 100, 30);
        emailField.setText(SingletonManagerDriver.getInstance().getDriver().getEmail());
        frame.add(emailField);

        JLabel phoneLabel = createLabel("Phone:", LEFT_MARGIN, 90, 100, 30);
        frame.add(phoneLabel);

        JTextField phoneField = createTextField(LEFT_MARGIN + 100, 90, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN - 100, 30);
        phoneField.setText(SingletonManagerDriver.getInstance().getDriver().getPhoneNumber());
        frame.add(phoneField);

        JButton saveButton = createButton("Save", LEFT_MARGIN, 140, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        saveButton.addActionListener(e -> {
            if (UpdateProfileDriver.update(nameField.getText(), emailField.getText(), phoneField.getText())) {
                JOptionPane.showMessageDialog(null, "Profile updated!");
                frame.dispose();
                new DriverPage();
            }
        });
        frame.add(saveButton);

        JButton backButton = createButton("Back", LEFT_MARGIN, 180, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        backButton.addActionListener(e -> {
            frame.dispose();
            new DriverPage();
        });
        frame.add(backButton);

        frame.setVisible(true);

    }

    private static JFrame createFrame() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2);
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2);

        JFrame frame = new JFrame("Edit Profile");
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return frame;
    }

    private static JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        return label;
    }

    private static JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        return textField;
    }

    private static JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        return button;
    }

}
