package view;

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
    private static final int FRAME_HEIGHT = 400;
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
                // Displaying dummy data for demonstration
                String[] columnNames = {"Food Item", "Price", "Select", "Action"};
                Object[][] data = {
                        {"Burger", "$5.00", false, new JButton("testing")},
                        {"Pizza", "$8.00", false, new JButton("testing")},
                        {"Salad", "$4.00", false, new JButton("testing")}
                };

                if (table != null) {
                    frame.remove(table);
                }

                table = new JTable(data, columnNames) {
                    @Override
                    public Class<?> getColumnClass(int column) {
                        switch (column) {
                            case 2:
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
                                boolean isSelected = (boolean) table.getValueAt(selectedRow, 2);
                                System.out.println(isSelected);
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
                scrollPane.setBounds(LEFT_MARGIN, 100, FRAME_WIDTH - 2 * LEFT_MARGIN, 150);
                frame.add(scrollPane);
                frame.revalidate();
                frame.repaint();
            }
        });
        frame.add(searchButton);

        JButton orderButton = createButton("Make an Order", LEFT_MARGIN, 270, FRAME_WIDTH - 2 * LEFT_MARGIN - 10, 30);
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!selectedFoods.isEmpty()) {
                    StringBuilder message = new StringBuilder("Selected Foods:\n");
                    for (String food : selectedFoods) {
                        message.append(food).append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, message.toString());
                } else {
                    JOptionPane.showMessageDialog(frame, "No food selected.");
                }
            }
        });
        frame.add(orderButton);

        JButton backButton = createButton("Back to Main Menu", LEFT_MARGIN, 310, FRAME_WIDTH - 2 * LEFT_MARGIN - 10, 30);
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

}
