package view;

import controller.UpdateProfileAdmin;
import model.Class.SingletonManagers.SingletonManagerAdmin;

import javax.swing.*;
import java.awt.*;

public class ViewProfileAdmin extends JFrame{
    public ViewProfileAdmin() {
        showProfileAdmin();
    }

    private void showProfileAdmin() {
        this.setTitle("Edit Profile");
        this.setSize(new Dimension(400, 250)); //width, height
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel container = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 0);// Top, left, bottom, right padding


        gbc.gridx=0;
        gbc.gridy=0;
        gbc.anchor = GridBagConstraints.WEST;
        JButton back = new JButton("Back");
        container.add(back, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel header = new JLabel("Edit Profile");
        header.setFont(new Font("SansSerif", Font.BOLD, 20));
        container.add(header, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;
        JButton update = new JButton("Save");
        container.add(update, gbc);

        gbc.gridx=0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        container.add(new JLabel("Nama : "), gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 0, 10, 10);
        JTextField name =  new JTextField(SingletonManagerAdmin.getInstance().getAdmin().getName(), 15);
        container.add(name, gbc);

        gbc.gridy++;
        gbc.gridx--;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 10, 10, 20);
        container.add(new JLabel("Telephone : "), gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 0, 10, 10);
        JTextField telp =  new JTextField(SingletonManagerAdmin.getInstance().getAdmin().getPhoneNumber(), 15);
        container.add(telp, gbc);

        gbc.gridy++;
        gbc.gridx--;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 10, 10, 20);
        container.add(new JLabel("Email : "), gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 0, 10, 10);
        JTextField email =  new JTextField(SingletonManagerAdmin.getInstance().getAdmin().getEmail(), 15);
        container.add(email,gbc);

        this.add(container);
        this.setVisible(true);

        back.addActionListener(e ->{
            new AdminPage();
            this.dispose();
        });

        update.addActionListener(e -> {
            if(!UpdateProfileAdmin.validatingInput(name.getText(), telp.getText(), email.getText())){
                JOptionPane.showMessageDialog(null, "Pastikan semua data terisi", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                if(!UpdateProfileAdmin.update(name.getText(), telp.getText(), email.getText())){
                    JOptionPane.showMessageDialog(null, "Update tidak berhasil", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(this, "Update berhasil");
                    new CustomerPage();
                    this.dispose();
                }
            }
        });
    }
}
