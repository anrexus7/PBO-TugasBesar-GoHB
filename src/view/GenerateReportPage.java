package view;

import controller.GeneratingReport;
import controller.ManagingBlackList;
import controller.ManagingCustomer;
import model.Class.user.Customer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GenerateReportPage extends JFrame {
    public GenerateReportPage() {
        showGenerateReport();
    }

    public void showGenerateReport() {
        this.setTitle("Manage Report");
        this.setSize(new Dimension(700, 400));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<String[]> data = GeneratingReport.getData();

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
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 6;
        gbc.insets = new Insets(0, 10, 10, 10);// Top, left, bottom, right padding
        JLabel header = new JLabel("Manage Report");
        header.setFont(new Font("SansSerif", Font.BOLD, 24));
        container.add(header, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);// Top, left, bottom, right padding
        container.add(new JLabel("user_id"), gbc);

        gbc.gridx++;
        container.add(new JLabel("complaint"), gbc);

        gbc.gridx++;
        container.add(new JLabel("created at"), gbc);

        gbc.gridx++;
        container.add(new JLabel("Action"), gbc);

        for (String[] datum : data) {
            gbc.gridy++;
            gbc.gridx = 0;
            container.add(new JLabel(datum[1]), gbc);

            gbc.gridx++;
            JTextArea textComplaint = new JTextArea();
            textComplaint.setLineWrap(true);
            textComplaint.setWrapStyleWord(true);

            textComplaint.setEditable(false);
            textComplaint.setText(datum[2]);
            container.add(textComplaint, gbc);

            gbc.gridx++;
            container.add(new JLabel(datum[3]), gbc);

            gbc.gridx++;
            JButton opened = new JButton("Set OPENED");
            container.add(opened, gbc);

            opened.addActionListener(e -> {
                if (GeneratingReport.changeStatus(datum[0])) {
                    JOptionPane.showMessageDialog(this, "Status report menjadi OPENED");
                    this.dispose();
                    new AdminPage();
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal mengubah status report");
                }
            });
        }

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
