package skillcraft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SCT_SD_3 {
    JFrame frame;
    JTextField[][] cells = new JTextField[9][9];

    public SCT_SD_3() {
        frame = new JFrame("Sudoku Puzzle Solver");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                for (int i = 0; i <= 9; i++) {
                    int thickness = (i % 3 == 0) ? 4 : 1;
                    g.fillRect(i * (getWidth() / 9) - thickness / 2, 0, thickness, getHeight());
                    g.fillRect(0, i * (getHeight() / 9) - thickness / 2, getWidth(), thickness);
                }
            }
        };
        panel.setLayout(new GridLayout(9, 9));

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                cells[row][col].setFont(new Font("Arial", Font.BOLD, 20));
                cells[row][col].setBackground((row / 3 + col / 3) % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                cells[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.add(cells[row][col]);
            }
        }

        JButton solveButton = new JButton("Solve");
        solveButton.setFont(new Font("Arial", Font.BOLD, 16));
        solveButton.setBackground(Color.GREEN.darker());
        solveButton.setForeground(Color.WHITE);
        solveButton.setToolTipText("Click to solve the puzzle.");
        solveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                solveSudoku();
            }
        });

        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.setBackground(Color.RED.darker());
        resetButton.setForeground(Color.WHITE);
        resetButton.setToolTipText("Click to clear the grid.");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGrid();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        buttonPanel.add(solveButton);
        buttonPanel.add(resetButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void solveSudoku() {
        int[][] grid = new int[9][9];

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String text = cells[row][col].getText();
                if (!text.isEmpty()) {
                    try {
                        int value = Integer.parseInt(text);
                        if (value < 1 || value > 9) {
                            throw new NumberFormatException();
                        }
                        grid[row][col] = value;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                                frame,
                                "Invalid input at cell (" + (row + 1) + ", " + (col + 1) + ").\n" +
                                        "Please enter a number between 1 and 9.",
                                "Input Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                } else {
                    grid[row][col] = 0;
                }
            }
        }

        if (solve(grid)) {
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    cells[row][col].setText(String.valueOf(grid[row][col]));
                    cells[row][col].setBackground(Color.CYAN);
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "No solution exists!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetGrid() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col].setText("");
                cells[row][col].setBackground((row / 3 + col / 3) % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
            }
        }
    }

    private boolean solve(int[][] grid) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(grid, row, col, num)) {
                            grid[row][col] = num;
                            if (solve(grid)) {
                                return true;
                            }
                            grid[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int[][] grid, int row, int col, int num) {
        for (int x = 0; x < 9; x++) {
            if (grid[row][x] == num || grid[x][col] == num || grid[row - row % 3 + x / 3][col - col % 3 + x % 3] == num) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new SCT_SD_3();
    }
}
