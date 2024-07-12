package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TopUpPage {
    public TopUpPage() {
        initTopUpPage();
    }

    private void initTopUpPage() {
        JFrame frame = new JFrame("Top Up");

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450, 150);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); 

        JPanel formPanel = new JPanel(new GridLayout(2, 1, 5,5));
        formPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel amountLabel = new JLabel("Amount (IDR):");
        JTextField amounTextField = new JTextField();

        formPanel.add(amountLabel);
        formPanel.add(amounTextField);

        JPanel actionPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        actionPanel.setBorder(new EmptyBorder(0, 60, 10, 60));

        JButton topUpButton = new JButton("Top Up Balance");
        JButton backButton = new JButton("Back to Main Menu");

        actionPanel.add(topUpButton);
        actionPanel.add(backButton);

        frame.getContentPane().add(formPanel, BorderLayout.CENTER);
        frame.getContentPane().add(actionPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new TopUpPage();
    }
}
