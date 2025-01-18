package skillcraft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SCT_SD_2 extends JFrame implements ActionListener {

    private Random rand = new Random();
    private int number;
    private int attempts;
    private int score;

    private JLabel titleLabel;
    private JLabel instructionsLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel resultLabel;
    private JButton resetButton;

    public SCT_SD_2() {
        setTitle("Number Guessing Game");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(50, 50, 50));
        titleLabel = new JLabel("Welcome to the Number Guessing Game!");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titlePanel.add(titleLabel);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1, 10, 10));
        centerPanel.setBackground(new Color(230, 240, 255));

        instructionsLabel = new JLabel("Guess the number between 1 and 100. You have 10 attempts.", SwingConstants.CENTER);
        instructionsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        centerPanel.add(instructionsLabel);

        guessField = new JTextField();
        guessField.setFont(new Font("Arial", Font.PLAIN, 18));
        guessField.setHorizontalAlignment(JTextField.CENTER);
        centerPanel.add(guessField);

        guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.BOLD, 16));
        guessButton.setBackground(new Color(50, 150, 250));
        guessButton.setForeground(Color.WHITE);
        guessButton.setFocusPainted(false);
        guessButton.addActionListener(this);
        centerPanel.add(guessButton);

        resultLabel = new JLabel("Your result will be displayed here.", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        resultLabel.setForeground(Color.DARK_GRAY);
        centerPanel.add(resultLabel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(230, 240, 255));
        resetButton = new JButton("Play Again");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.setBackground(new Color(250, 80, 80));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusPainted(false);
        resetButton.setEnabled(false);
        resetButton.addActionListener(e -> resetGame());
        bottomPanel.add(resetButton);

        add(titlePanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        initializeGame();
    }

    private void initializeGame() {
        number = rand.nextInt(100) + 1;
        attempts = 0;
        score = 11;
        resultLabel.setText("Your result will be displayed here.");
        guessField.setText("");
        guessField.setEnabled(true);
        guessButton.setEnabled(true);
        resetButton.setEnabled(false);
    }

    private void resetGame() {
        initializeGame();
    }

    public void actionPerformed(ActionEvent e) {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;
            score--;

            if (guess < number) {
                resultLabel.setText("Guess higher! Attempts left: " + (10 - attempts));
            } else if (guess > number) {
                resultLabel.setText("Guess lower! Attempts left: " + (10 - attempts));
            } else {
                resultLabel.setText("Correct! You guessed the number in " + attempts + " attempts. Score: " + score + "/10");
                endGame();
            }

            if (attempts >= 10 && guess != number) {
                resultLabel.setText("You ran out of attempts! The number was: " + number);
                endGame();
            }
        } catch (NumberFormatException x) {
            resultLabel.setText("Please enter a valid number.");
        }
        guessField.setText("");
    }

    private void endGame() {
        guessField.setEnabled(false);
        guessButton.setEnabled(false);
        resetButton.setEnabled(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SCT_SD_2 frame = new SCT_SD_2();
            frame.setVisible(true);
        });
    }
}
