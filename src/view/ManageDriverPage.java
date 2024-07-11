package view;
import controller.ManagingBlackList;
import controller.ManagingDriver;
import model.Class.user.Driver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManageDriverPage extends JFrame {
    public ManageDriverPage() {
        showManageDriver();
    }

    private void showManageDriver() {
        this.setTitle("Manage Customer");
        this.setSize(new Dimension(650, 400)); //width, height
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Driver> data = ManagingDriver.getData();

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
        gbc.gridwidth = 6;
        gbc.insets = new Insets(0, 10, 10, 10);// Top, left, bottom, right padding
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel header = new JLabel("Manage Customer");
        header.setFont(new Font("SansSerif", Font.BOLD, 24));
        container.add(header,gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.insets = new Insets(10, 10, 10, 10);// Top, left, bottom, right padding
        gbc.anchor = GridBagConstraints.WEST;
        container.add(new JLabel("driver_id"),gbc);

        gbc.gridx++;
        container.add(new JLabel("username"),gbc);

        gbc.gridx++;
        container.add(new JLabel("phone number"),gbc);

        gbc.gridx++;
        container.add(new JLabel("email"),gbc);

        gbc.gridx++;
        container.add(new JLabel("Rating"),gbc);

        gbc.gridx++;
        container.add(new JLabel("Action"),gbc);

        for(Driver datum : data){
            gbc.gridy++;
            gbc.gridx=0;
            container.add(new JLabel(String.valueOf(datum.getDriverId())),gbc);

            gbc.gridx++;
            container.add(new JLabel(datum.getUsername()),gbc);

            gbc.gridx++;
            container.add(new JLabel(datum.getPhoneNumber()),gbc);

            gbc.gridx++;
            container.add(new JLabel(datum.getEmail()),gbc);

            gbc.gridx++;
            container.add(new JLabel(String.valueOf(datum.getRating())),gbc);

            gbc.gridx++;
            JButton addBL = new JButton("Add to BlackList");
            container.add(addBL,gbc);

            addBL.addActionListener(e ->{
                int response = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menambah driver "+datum.getUsername() +" pada blacklist ?");

                if(response == JOptionPane.YES_OPTION){
                    if(ManagingBlackList.setBlackList(datum.getUserID())){
                        JOptionPane.showMessageDialog(this,"Berhasil menambah driver ke black list");
                        this.dispose();
                        new AdminPage();
                    }else{
                        JOptionPane.showMessageDialog(this,"Gagal menambah driver ke black list");
                    }
                }
            });
        }

        back.addActionListener(e -> {
            this.dispose();
            new AdminPage();
        });

        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(scrollPane);
        this.add(container);
        this.setVisible(true);
    }
}
