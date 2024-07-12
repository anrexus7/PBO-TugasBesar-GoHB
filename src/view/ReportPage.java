package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import controller.SubmitReport;

public class ReportPage {

    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 350;
    private static final int LEFT_MARGIN = 10;
    private static final int RIGHT_MARGIN = 20;

    public ReportPage() {
        showReportPage();
    }

    private void showReportPage() {
        JFrame frame = createFrame();
        frame.setLayout(null);

        JLabel reportLabel = createLabel("Enter your report:", LEFT_MARGIN, 10, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        frame.add(reportLabel);

        JTextArea reportArea = createTextArea(LEFT_MARGIN, 50, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 150);
        frame.add(reportArea);

        JButton submitButton = createButton("Submit", LEFT_MARGIN, 210, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        submitButton.addActionListener(e -> {
            SubmitReport.insertReport(reportArea.getText());
            JOptionPane.showMessageDialog(null, "Report submitted!");
            frame.dispose();
            new DriverPage();
        });
        frame.add(submitButton);

        JButton backButton = createButton("Back", LEFT_MARGIN, 250, FRAME_WIDTH - LEFT_MARGIN - RIGHT_MARGIN, 30);
        backButton.addActionListener(e -> {
            frame.dispose();
            new DriverPage();
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

        JFrame frame = new JFrame("Report");
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return frame;
    }

    private JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        return label;
    }

    private JTextArea createTextArea(int x, int y, int width, int height) {
        JTextArea textArea = new JTextArea();
        textArea.setBounds(x, y, width, height);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return textArea;
    }

    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        return button;
    }

    public static void main(String[] args) {
        new ReportPage();
    }
}
