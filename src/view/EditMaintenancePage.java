package view;

import controller.ManagingMaintenanceVehicle;
import model.Class.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class EditMaintenancePage extends JFrame {
    public EditMaintenancePage(Object[] datum) {
        showEditMaintenance(datum);
    }

    private void showEditMaintenance(Object[] datum) {
        this.setTitle("Manage Maintenance Vehicle");
        this.setSize(new Dimension(500, 250));
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
        gbc.insets = new Insets(10, 10, 10, 10);// Top, left, bottom, right padding
        gbc.anchor = GridBagConstraints.WEST;
        container.add(new JLabel("Vehicle id"), gbc);

        gbc.gridx++;
        JTextField vehicleId = new JTextField(String.valueOf(datum[1]),17);
        container.add(vehicleId, gbc);

        gbc.gridy++;
        gbc.gridx--;
        container.add(new JLabel("Date "), gbc);

        gbc.gridx++;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime((Date) datum[3]);

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        JDatePanelImpl datePanel;

        model.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        model.setSelected(true);
        datePanel = new JDatePanelImpl(model, p);

        JDatePickerImpl maintenanceDate = new JDatePickerImpl(datePanel , new DateLabelFormatter());
        container.add(maintenanceDate,gbc);

        gbc.gridy++;
        gbc.gridx--;
        container.add(new JLabel("Status "), gbc);

        gbc.gridx++;
        JComboBox<String> statusMainte = new JComboBox<>(new String[]{"SCHEDULED","COMPLETED","CANCELED","ONGOING"});
        statusMainte.setSelectedItem(datum[4]);
        container.add(statusMainte, gbc);

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
                    int idM = Integer.parseInt(String.valueOf(datum[0]));
                    if(ManagingMaintenanceVehicle.updatingMainte(idM, statusMainte.getSelectedItem().toString() ,maintenanceDate.getModel().getValue())){
                        mssg= "Update Maintenance Date berhasil, Success";

                        this.dispose();
                        new ManageMaintenanceVehiclePage();
                    }else{
                        mssg = "Update Maintenance Date gagal, Error";
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
