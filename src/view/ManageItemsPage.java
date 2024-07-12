package view;

import controller.ManagingItems;
import controller.ManagingPromo;
import model.Class.restaurant.Item;
import model.Class.restaurant.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManageItemsPage extends JFrame {
    public ManageItemsPage(int restoId){
        showManageItems(restoId);
    }

    public void showManageItems(int restoId){
        this.setTitle("Manage Items");
        this.setSize(new Dimension(700, 400));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Item> data = ManagingItems.getData(restoId);

        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JPanel editor = new JPanel();
        editor.add(editButton);
        editor.add(deleteButton);

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
        JLabel header = new JLabel("Manage Items");
        header.setFont(new Font("SansSerif", Font.BOLD, 24));
        container.add(header,gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        container.add(new JLabel("ID"),gbc);

        gbc.gridx++;
        container.add(new JLabel("Name"),gbc);

        gbc.gridx++;
        container.add(new JLabel("Type"),gbc);

        gbc.gridx++;
        container.add(new JLabel("Price"),gbc);

        gbc.gridx++;
        container.add(new JLabel("Stock"),gbc);

        gbc.gridx++;
        container.add(new JLabel("Action"),gbc);

        gbc.insets = new Insets(10, 10, 10, 10);// Top, left, bottom, right padding

        for (Item datum : data) {
            gbc.gridy++;
            gbc.gridx = 0;
            container.add(new JLabel(String.valueOf(datum.getItemID())), gbc);

            gbc.gridx++;
            container.add(new JLabel(datum.getName()), gbc);

            gbc.gridx++;
            container.add(new JLabel(datum.getKategori().toString()), gbc);

            gbc.gridx++;
            container.add(new JLabel(String.valueOf(datum.getHarga())), gbc);

            gbc.gridx++;
            container.add(new JLabel(String.valueOf(datum.getStock())), gbc);

            gbc.gridx++;
            container.add(editor, gbc);

            deleteButton.addActionListener(e -> {
                int response = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus item "+datum.getName());

                if(response == JOptionPane.YES_OPTION){
                    if(ManagingItems.deleteItem(datum.getItemID())){
                        JOptionPane.showMessageDialog(this,"Berhasil menghapus item");
                        this.dispose();
                        new AdminPage();
                    }else{
                        JOptionPane.showMessageDialog(this,"Gagal menghapus item");
                    }
                }
            });

            editButton.addActionListener(e -> {
                new EditItemPage(datum, restoId);
            });
        }

        gbc.gridx = 0;
        gbc.gridy++;
        JButton add = new JButton("Add Resto");
        container.add(add, gbc);

        add.addActionListener(e ->{
            this.dispose();
            new RegisterItemPage(restoId);
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
