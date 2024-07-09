package view;

import controller.SubscriptGoPlus;
import model.Class.user.Customer;

import javax.swing.*;
import java.awt.*;

public class GoPlusPage extends JFrame {

    public GoPlusPage(Customer customer) {
        showGoPlusPage(customer);
    }

    private void showGoPlusPage(Customer customer) {
        this.setTitle("GoPlus Subscription");
        this.setSize(new Dimension(450, 400));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel container = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 10, 0);// Top, left, bottom, right padding

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        JButton back = new JButton("Back to Home");
        container.add(back, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel header = new JLabel("Go Plus");
        header.setFont(new Font("SansSerif", Font.BOLD, 36));
        container.add(header, gbc);

        gbc.gridy++;
        container.add(new JLabel("One plan for guarenteed daily discount"),gbc);

        gbc.gridy++;
        JComboBox<String> comboBoxPlan = new JComboBox<>(new String[]{"WEEKLY", "MONTHLY", "ANNUALY"});
        container.add(comboBoxPlan, gbc);

        gbc.gridy++;
        JLabel labelDiscGobike = new JLabel("goride up to 8.000 off");
        container.add(labelDiscGobike, gbc);

        gbc.gridy++;
        JLabel labelDiscGocar = new JLabel("gocar up to 8.000 off");
        container.add(labelDiscGocar, gbc);

        gbc.gridy++;
        JLabel labelDiscGosend = new JLabel("gosend flat 3.000 off");
        container.add(labelDiscGosend, gbc);

        gbc.gridy++;
        JLabel labelDiscGofood = new JLabel("gofood up to 12.000 off");
        container.add(labelDiscGofood, gbc);

        JPanel footer = new JPanel();
        footer.add(new JLabel("Save up to"));
        JLabel footerSaveUp = new JLabel("Rp 4.000.000");
        footerSaveUp.setFont(new Font("SansSerif", Font.BOLD, 20));
        footer.add(footerSaveUp);
        footer.add(new JLabel("with this plan"));

        gbc.gridy++;
        container.add(footer, gbc);

        gbc.gridy++;
        JButton subs = new JButton("Subscribe for 7 days 10.000");
        container.add(subs, gbc);

        comboBoxPlan.addActionListener(e->{
            if(comboBoxPlan.getSelectedItem().equals("MONTHLY")){
                footerSaveUp.setText("Rp 8.260.000");
                subs.setText("Subscribe for 30 days 15.000");
            }else if(comboBoxPlan.getSelectedItem().equals("ANNUALY")){
                footerSaveUp.setText("Rp 24.800.000");
                subs.setText("Subscribe for 360 days 34.000");
            }else {
                footerSaveUp.setText("Rp 4.000.000");
                subs.setText("Subscribe for 7 days 10.000");
            }
        });

        back.addActionListener(e->{
           new CustomerPage(customer);
           this.dispose();
        });

        subs.addActionListener(e->{
           if(!SubscriptGoPlus.subscribe(comboBoxPlan.getSelectedItem().toString())){
               JOptionPane.showMessageDialog(null, "Gagal melakukan langganan", "Error", JOptionPane.ERROR_MESSAGE);
           }else{
               JOptionPane.showMessageDialog(null, "Berhasil Berlangganan !");
                new CustomerPage(customer);
               this.dispose();
           }
        });

        this.add(container);
        this.setVisible(true);
    }
}
