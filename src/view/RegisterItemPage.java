package view;

import controller.ManagingItems;
import controller.ManagingResto;
import model.Enum.KategoriItem;

import javax.swing.*;
import java.awt.*;

public class RegisterItemPage extends JFrame {
    public RegisterItemPage(int restoId) {
        showRegisterItem(restoId);
    }

    private void showRegisterItem(int restoId) {
        this.setTitle("Manage Item");
        this.setSize(new Dimension(650, 400));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        gbc.anchor = GridBagConstraints.WEST;
        container.add(new JLabel("Product Name "), gbc);

        gbc.gridx++;
        JTextField name = new JTextField(20);
        container.add(name, gbc);

        gbc.gridy++;
        gbc.gridx--;
        container.add(new JLabel("Price "), gbc);

        gbc.gridx++;
        JTextField price = new JTextField(20);
        container.add(price, gbc);

        gbc.gridy++;
        gbc.gridx--;
        container.add(new JLabel("Stock "), gbc);

        gbc.gridx++;
        JTextField stock = new JTextField(20);
        container.add(stock, gbc);

        gbc.gridy++;
        gbc.gridx--;
        container.add(new JLabel("Type "), gbc);

        gbc.gridx++;
        JComboBox<Object> TypeI = new JComboBox<>(KategoriItem.values());
        container.add(TypeI, gbc);

        gbc.gridy++;
        gbc.gridx--;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton submit = new JButton("Submit");
        container.add(submit, gbc);

        back.addActionListener(e -> {
            this.dispose();
            new ManageItemsPage(restoId);
        });

        submit.addActionListener(e -> {
            String mssg = "";
            if(ManagingItems.validatingInput(name.getText(), price.getText(), stock.getText())){
                mssg= "ISI SEMUA FIELD NYING, Error";
            }else{
                double priceD=0;
                int stockI=0;
                try{
                    priceD = Double.parseDouble(price.getText());
                    stockI = Integer.parseInt(stock.getText());

                    if(ManagingItems.registeringItem(restoId, name.getText(), TypeI.getSelectedItem().toString(), priceD, stockI) ){
                        mssg= "Register Item berhasil, Success";

                        this.dispose();
                        new ManageItemsPage(restoId);
                    }else{
                        mssg = "Register Item gagal, Error";
                    }
                }catch(NumberFormatException x){
                    mssg = "Pastikan stock/harga input berupa angka, Error";
                }
            }

            String[] splitMssg = mssg.split(", ");
            if(splitMssg[1].equalsIgnoreCase("Error")){
                JOptionPane.showMessageDialog(null, splitMssg[0], "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, splitMssg[0]);
            }
        });

        this.add(container);
        this.setVisible(true);
    }
}
