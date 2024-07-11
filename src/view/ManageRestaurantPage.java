package view;

import controller.ManagingPromo;
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
        this.setSize(new Dimension(650, 400));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Promo> data = ManagingPromo.getData();
        JButton viewButton = new JButton("View Items");
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

        int i = 1;
        gbc.anchor = GridBagConstraints.WEST;
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

            viewButton.addActionListener(e -> {

            });

            deleteButton.addActionListener(e -> {
                int response = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus resto "+datum.getName());

                if(response == JOptionPane.YES_OPTION){
                    if(ManagingPromo.deletePromo(datum.getRestoID())){
                        JOptionPane.showMessageDialog(this,"Berhasil menghapus promo");
                        new AdminPage();
                        this.dispose();
                    }else{
                        JOptionPane.showMessageDialog(this,"Gagal menghapus promo");
                    }
                }
            });

            editButton.addActionListener(e -> {
                new EditPromoPage(datum);
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
