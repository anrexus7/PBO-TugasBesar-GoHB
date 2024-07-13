package view;

import controller.*;
import model.Class.location.Region;
import model.Class.restaurant.Item;
import model.Class.user.Customer;
import model.Enum.TipeBarang;
import model.Enum.TypeOfService;
import model.Enum.VehicleType;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener; // <-- Add this import for ListSelectionListener
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GoFoodPage {

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 550;
    private static final int LEFT_MARGIN = 10;
    private static final int RIGHT_MARGIN = 20;

    private JFrame frame;
    private JTable table;
    private List<String> selectedFoods = new ArrayList<>();

    public GoFoodPage() {
        showGoFoodPage();
    }

    private void showGoFoodPage() {
        frame = createFrame();
        frame.setLayout(null);

        JLabel titleLabel = createLabel("Go Food Service", LEFT_MARGIN, 10, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        frame.add(titleLabel);

        JTextField searchField = createTextField(LEFT_MARGIN, 50, FRAME_WIDTH - 2 * LEFT_MARGIN - 100, 30);
        frame.add(searchField);

        JButton searchButton = createButton("Search", FRAME_WIDTH - RIGHT_MARGIN - 100, 50, 100, 30);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<Item> arrItems = null;
                String[] columnNames = {"Name", "Price", "Stock", "Select"};

                if (searchField.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Please enter the name of restaurant", "Notification", JOptionPane.INFORMATION_MESSAGE);

                }
                else {

                    arrItems = SearchFood.searchFood(searchField.getText());

                }

                if (table != null) {
                    // Assuming the table is wrapped in a JScrollPane and added to the frame
                    Container parent = table.getParent();
                    while (!(parent instanceof JScrollPane) && parent != null) {
                        parent = parent.getParent();
                    }
                    if (parent != null) {
                        frame.remove(parent);
                    }
                }

                Object[][] data = null;

                if (arrItems != null) {

                    data = new Object[arrItems.size()][4];

                    for (int i = 0; i < arrItems.size(); i++) {

                        Item item = arrItems.get(i);
                        data[i][0] = item.getName();
                        data[i][1] = item.getHarga();
                        data[i][2] = item.getStock();
                        data[i][3] = false;

                    }

                    table = new JTable(data, columnNames) {

                        @Override
                        public Class<?> getColumnClass(int column) {
                            switch (column) {
                                case 3:
                                    return Boolean.class;
                                default:
                                    return String.class;
                            }
                        }
                    };

                    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                        @Override
                        public void valueChanged(ListSelectionEvent e) {
                            int selectedRow = table.getSelectedRow();
                            if (!e.getValueIsAdjusting()) {
                                if (selectedRow != -1) {
                                    String foodItem = (String) table.getValueAt(selectedRow, 0);
                                    boolean isSelected = (boolean) table.getValueAt(selectedRow, 3);
                                    if (isSelected) {
                                        selectedFoods.add(foodItem);
                                    } else {
                                        selectedFoods.remove(foodItem);
                                    }
                                    table.clearSelection();
                                }
                            }
                        }
                    });

                    JScrollPane scrollPane = new JScrollPane(table);
                    scrollPane.setBounds(LEFT_MARGIN, 100, FRAME_WIDTH - 2 * LEFT_MARGIN - 10, 150);
                    frame.add(scrollPane);
                    frame.revalidate();
                    frame.repaint();

                }
                else if (!searchField.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Restaurant not found", "Notification", JOptionPane.INFORMATION_MESSAGE);

                }

            }

        });
        frame.add(searchButton);

        ArrayList<Region> regions = FetchDataRegion.getRegions();
        String[] regionNames = new String[regions.size()];
        for (int i = 0; i < regions.size(); i++) {
            regionNames[i] = regions.get(i).getVillage() + ", " + regions.get(i).getDistrict();

        }

        JLabel currLocLabel = createLabel("Current Location:", LEFT_MARGIN, 270, FRAME_WIDTH - 2 * LEFT_MARGIN - 10, 30);
        JTextField currLocTextField = createTextField(LEFT_MARGIN, 310, FRAME_WIDTH - 2 * LEFT_MARGIN - 10, 30);
        JComboBox<String> currLocComboBox = createComboBox(LEFT_MARGIN, 350, FRAME_WIDTH - 2 * LEFT_MARGIN - 10, 30);

        frame.add(currLocLabel);
        frame.add(currLocTextField);
        frame.add(currLocComboBox);

        JButton orderButton = createButton("Make an Order", LEFT_MARGIN, 390, FRAME_WIDTH - 2 * LEFT_MARGIN - 10, 30);
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!selectedFoods.isEmpty()) {
                    StringBuilder message = new StringBuilder("Selected Foods:\n");
                    for (String food : selectedFoods) {
                        message.append(food).append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, message.toString());

                    //konek db gon :v

                } else {
                    JOptionPane.showMessageDialog(frame, "No food selected.");
                }
            }
        });
        frame.add(orderButton);

        JButton backButton = createButton("Back to Main Menu", LEFT_MARGIN, 430, FRAME_WIDTH - 2 * LEFT_MARGIN - 10, 30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new CustomerPage();
            }
        });
        frame.add(backButton);

        frame.setVisible(true);
    }

    private JFrame createFrame() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2);
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2);

        JFrame frame = new JFrame("Go Food");
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return frame;
    }

    private JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        return label;
    }

    private JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        return textField;
    }

    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        return button;
    }

    private JComboBox<String> createComboBox(int x, int y, int width, int height){
        ArrayList<Region> regions = FetchDataRegion.getRegions();
        String[] regionNames = new String[regions.size()];
        for (int i = 0; i < regions.size(); i++) {
            regionNames[i] = regions.get(i).getVillage() + ", " + regions.get(i).getDistrict();
        }
        JComboBox<String> currLocComboBox = new JComboBox<>(regionNames);
        currLocComboBox.setBounds(x, y, width, height);
        currLocComboBox.setMaximumRowCount(10);

        return currLocComboBox;
    }

}
