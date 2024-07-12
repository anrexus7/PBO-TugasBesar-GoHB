package view;

import controller.ManagingPromo;
import controller.ManagingResto;
import model.Class.restaurant.Restaurant;
import model.Class.transaction.Promo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManageRestaurantPage extends JFrame {
    public ManageRestaurantPage() {
        showManageRestaurant();
    }

    private void showManageRestaurant() {
        this.setTitle("Manage Resto");
        this.setSize(new Dimension(800, 400));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Restaurant> data = ManagingResto.getData();
        JButton viewItemsButton = new JButton("View Items");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JPanel editor = new JPanel();
        editor.add(viewItemsButton);
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
        JLabel header = new JLabel("Manage Resto (add/edit/delete)");
        header.setFont(new Font("SansSerif", Font.BOLD, 20));
        container.add(header, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        container.add(new JLabel("Resto ID"),gbc);

        gbc.gridx++;
        container.add(new JLabel("Resto Name"),gbc);

        gbc.gridx++;
        container.add(new JLabel("Address"),gbc);

        gbc.gridx++;
        container.add(new JLabel("Rating"),gbc);

        gbc.gridx++;
        container.add(new JLabel("Action"),gbc);

        gbc.insets = new Insets(10, 10, 10, 10);// Top, left, bottom, right padding

        for (Restaurant datum : data) {
            gbc.gridy++;
            gbc.gridx = 0;
            container.add(new JLabel(String.valueOf(datum.getRestoID())), gbc);

            gbc.gridx++;
            container.add(new JLabel(datum.getName()), gbc);

            gbc.gridx++;
            container.add(new JLabel(datum.getLocation().getAddress()), gbc);

            gbc.gridx++;
            container.add(new JLabel(String.valueOf(datum.getRating())), gbc);

            gbc.gridx++;
            container.add(editor, gbc);

            viewItemsButton.addActionListener(e ->{
                this.dispose();
                new ManageItemsPage(datum.getRestoID());
            });

            deleteButton.addActionListener(e -> {
                int response = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus resto "+datum.getName());

                if(response == JOptionPane.YES_OPTION){
                    if(ManagingPromo.deletePromo(datum.getRestoID())){
                        JOptionPane.showMessageDialog(this,"Berhasil menghapus promo");
                        this.dispose();
                        new AdminPage();
                    }else{
                        JOptionPane.showMessageDialog(this,"Gagal menghapus promo");
                    }
                }
            });

            editButton.addActionListener(e -> {
                new EditRestoPage(datum);
            });
        }

        gbc.gridx = 0;
        gbc.gridy++;
        JButton add = new JButton("Add Resto");
        container.add(add, gbc);

        add.addActionListener(e ->{
            this.dispose();
            new RegisterRestoPage();
        });

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
