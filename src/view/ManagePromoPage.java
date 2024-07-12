package view;

import controller.ManagingPromo;
import model.Class.transaction.Promo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManagePromoPage extends JFrame {

    public ManagePromoPage() {
        showManagePromo();
    }

    public void showManagePromo() {
        this.setTitle("Manage Promo");
        this.setSize(new Dimension(650, 400));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Promo> data = ManagingPromo.getData();
        JButton viewButton = new JButton("View");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JPanel editor = new JPanel();
        editor.add(viewButton);
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
        JLabel header = new JLabel("Manage Promo (add/edit/delete)");
        header.setFont(new Font("SansSerif", Font.BOLD, 20));
        container.add(header, gbc);

        int i = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);// Top, left, bottom, right padding
        for (Promo datum : data) {
            gbc.gridy++;
            gbc.gridx = 0;
            container.add(new JLabel(i + ". "), gbc);

            gbc.gridx++;
            container.add(new JLabel("Voucher id : " + datum.getPromoID()), gbc);

            gbc.gridx++;
            container.add(new JLabel("Kode : " + datum.getCode()), gbc);

            gbc.gridx++;
            container.add(editor, gbc);

            viewButton.addActionListener(e -> {
                showDialog(datum);
            });

            deleteButton.addActionListener(e -> {
                int response = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus promo dengan kode "+datum.getCode());

                if(response == JOptionPane.YES_OPTION){
                    if(ManagingPromo.deletePromo(datum.getPromoID())){
                        JOptionPane.showMessageDialog(this,"Berhasil menghapus promo");
                        new AdminPage();
                        this.dispose();
                    }else{
                        JOptionPane.showMessageDialog(this,"Gagal menghapus promo");
                    }
                }
            });

            editButton.addActionListener(e -> {
                new EditPromoPage(datum);
            });
        }

        gbc.gridx = 0;
        gbc.gridy++;
        JButton add = new JButton("Add Promo");
        container.add(add, gbc);

        add.addActionListener(e ->{
            new RegisterPromoPage();
            this.dispose();
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

    private void showDialog(Promo datum) {
        JDialog dialog = new JDialog(this, "View Voucher", true);
        dialog.setSize(250, 150);
        dialog.setLayout(new FlowLayout());

        JLabel serviceType = new JLabel("Berlaku untuk " + datum.getTypeOfService());
        dialog.add(serviceType);

        JLabel potongan = new JLabel("Potongan sebesar " + datum.getDiscount());
        dialog.add(potongan);

        JLabel to = new JLabel("Berlaku hingga " + datum.getExpiryDate());
        dialog.add(to);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}
