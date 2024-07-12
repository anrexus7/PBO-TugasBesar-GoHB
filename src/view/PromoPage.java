package view;

import controller.ManagingPromo;
import controller.ShowPromoCustomer;
import model.Class.transaction.Promo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PromoPage extends JFrame {
    public PromoPage(String typeService) {
        showPromo(typeService);
    }

    private void showPromo(String typeService) {
        this.setTitle("View Promo");
        this.setSize(new Dimension(450, 400));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Promo> data = ShowPromoCustomer.getData(typeService);

        JPanel container = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10, 0, 10);// Top, left, bottom, right padding
        gbc.anchor = GridBagConstraints.NORTHWEST;
        JButton back = new JButton("Back");
        container.add(back, gbc);

        gbc.gridy++;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 10, 10, 10);// Top, left, bottom, right padding
        JLabel header = new JLabel("Promo "+typeService);
        header.setFont(new Font("SansSerif", Font.BOLD, 20));
        container.add(header, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);// Top, left, bottom, right padding
        container.add(new JLabel("Kode Promo "), gbc);

        gbc.gridx++;
        container.add(new JLabel("Potongan "), gbc);

        gbc.gridx++;
        container.add(new JLabel("Berlaku Hingga "), gbc);

        for (Promo datum : data) {
            gbc.gridy++;
            gbc.gridx = 0;
            container.add(new JLabel(datum.getCode()), gbc);

            gbc.gridx++;
            container.add(new JLabel(String.valueOf(datum.getDiscount())), gbc);

            gbc.gridx++;
            container.add(new JLabel(String.valueOf(datum.getExpiryDate())), gbc);
        }

        back.addActionListener(e -> {
            this.dispose();
            new ViewProfileCustomer();
        });

        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(scrollPane);
        this.setVisible(true);
    }
}
