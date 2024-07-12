package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.FetchDataRegion;
import model.Class.location.Region;

public class GoCarPage {
    public GoCarPage() {
        initGoCarPage();
    }

    private void initGoCarPage() {
        JFrame frame = new JFrame("Go Car");

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450, 250);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); 

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 5,5));
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

        formPanel.add(currLocLabel);
        formPanel.add(currLocTextField);
        formPanel.add(currLocComboBox);
        formPanel.add(destinationLabel);
        formPanel.add(destinationTextField);
        formPanel.add(destinationComboBox);

        JPanel actionPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        actionPanel.setBorder(new EmptyBorder(0, 60, 10, 60));

        JButton orderButton = new JButton("Make Order");
        JButton backButton = new JButton("Back to Main Menu");

        actionPanel.add(orderButton);
        actionPanel.add(backButton);

        frame.getContentPane().add(formPanel, BorderLayout.CENTER);
        frame.getContentPane().add(actionPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    // public static void main(String[] args) {
    //     new GoCarPage();
    // }
}
