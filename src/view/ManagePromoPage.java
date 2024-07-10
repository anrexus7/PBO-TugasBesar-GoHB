package view;

import controller.ManagingPromo;
import model.Class.transaction.Promo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManagePromoPage extends JFrame {

    public ManagePromoPage() {
        showManagePromo();
    }

    public void showManagePromo() {
        this.setTitle("Manage Promo");
        this.setSize(new Dimension(650, 400));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Promo> data = ManagingPromo.getData();
        JButton viewButton = new JButton("View");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JPanel editor = new JPanel();
        editor.add(viewButton);
        editor.add(editButton);
        editor.add(deleteButton);

        JPanel container = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 10, 0, 10);// Top, left, bottom, right padding
        gbc.anchor = GridBagConstraints.NORTHWEST;
        JButton back = new JButton("Back");
        container.add(back, gbc);

        gbc.gridy++;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 10, 10, 10);// Top, left, bottom, right padding
        JLabel header = new JLabel("Manage Customer (add/edit/delete)");
        header.setFont(new Font("SansSerif", Font.BOLD, 20));
        container.add(header, gbc);

        int i = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);// Top, left, bottom, right padding
        for (Promo datum : data) {
            gbc.gridy++;
            gbc.gridx = 0;
            container.add(new JLabel(i + ". "), gbc);

            gbc.gridx++;
            container.add(new JLabel("Voucher id : " + datum.getPromoID()), gbc);

            gbc.gridx++;
            container.add(new JLabel("Kode : " + datum.getCode()), gbc);

            gbc.gridx++;
            container.add(editor, gbc);

            viewButton.addActionListener(e -> {

            });

            deleteButton.addActionListener(e -> {

            });

            editButton.addActionListener(e -> {

            });
        }

        gbc.gridx = 0;
        gbc.gridy++;
        JButton add = new JButton("Add Promo");
        container.add(add, gbc);

        add.addActionListener(e ->{
            new RegisterPromoPage();
            this.dispose();
        });

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
