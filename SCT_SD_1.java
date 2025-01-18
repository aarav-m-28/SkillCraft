package skillcraft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SCT_SD_1 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Temperature Converter");
        frame.setSize(400, 350);
        frame.setLayout(new GridLayout(6, 2));
       
        
        JLabel inputLabel = new JLabel("Enter Temperature:");
        JTextArea inputField = new JTextArea();

        JLabel unitLabel = new JLabel("Select Unit:");
        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        JComboBox<String> unitComboBox = new JComboBox<>(units);

        JLabel outputLabel = new JLabel("Converted Temperatures:");
        
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);

         JButton clearButton = new JButton("Clear");
        JButton convertButton = new JButton("Convert");

        frame.add(inputLabel);
        frame.add(inputField);
        frame.add(unitLabel);
        frame.add(unitComboBox);
        frame.add(outputLabel);
        frame.add(new JScrollPane(outputArea));
        frame.add(clearButton);
        frame.add(convertButton);
        
        convertButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                try {
                    double temp = Double.parseDouble(inputField.getText());
                    String unit =  (String) unitComboBox.getSelectedItem();
                    double celsius, fahrenheit, kelvin;

                    if (unit.equals("Celsius")) {
                        celsius = temp;
                        fahrenheit = (temp * 9 / 5) + 32;
                        kelvin = temp + 273.15;
                    } else if (unit.equals("Fahrenheit")) {
                        celsius = (temp - 32) * 5 / 9;
                        fahrenheit = temp;
                        kelvin = celsius + 273.15;
                    } else {
                        celsius = temp - 273.00;
                        fahrenheit = (celsius * 9 / 5) + 32;
                        kelvin = temp;
                    }

                    outputArea.setText(String.format("Celsius: %.2f\nFahrenheit: %.2f\nKelvin: %.2f", celsius, fahrenheit, kelvin));
                } catch (Exception ex) {
                    outputArea.setText("Please enter a valid number.");
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                inputField.setText("");
                outputArea.setText("");
                unitComboBox.setSelectedIndex(0);
            }
        });

        frame.setVisible(true);
    }
}
