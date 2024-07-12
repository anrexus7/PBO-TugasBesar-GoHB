package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ManagingBalance;

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

        topUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double amount = Double.valueOf(amounTextField.getText());
                boolean status = ManagingBalance.TopUpBalance(amount);

                if (status) {
                    JOptionPane.showMessageDialog(frame, "TOPUP SUKSES!");
                } else {
                    JOptionPane.showMessageDialog(frame, "TOPUP GAGAL!", "ERROR", JOptionPane.ERROR_MESSAGE);
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
    //     new TopUpPage();
    // }
}
