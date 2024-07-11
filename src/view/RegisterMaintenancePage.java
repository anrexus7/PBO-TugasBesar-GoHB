package view;

import controller.ManagingMaintenanceVehicle;
import model.Class.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class RegisterMaintenancePage extends JFrame {
    public RegisterMaintenancePage() {
        showRegisterMaintenance();
    }

    private void showRegisterMaintenance() {
        this.setTitle("Manage Maintenance Vehicle");
        this.setSize(new Dimension(500, 200));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel container = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 10);// Top, left, bottom, right padding

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        JButton back = new JButton("Back");
        container.add(back, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(10, 10, 10, 10);// Top, left, bottom, right padding
        gbc.anchor = GridBagConstraints.WEST;
        container.add(new JLabel("Vehicle id"), gbc);

        gbc.gridx++;
        JTextField vehicleId = new JTextField(17);
        container.add(vehicleId, gbc);

        gbc.gridy++;
        gbc.gridx--;
        container.add(new JLabel("Date "), gbc);


        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        JDatePanelImpl datePanel;
        datePanel = new JDatePanelImpl(model, p);

        gbc.gridx++;
        JDatePickerImpl maintenanceDate = new JDatePickerImpl(datePanel , new DateLabelFormatter());
        container.add(maintenanceDate,gbc);

        gbc.gridy++;
        gbc.gridx--;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton submit = new JButton("Submit");
        container.add(submit,gbc);

        back.addActionListener(e ->{
            this.dispose();
            new ManageMaintenanceVehiclePage();
        });

        submit.addActionListener(e -> {
            String mssg = "";
            if(ManagingMaintenanceVehicle.validatingInput(vehicleId.getText(), maintenanceDate.getModel())){
                mssg= "ISI SEMUA FIELD NYING, Error";
            }else{
                try{
                    int idV = Integer.parseInt(vehicleId.getText());
                    if(ManagingMaintenanceVehicle.registeringMaintenance(idV, maintenanceDate.getModel().getValue())){
                        mssg= "Register Maintenance Date berhasil, Success";

                        this.dispose();
                        new ManageMaintenanceVehiclePage();
                    }else{
                        mssg = "Register Maintenance Date gagal, Error";
                    }
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "ISI PAKE ANGKA ID GEBLEG :v", "Error", JOptionPane.ERROR_MESSAGE);
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
