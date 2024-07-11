package view;

import controller.ManagingPromo;
import model.Class.DateLabelFormatter;
import model.Class.transaction.Promo;
import model.Enum.TypeOfService;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class EditPromoPage extends JFrame {
    public EditPromoPage(Promo promo) {
        showEditPromo(promo);
    }

    private void showEditPromo(Promo promo) {
        this.setTitle("Manage Promo");
        this.setSize(new Dimension(650, 400));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        gbc.anchor = GridBagConstraints.WEST;
        container.add(new JLabel("Promo Code"), gbc);

        gbc.gridx++;
        JTextField promoCode = new JTextField(promo.getCode(), 20);
        container.add(promoCode, gbc);

        gbc.gridy++;
        gbc.gridx--;
        container.add(new JLabel("Discount amount"), gbc);

        gbc.gridx++;
        JTextField discount = new JTextField(Double.toString(promo.getDiscount()),20);
        container.add(discount, gbc);

        gbc.gridy++;
        gbc.gridx--;
        container.add(new JLabel("Service type"), gbc);

        gbc.gridx++;
        JComboBox<Object> serviceType = new JComboBox<>(TypeOfService.values());
        serviceType.setSelectedItem(promo.getTypeOfService());
        container.add(serviceType, gbc);

        gbc.gridy++;
        gbc.gridx--;
        container.add(new JLabel("Valid From"), gbc);

        gbc.gridx++;
        JDatePickerImpl validFrom = new JDatePickerImpl(createDatePanel(promo.getStartDate()), new DateLabelFormatter());
        container.add(validFrom, gbc);

        gbc.gridy++;
        gbc.gridx--;
        container.add(new JLabel("Valid To"), gbc);

        gbc.gridx++;
        JDatePickerImpl validTo = new JDatePickerImpl(createDatePanel(promo.getExpiryDate()), new DateLabelFormatter());
        container.add(validTo, gbc);

        gbc.gridy++;
        gbc.gridx--;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton submit = new JButton("Submit");
        container.add(submit, gbc);

        back.addActionListener(e -> {
            new ManagePromoPage();
            this.dispose();
        });

        submit.addActionListener(e -> {
            String mssg = "";
            if(ManagingPromo.validatingInput(promoCode.getText(), discount.getText(), validFrom.getModel(), validTo.getModel())){
                mssg= "ISI SEMUA FIELD NYING, Error";
            }else{
                try{
                    Integer.parseInt(discount.getText());
                    if(ManagingPromo.updatingPromo(promo.getPromoID(), promoCode.getText(), discount.getText(), serviceType.getSelectedItem(), validFrom.getModel().getValue(), validTo.getModel().getValue())){
                        mssg= "Update Promo berhasil, Success";

                        new ManagePromoPage();
                        this.dispose();
                    }else{
                        mssg = "Update promo gagal, Error";
                    }
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "ISI PAKE ANGKA DISCOUNT GEBLEG :v", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            String[] splitMssg = mssg.split(", ");
            if(splitMssg[1].equalsIgnoreCase("Error")){
                JOptionPane.showMessageDialog(null, splitMssg[0], "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, splitMssg[0]);
            }
        });

        this.add(container);
        this.setVisible(true);
    }

    private JDatePanelImpl createDatePanel(Date value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(value);

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();

        model.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        model.setSelected(true);

        JDatePanelImpl datePanel;
        datePanel = new JDatePanelImpl(model, p);

        return datePanel;
    }
}
