import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MultiplicationTableGenerator {
    private JFrame frame;
    private JTextArea textArea;
    private JTextField textField;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MultiplicationTableGenerator app = new MultiplicationTableGenerator();
            app.frame.setVisible(true);
        });
    }

     MultiplicationTableGenerator() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Multiplication Table Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 450);
        frame.setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top panel for input
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        inputPanel.add(new JLabel("Enter Number: "), BorderLayout.WEST);
        inputPanel.add(textField, BorderLayout.CENTER);

        JButton generateButton = new JButton("Generate");
        generateButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        generateButton.addActionListener(this::generateTable);
        inputPanel.add(generateButton, BorderLayout.EAST);

        panel.add(inputPanel, BorderLayout.NORTH);

        // Center panel for table
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setEditable(false);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Bottom panel for number buttons
        JPanel numberPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        for (int i = 1; i <= 9; i++) {
            String label = String.valueOf(i);
            JButton btn = new JButton(label);
            btn.setFont(new Font("Tahoma", Font.BOLD, 18));
            btn.addActionListener(e -> {
                textField.setText(label);
                generateTable(null);
            });
            numberPanel.add(btn);
        }

        panel.add(numberPanel, BorderLayout.SOUTH);

        frame.setContentPane(panel);
    }

    void generateTable(ActionEvent e) {
        String input = textField.getText().trim();
        if (input.isEmpty()) {
            textArea.setText("Please enter a number.");
            return;
        }

        try {
            int number = Integer.parseInt(input);
            StringBuilder table = new StringBuilder();
            for (int i = 1; i <= 10; i++) {
                table.append(String.format("%2d x %2d = %3d\n", number, i, number * i));
            }
            textArea.setText(table.toString());
        } catch (NumberFormatException ex) {
            textArea.setText("Invalid input! Please enter a valid number.");
        }
    }
}
