package view;

import controller.ManagingCustomer;
import model.Class.user.Customer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManageCustomerPage extends JFrame {
    public ManageCustomerPage() {
        showManageCustomer();
    }

    public void showManageCustomer() {
        this.setTitle("Manage Customer");
        this.setSize(new Dimension(700, 400));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Customer> data = ManagingCustomer.getData();

        JPanel container = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx=0;
        gbc.gridy=0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 10, 0, 10);// Top, left, bottom, right padding
        gbc.anchor = GridBagConstraints.NORTHWEST;
        JButton back = new JButton("Back");
        container.add(back, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 6;
        gbc.insets = new Insets(0, 10, 10, 10);// Top, left, bottom, right padding
        JLabel header = new JLabel("Manage Customer");
        header.setFont(new Font("SansSerif", Font.BOLD, 24));
        container.add(header,gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);// Top, left, bottom, right padding
        container.add(new JLabel("user_id"),gbc);

        gbc.gridx++;
        container.add(new JLabel("username"),gbc);

        gbc.gridx++;
        container.add(new JLabel("phone number"),gbc);

        gbc.gridx++;
        container.add(new JLabel("email"),gbc);

        gbc.gridx++;
        container.add(new JLabel("Status GoPlus"),gbc);

        gbc.gridx++;
        container.add(new JLabel("Action"),gbc);

        for(Customer datum : data){
            gbc.gridy++;
            gbc.gridx=0;
            container.add(new JLabel(String.valueOf(datum.getUserID())),gbc);

            gbc.gridx++;
            container.add(new JLabel(datum.getUsername()),gbc);

            gbc.gridx++;
            container.add(new JLabel(datum.getPhoneNumber()),gbc);

            gbc.gridx++;
            container.add(new JLabel(datum.getEmail()),gbc);

            gbc.gridx++;
            String labelStatus;

            if(datum.getGojekPlus() == null){
                labelStatus = "False";
            }else{
                labelStatus = String.valueOf(datum.getGojekPlus().isStatus());
            }
            container.add(new JLabel(labelStatus),gbc);


            gbc.gridx++;
            JButton addBL = new JButton("Add to BlackList");
            container.add(addBL,gbc);

            addBL.addActionListener(e ->{

            });
        }

        back.addActionListener(e -> {
            new AdminPage();
            this.dispose();
        });

        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(scrollPane);
        this.add(container);
        this.setVisible(true);
    }
}
