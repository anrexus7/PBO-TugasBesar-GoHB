package view;

import controller.ManagingMaintenanceVehicle;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManageMaintenanceVehiclePage extends JFrame {
    public ManageMaintenanceVehiclePage() {
        showManageMaintenanceVehicle();
    }

    public void showManageMaintenanceVehicle() {
        this.setTitle("Manage Maintenance Vehicle");
        this.setSize(new Dimension(800, 400));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Object[]> data = ManagingMaintenanceVehicle.getData();
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JPanel editor = new JPanel();
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
        JLabel header = new JLabel("Manage Maintenance Vehicle (add/edit/delete)");
        header.setFont(new Font("SansSerif", Font.BOLD, 20));
        container.add(header, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);// Top, left, bottom, right padding
        container.add(new JLabel("Vehicle ID"), gbc);

        gbc.gridx++;
        container.add(new JLabel("Driver ID"), gbc);

        gbc.gridx++;
        container.add(new JLabel("Schedule date"), gbc);

        gbc.gridx++;
        container.add(new JLabel("Status"), gbc);

        gbc.gridx++;
        container.add(new JLabel("Action"), gbc);


        for (Object[] datum : data) {
            gbc.gridy++;
            gbc.gridx = 0;
            container.add(new JLabel(String.valueOf(datum[1])), gbc);

            gbc.gridx++;
            container.add(new JLabel(String.valueOf(datum[2])), gbc);

            gbc.gridx++;
            container.add(new JLabel(String.valueOf(datum[3])), gbc);

            gbc.gridx++;
            container.add(new JLabel(String.valueOf(datum[4])), gbc);

            gbc.gridx++;
            container.add(editor, gbc);

            deleteButton.addActionListener(e -> {
                int response = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin schedule maintenance ? ");

                if(response == JOptionPane.YES_OPTION){
                    int id = Integer.parseInt((String) datum[0]);
                    if(ManagingMaintenanceVehicle.deleteMaintenance(id)){
                        JOptionPane.showMessageDialog(this,"Berhasil menghapus Maintenance");
                        this.dispose();
                        new AdminPage();
                    }else{
                        JOptionPane.showMessageDialog(this,"Gagal menghapus Maintenance");
                    }
                }
            });

            editButton.addActionListener(e -> {
                this.dispose();
                new EditMaintenancePage(datum);
            });
        }

        gbc.gridx = 0;
        gbc.gridy++;
        JButton add = new JButton("Add Maintenance");
        container.add(add, gbc);

        add.addActionListener(e ->{
            this.dispose();
            new RegisterMaintenancePage();
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
