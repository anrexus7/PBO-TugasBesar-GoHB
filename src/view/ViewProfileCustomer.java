package view;

import model.Class.SingletonManagerCustomer;

import javax.swing.*;
import java.awt.*;

public class ViewProfileCustomer extends JFrame {
    public ViewProfileCustomer() {
        showProfileCustomer();
    }

    private void showProfileCustomer() {
        this.setTitle("My Profile");
        this.setSize(new Dimension(450, 400));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel container = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);// Top, left, bottom, right padding


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        JButton back = new JButton("Back");
        container.add(back, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        container.add(profilePage(),gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;
        JButton editProfile = new JButton("Edit");
        container.add(editProfile, gbc);

        gbc.gridy++;
        gbc.gridx--;
        gbc.insets = new Insets(10, 10, 0, 10);
        gbc.anchor = GridBagConstraints.WEST;
        container.add(new JLabel("Account :"),gbc);

        gbc.gridy++;
        container.add(new JLabel("My Subscription"),gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 40, 0, 0);
        JButton viewSubs = new JButton("view");
        container.add(viewSubs, gbc);

        gbc.gridy++;
        gbc.gridx--;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 0, 10);
        container.add(new JLabel("Promos"),gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 40, 0, 0);
        JButton viewPromo = new JButton("view");
        container.add(viewPromo, gbc);

        this.add(container);
        this.setVisible(true);

        viewSubs.addActionListener(e ->{
            if(SingletonManagerCustomer.getInstance().getCustomer().getGojekPlus() == null){
                new GoPlusPage();
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this, "Anda sedang berlangannan paket : "+SingletonManagerCustomer.getInstance().getCustomer().getGojekPlus().getPaket()+
                        " dan akan berakhir pada tanggal "+SingletonManagerCustomer.getInstance().getCustomer().getGojekPlus().getValidTo());
            }
        });

        viewPromo.addActionListener(e ->{
            JOptionPane.showMessageDialog(this, "Teu nyaho mau nampilin apa tar dipikirin");
        });

        editProfile.addActionListener(e ->{
            new EditProfilePage();
            this.dispose();
        });
    }

    private JPanel profilePage(){
        JPanel container = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 0, 10);

        gbc.gridx=0;
        gbc.gridy=0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel namaUser = new JLabel(SingletonManagerCustomer.getInstance().getCustomer().getName());
        namaUser.setFont(new Font("SansSerif", Font.BOLD, 18));
        container.add(namaUser, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 10, 0, 10);
        JLabel emailUser = new JLabel(SingletonManagerCustomer.getInstance().getCustomer().getEmail());
        container.add(emailUser, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 10, 10, 10);
        JLabel phoneNumber = new JLabel(SingletonManagerCustomer.getInstance().getCustomer().getPhoneNumber());
        container.add(phoneNumber, gbc);

        return container;
    }
}
