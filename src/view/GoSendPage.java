package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.CalculateCost;
import controller.CalculateDistance;
import controller.CustomerOrder;
import controller.FetchDataRegion;
import controller.ServiceAndPackage;
import model.Class.location.Region;
import model.Enum.TipeBarang;
import model.Enum.TypeOfService;
import model.Enum.VehicleType;

public class GoSendPage {
    public GoSendPage() {
        initGoSendPage();
    }

    private void initGoSendPage() {
        JFrame frame = new JFrame("Go Send");

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450, 350);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); 

        JPanel formPanel = new JPanel(new GridLayout(10, 1, 5,5));
        formPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        ArrayList<Region> regions = FetchDataRegion.getRegions();
        String[] regionNames = new String[regions.size()];
        for (int i = 0; i < regions.size(); i++) {
            regionNames[i] = regions.get(i).getVillage() + ", " + regions.get(i).getDistrict();
        }

        JLabel currLocLabel = new JLabel("Current Location:");
        JTextField currLocTextField = new JTextField();
        JComboBox<String> currLocComboBox = new JComboBox<>(regionNames);
        currLocComboBox.setMaximumRowCount(10);

        JLabel destinationLabel = new JLabel("Destination:");
        JTextField destinationTextField = new JTextField();
        JComboBox<String> destinationComboBox = new JComboBox<>(regionNames);
        destinationComboBox.setMaximumRowCount(10);

        JLabel typeLabel = new JLabel("Type of Goods:");
        TipeBarang[] types = {TipeBarang.NORMAL, TipeBarang.FRAGILE, TipeBarang.RADIOACTIVE, TipeBarang.CORROSIVE, TipeBarang.FLAMMABLE, TipeBarang.HAZARD};
        JComboBox<TipeBarang> typeComboBox = new JComboBox<>(types);

        JLabel weightLabel = new JLabel("Weight of Goods (kg):");
        JTextField weightTextField = new JTextField();

        formPanel.add(currLocLabel);
        formPanel.add(currLocTextField);
        formPanel.add(currLocComboBox);
        formPanel.add(destinationLabel);
        formPanel.add(destinationTextField);
        formPanel.add(destinationComboBox);
        formPanel.add(typeLabel);
        formPanel.add(typeComboBox);
        formPanel.add(weightLabel);
        formPanel.add(weightTextField);

        JPanel actionPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        actionPanel.setBorder(new EmptyBorder(0, 60, 10, 60));

        JButton orderButton = new JButton("Make Order");
        JButton backButton = new JButton("Back to Main Menu");

        actionPanel.add(orderButton);
        actionPanel.add(backButton);

        frame.getContentPane().add(formPanel, BorderLayout.CENTER);
        frame.getContentPane().add(actionPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currLocAddress = currLocTextField.getText();
                int currLocRegionID = currLocComboBox.getSelectedIndex() + 1;
                Region currLocRegion = FetchDataRegion.getRegion(currLocRegionID);
                
                String destinationAdress = destinationTextField.getText();
                int destinationRegionID = destinationComboBox.getSelectedIndex() + 1;
                Region destinationRegion = FetchDataRegion.getRegion(destinationRegionID);

                double distance = CalculateDistance.calculateDistance(currLocRegion, destinationRegion);
                TipeBarang barang = ServiceAndPackage.getTipeBarang(String.valueOf(typeComboBox.getSelectedItem()));
                double fareKm = VehicleType.BIKE.getFareKm();
                double fare5Kg = barang.getFare5Kg();
                float weight = Float.valueOf(weightTextField.getText());
                double cost = CalculateCost.calculateGoSend(distance, fareKm, fare5Kg, weight);

                boolean status = CustomerOrder.createOrder(TypeOfService.GOCAR, VehicleType.CAR, currLocAddress, currLocRegionID, destinationAdress, destinationRegionID, cost);

                if (status) {
                    JOptionPane.showMessageDialog(frame, "ORDER SUKSES!");
                } else {
                    JOptionPane.showMessageDialog(frame, "ORDER GAGAL!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }

                frame.dispose();
                new CustomerPage();
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            new CustomerPage();
        });
    }

    // public static void main(String[] args) {
    //     new GoSendPage();
    // }
}
