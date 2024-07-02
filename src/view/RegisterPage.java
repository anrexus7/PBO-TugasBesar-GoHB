package view;

import controller.Register;
import controller.ValidatingRegisterInput;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class RegisterPage {

    public RegisterPage() {
        showRegisterPage();
    }

    public void showRegisterPage() {
        JFrame frame = createFrame();
        JPanel panel = createPanel();

        frame.add(panel);

        JLabel titleLabel = createLabel("Register", 130, 10, 150, 25, 20);
        panel.add(titleLabel);

        JLabel userLabel = createLabel("Username", 10, 50, 80, 25, 14);
        panel.add(userLabel);

        JTextField userText = createTextField(150, 50, 165, 25);
        panel.add(userText);

        JLabel nameLabel = createLabel("Name", 10, 80, 80, 25, 14);
        panel.add(nameLabel);

        JTextField nameText = createTextField(150, 80, 165, 25);
        panel.add(nameText);

        JLabel passwordLabel = createLabel("Password", 10, 110, 80, 25, 14);
        panel.add(passwordLabel);

        JPasswordField passwordText = createPasswordField(150, 110, 165, 25);
        panel.add(passwordText);

        JLabel verifyPasswordLabel = createLabel("Verify Password", 10, 140, 120, 25, 14);
        panel.add(verifyPasswordLabel);

        JPasswordField verifyPasswordText = createPasswordField(150, 140, 165, 25);
        panel.add(verifyPasswordText);

        JLabel phoneLabel = createLabel("Phone Number", 10, 170, 120, 25, 14);
        panel.add(phoneLabel);

        JTextField phoneText = createTextField(150, 170, 165, 25);
        panel.add(phoneText);

        JLabel emailLabel = createLabel("Email", 10, 200, 80, 25, 14);
        panel.add(emailLabel);

        JTextField emailText = createTextField(150, 200, 165, 25);
        panel.add(emailText);

        JLabel roleLabel = createLabel("Role", 10, 230, 80, 25, 14);
        panel.add(roleLabel);

        String[] roles = {"Driver", "Customer"};
        JComboBox<String> roleComboBox = createComboBox(roles, 150, 230, 165, 25);
        panel.add(roleComboBox);

        JButton backButton = createButton("Back to Login", 30, 270, 120, 25);
        panel.add(backButton);

        JButton registerButton = createButton("Register", 200, 270, 120, 25);
        panel.add(registerButton);

        registerButton.addActionListener(e -> {
            HashMap<String, String> tempInputs = placeAllInputTemp(userText, nameText, phoneText, emailText, roleComboBox);
            char[][] tempPass = {passwordText.getPassword(), verifyPasswordText.getPassword()};
            String mssg="";

            if (!ValidatingRegisterInput.checkAllInput(tempInputs, tempPass)) {
                JOptionPane.showMessageDialog(frame, "Isi semua field !", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                switch (ValidatingRegisterInput.checkToDB(tempInputs)) {
                    case "username":
                        mssg = "username yang diinput sudah ada";
                        break;
                    case "email":
                        mssg = "email yang diinput sudah digunakan";
                        break;
                    case "phone":
                        mssg = "nomor handphone yang diinput sudah digunakan";
                        break;
                    default:
                        if (ValidatingRegisterInput.checkPassword(passwordText.getPassword(), verifyPasswordText.getPassword())) {
                            if(Register.validatingRegister(tempInputs, passwordText.getPassword())){
                                mssg = "register berhasil !";
                                new LoginPage();
                                frame.dispose();
                            }else{
                                mssg = "register gagal";
                            }
                        } else {
                            mssg = "password yang diinput tidak sama";
                        }
                }
                JOptionPane.showMessageDialog(frame, mssg);
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            new LoginPage();
        });

        frame.setVisible(true);
    }

    private JFrame createFrame() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        final int FRAME_WIDTH = 400;
        final int FRAME_HEIGHT = 350;

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2);
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2);

        JFrame frame = new JFrame("Register Page");
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 400, 350);
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

    private JComboBox<String> createComboBox(String[] items, int x, int y, int width, int height) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setBounds(x, y, width, height);
        return comboBox;
    }

    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        return button;
    }

    private HashMap<String, String> placeAllInputTemp(JTextField userText, JTextField nameText,
                                                      JTextField phoneText, JTextField emailText,
                                                      JComboBox<String> roleComboBox) {
        HashMap<String, String> temp = new HashMap<>();
        temp.put("username", userText.getText());
        temp.put("name", nameText.getText());
        temp.put("phone", phoneText.getText());
        temp.put("email", emailText.getText());
        temp.put("role", (String) roleComboBox.getSelectedItem());

        return temp;
    }
}
