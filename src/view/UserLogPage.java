package view;

import controller.ManageUserLog;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserLogPage extends JFrame {
    public UserLogPage() {
        viewUserLog();
    }

    private void viewUserLog() {
        ArrayList<Object[]> userLogs = ManageUserLog.getData();

        this.setTitle("User Log");
        this.setSize(new Dimension(600, 400));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel container = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx=0;
        gbc.gridy=0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 10, 0, 10);// Top, left, bottom, right padding
        JButton back = new JButton("Back");
        container.add(back, gbc);


        gbc.gridy++;
        gbc.insets = new Insets(10, 10, 10, 10);// Top, left, bottom, right padding
        container.add(new JLabel("log_id"),gbc);

        gbc.gridx++;
        container.add(new JLabel("user_id"),gbc);

        gbc.gridx++;
        container.add(new JLabel("Activity type"),gbc);

        gbc.gridx++;
        container.add(new JLabel("Time Stamp"),gbc);

        gbc.gridx++;
        container.add(new JLabel("User Type"),gbc);

        for(Object[] data : userLogs){
            gbc.gridy++;
            gbc.gridx=0;
            container.add(new JLabel(String.valueOf(data[0])),gbc);

            gbc.gridx++;
            container.add(new JLabel(String.valueOf(data[1])),gbc);

            gbc.gridx++;
            container.add(new JLabel(String.valueOf(data[2])),gbc);

            gbc.gridx++;
            container.add(new JLabel(String.valueOf(data[3])),gbc);

            gbc.gridx++;
            container.add(new JLabel(String.valueOf(data[4])),gbc);
        }

        back.addActionListener(e -> {
            this.dispose();
            new AdminPage();
        });

        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(scrollPane);
        this.setVisible(true);
    }
}
