package view;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TypePromoPage extends JFrame{

    public TypePromoPage() {
        viewTypePromo();
    }

    private void viewTypePromo() {
        this.setTitle("Type of Service");
        this.setSize(new Dimension(400, 150));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton back = new JButton("Back");
        JButton car = new JButton("GoCar");
        JButton ride = new JButton("GoRide");
        JButton send = new JButton("GoSend");
        JButton food = new JButton("GoFood");

        JPanel container = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        container.add(back, gbc);
        back.addActionListener(e->{
            this.dispose();
            new ViewProfileCustomer();
        });

        gbc.gridy++;
        container.add(car, gbc);
        car.addActionListener(e ->{
            this.dispose();
            new PromoPage("GOCAR");
        });

        gbc.gridx++;
        container.add(ride, gbc);
        ride.addActionListener(e -> {
            this.dispose();
            new PromoPage("GORIDE");
        });

        gbc.gridy++;
        gbc.gridx--;
        container.add(send, gbc);
        send.addActionListener(e -> {
            this.dispose();
            new PromoPage("GOSEND");
        });

        gbc.gridx++;
        container.add(food, gbc);
        food.addActionListener(e ->{
            this.dispose();
            new PromoPage("GOFOOD");
        });

        this.add(container);
        this.setVisible(true);
    }
}
