package view;

import javax.swing.*;
import java.awt.*;

public class LoginPage {

    final int FRAME_WIDTH = 300;
    final int FRAME_HEIGHT = 240;

    public LoginPage() {
        showLoginPage();
    }

    private void showLoginPage() {

        JFrame frame = createFrame();
        JPanel panel = createPanel();

        frame.add(panel);

        JLabel titleWelcome = createLabel("WELCOME TO", 75, 10, 150, 25, 20);
        panel.add(titleWelcome);

        JLabel titleGOHB = createLabel("GO HB", 110, 30, 80, 25, 20);
        panel.add(titleGOHB);

        JLabel userLabel = createLabel("Username", 10, 70, 80, 25, 14);
        panel.add(userLabel);

        JTextField userText = createTextField(100, 70, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = createLabel("Password", 10, 100, 80, 25, 14);
        panel.add(passwordLabel);

        JPasswordField passwordText = createPasswordField(100, 100, 165, 25);
        panel.add(passwordText);

        JButton loginButton = createButton("Login", 180, 140, 80, 25);
        panel.add(loginButton);

        JButton registerButton = createButton("Register", 10, 140, 100, 25);
        panel.add(registerButton);

        loginButton.addActionListener(e -> {
            // CHECK USERNAME & PASS KE DATABASE
        });

        registerButton.addActionListener(e -> {
            frame.dispose();
            new RegisterPage();
        });

        frame.setVisible(true);
    }

    private JFrame createFrame() {

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2);
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2);

        JFrame frame = new JFrame("Login Menu");
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        return panel;
    }

    private JLabel createLabel(String text, int x, int y, int width, int height, int fontSize) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Sans Serif", Font.BOLD, fontSize));
        return label;
    }

    private JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField(20);
        textField.setBounds(x, y, width, height);
        return textField;
    }

    private JPasswordField createPasswordField(int x, int y, int width, int height) {
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(x, y, width, height);
        return passwordField;
    }

    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        return button;
    }

}
