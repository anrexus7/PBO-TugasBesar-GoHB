package view;

import controller.RegisterVehicle;
import model.Enum.VehicleType;

import javax.swing.*;
import java.awt.*;

public class RegisterVehiclePage extends JFrame {

    public RegisterVehiclePage() {
        showRegisterVehiclePage();
    }

    private void showRegisterVehiclePage() {
        this.setTitle("Vehicle Registration");
        this.setSize(new Dimension(500, 300)); //width, height
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel container = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 10, 0);// Top, left, bottom, right padding

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel header = new JLabel("Register Your Vehicle Now !");
        header.setFont(new Font("SansSerif", Font.BOLD, 25));
        container.add(header, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        container.add(new JLabel("Plat nomor kendaraan"), gbc);

        gbc.gridx++;
        gbc.insets = new Insets(0, 80, 10, 0);
        JTextField plateNumber = new JTextField(15);
        container.add(plateNumber, gbc);

        gbc.gridy++;
        gbc.gridx--;
        gbc.insets = new Insets(0, 10, 10, 0);
        container.add(new JLabel("Tipe Kendaraan"), gbc);

        gbc.gridx++;
        gbc.insets = new Insets(0, 80, 10, 0);
        JComboBox<Object> vehicleType = new JComboBox<>(VehicleType.values());
        vehicleType.setPreferredSize(new Dimension(170, vehicleType.getPreferredSize().height));
        container.add(vehicleType, gbc);

        gbc.gridy++;
        gbc.gridx--;
        gbc.insets = new Insets(0, 10, 10, 0);
        JLabel labelEngine = new JLabel("Kapasitas mesin");
        container.add(labelEngine, gbc);

        gbc.gridx++;
        gbc.insets = new Insets(0, 80, 10, 0);
        JTextField engineCapacity  = new JTextField(15);
        container.add(engineCapacity, gbc);

        gbc.gridy++;
        gbc.gridx--;
        gbc.insets = new Insets(0, 10, 10, 0);
        JLabel labelSeat = new JLabel("Jumlah kursi");
        labelSeat.setVisible(false);
        container.add(labelSeat, gbc);

        gbc.gridx++;
        gbc.insets = new Insets(0, 80, 10, 0);
        JTextField numberSeat  = new JTextField(15);
        numberSeat.setVisible(false);
        container.add(numberSeat, gbc);

        vehicleType.addActionListener(e->{
            if(vehicleType.getSelectedIndex()==1){
                labelEngine.setVisible(false);
                engineCapacity.setVisible(false);

                labelSeat.setVisible(true);
                numberSeat.setVisible(true);
            }else{
                labelEngine.setVisible(true);
                engineCapacity.setVisible(true);

                labelSeat.setVisible(false);
                numberSeat.setVisible(false);
            }
        });

        gbc.gridy++;
        gbc.gridx--;
        gbc.insets = new Insets(0, 10, 10, 0);
        JButton registerButton = new JButton("Register Vehicle");
        container.add(registerButton, gbc);

        registerButton.addActionListener(e->{
            if(vehicleType.getSelectedIndex()==0){
                try{
                    Integer.parseInt(engineCapacity.getText());
                    registering(plateNumber.getText(), vehicleType.getSelectedItem(), engineCapacity.getText(), numberSeat.getText());
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "ISI PAKE ANGKA GEBLEG :v", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                try{
                    Integer.parseInt(labelSeat.getText());
                    registering(plateNumber.getText(), vehicleType.getSelectedItem(), engineCapacity.getText(), numberSeat.getText());
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "ISI PAKE ANGKA GEBLEG :v", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        this.add(container);
        this.setVisible(true);
    }

    private void registering(String plat, Object vehicleType, String cc, String numSeat) {
        String mssg;
        if(!RegisterVehicle.validatingInput(plat,vehicleType,cc,numSeat)){
            mssg = "NU BALEG ISINYA NYING :v";
        }else{
            if(!RegisterVehicle.registering(plat,vehicleType,cc,numSeat)){
                mssg = "NU BALEG ISINYA NYING :v";
            }else{
                mssg = "Register vehicle berhasil !";
                //GUI Driver
                this.dispose();
            }
        }
        JOptionPane.showMessageDialog(this, mssg);
    }
}
