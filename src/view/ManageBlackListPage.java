package view;

import controller.ManagingBlackList;
import controller.ManagingCustomer;
import model.Class.user.Customer;
import model.Class.user.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManageBlackListPage extends JFrame {
    public ManageBlackListPage() {
        showManageBlackList();
    }

    public void showManageBlackList() {
        this.setTitle("Manage Blacklist");
        this.setSize(new Dimension(700, 400));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<User> data = ManagingBlackList.getData();

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
        JLabel header = new JLabel("Manage Blacklist");
        header.setFont(new Font("SansSerif", Font.BOLD, 24));
        container.add(header,gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);// Top, left, bottom, right padding
        container.add(new JLabel("user_id"),gbc);

        gbc.gridx++;
        container.add(new JLabel("username"),gbc);

        gbc.gridx++;
        container.add(new JLabel("phone number"),gbc);

        gbc.gridx++;
        container.add(new JLabel("email"),gbc);

        gbc.gridx++;
        container.add(new JLabel("User Type"),gbc);

        gbc.gridx++;
        container.add(new JLabel("Action"),gbc);

        for(User datum : data){
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
            container.add(new JLabel(datum.getUserType().toString()),gbc);

            gbc.gridx++;
            JButton delBL = new JButton("Remove from BlackList");
            container.add(delBL,gbc);

            delBL.addActionListener(e ->{
                int response = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus user "+datum.getUsername() +" dari blacklist ?");

                if(response == JOptionPane.YES_OPTION){
                    if(ManagingBlackList.removeBlackList(datum.getUserID())){
                        JOptionPane.showMessageDialog(this,"Berhasil menghapus user dari black list");
                        this.dispose();
                        new AdminPage();
                    }else{
                        JOptionPane.showMessageDialog(this,"Gagal menghapus user dari black list");
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
        this.setVisible(true);
    }
}
