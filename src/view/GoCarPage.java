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
import model.Enum.CarTypeOfCarService;
import model.Enum.TypeOfService;
import model.Enum.VehicleType;

public class GoCarPage {
    public GoCarPage() {
        initGoCarPage();
    }

    private void initGoCarPage() {
        JFrame frame = new JFrame("Go Car");

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450, 300);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); 

        JPanel formPanel = new JPanel(new GridLayout(8, 1, 5,5));
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

        JLabel serviceLabel = new JLabel("Type of Car Service:");
        CarTypeOfCarService[] services = {CarTypeOfCarService.HEMAT, CarTypeOfCarService.PRIORITAS, CarTypeOfCarService.COMFORT, CarTypeOfCarService.BIASA};
        JComboBox<CarTypeOfCarService> serviceComboBox = new JComboBox<>(services);

        formPanel.add(currLocLabel);
        formPanel.add(currLocTextField);
        formPanel.add(currLocComboBox);
        formPanel.add(destinationLabel);
        formPanel.add(destinationTextField);
        formPanel.add(destinationComboBox);
        formPanel.add(serviceLabel);
        formPanel.add(serviceComboBox);

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
                CarTypeOfCarService service = ServiceAndPackage.getTypeOfCarService(String.valueOf(serviceComboBox.getSelectedItem()));
                double fareKm = VehicleType.CAR.getFareKm();
                double addFareKm = service.getAddFareKm();
                double cost = CalculateCost.calculateGoCar(distance, fareKm, addFareKm);

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
    //     new GoCarPage();
    // }
}
