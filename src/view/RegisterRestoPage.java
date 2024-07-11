package view;

import controller.FetchDataRegion;
import controller.ManagingPromo;
import controller.ManagingResto;
import model.Class.DateLabelFormatter;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RegisterRestoPage extends JFrame {
    public RegisterRestoPage() {
        showRegisterResto();
    }

    private void showRegisterResto() {
        ArrayList<String> dataRegions = FetchDataRegion.getData();
        String[] itemsRegion = new String[dataRegions.size()];
        dataRegions.toArray(itemsRegion);

        this.setTitle("Manage Resto");
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
        container.add(new JLabel("Store Name "), gbc);

        gbc.gridx++;
        JTextField name = new JTextField(20);
        container.add(name, gbc);

        gbc.gridy++;
        gbc.gridx--;
        container.add(new JLabel("Address "), gbc);

        gbc.gridx++;
        JTextField address = new JTextField(20);
        container.add(address, gbc);

        gbc.gridy++;
        gbc.gridx--;
        container.add(new JLabel("Phone Number "), gbc);

        gbc.gridx++;
        JTextField phone = new JTextField(20);
        container.add(phone, gbc);

        gbc.gridy++;
        gbc.gridx--;
        container.add(new JLabel("Village "), gbc);

        gbc.gridx++;
        JComboBox<String> village = new JComboBox<>(itemsRegion);
        container.add(village, gbc);

        gbc.gridy++;
        gbc.gridx--;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton submit = new JButton("Submit");
        container.add(submit, gbc);

        back.addActionListener(e -> {
            new ManagePromoPage();
            this.dispose();
        });

        submit.addActionListener(e -> {
            String mssg = "";
            if(ManagingResto.validatingInput(name.getText(), address.getText(), phone.getText())){
                mssg= "ISI SEMUA FIELD NYING, Error";
            }else{
                if(ManagingResto.registeringRestaurant(name.getText(), address.getText(), phone.getText(), (village.getSelectedIndex()+1)) ){
                    mssg= "Register Promo berhasil, Success";

                    new ManageRestaurantPage();
                    this.dispose();
                }else{
                    mssg = "Register promo gagal, Error";
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
