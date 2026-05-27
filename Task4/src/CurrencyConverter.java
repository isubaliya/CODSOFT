import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
public class CurrencyConverter extends JFrame implements ActionListener {
    JLabel titleLabel, amountLabel, fromLabel, toLabel, resultLabel;
    JTextField amountField;
    JComboBox<String> fromBox, toBox;
    JButton convertButton;
    JPanel panel;
    String[] currencies = {
            "USD", "INR", "EUR", "GBP",
            "JPY", "AUD", "CAD"
    };
    CurrencyConverter() {
        // Frame
        setTitle("Currency Converter");
        setSize(500, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        // Main Panel
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(40, 30, 400, 320);
        panel.setBackground(Color.WHITE);
        panel.setBorder(new LineBorder(new Color(220,220,220),2,true));
        add(panel);
        // Title
        titleLabel = new JLabel("Currency Converter");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setBounds(80, 20, 300, 35);
        panel.add(titleLabel);
        // Amount Label
        amountLabel = new JLabel("Enter Amount");
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        amountLabel.setBounds(40, 80, 120, 25);
        panel.add(amountLabel);
        // Amount Field
        amountField = new JTextField();
        amountField.setBounds(40, 105, 320, 35);
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        amountField.setBorder(new LineBorder(Color.LIGHT_GRAY,1,true));
        panel.add(amountField);
        // From Label
        fromLabel = new JLabel("From Currency");
        fromLabel.setBounds(40, 150, 120, 25);
        fromLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(fromLabel);
        // From ComboBox
        fromBox = new JComboBox<>(currencies);
        fromBox.setBounds(40, 175, 140, 35);
        fromBox.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(fromBox);
        // To Label
        toLabel = new JLabel("To Currency");
        toLabel.setBounds(220, 150, 120, 25);
        toLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(toLabel);
        // To ComboBox
        toBox = new JComboBox<>(currencies);
        toBox.setBounds(220, 175, 140, 35);
        toBox.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(toBox);
        // Convert Button
        convertButton = new JButton("Convert");
        convertButton.setBounds(120, 235, 150, 40);
        convertButton.setFont(new Font("Arial", Font.BOLD, 16));
        convertButton.setBackground(new Color(41, 128, 185));
        convertButton.setForeground(Color.WHITE);
        convertButton.setFocusPainted(false);
        convertButton.setBorder(new LineBorder(new Color(41,128,185),1,true));
        convertButton.addActionListener(this);
        panel.add(convertButton);
        // Result Label
        resultLabel = new JLabel("Converted Amount = ");
        resultLabel.setBounds(40, 285, 320, 25);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(resultLabel);
        // Background
        getContentPane().setBackground(new Color(240, 242, 245));
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        try {
            double amount =
                    Double.parseDouble(amountField.getText());
            String from =
                    fromBox.getSelectedItem().toString();
            String to =
                    toBox.getSelectedItem().toString();
            // API URL
            String apiURL =
                    "https://open.er-api.com/v6/latest/" + from;
            URL url = new URL(apiURL);
            HttpURLConnection con =
                    (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(
                                    con.getInputStream()));
            String line;
            String response = "";
            while ((line = br.readLine()) != null) {
                response += line;
            }
            br.close();
            // Find Currency Rate
            String search = "\"" + to + "\":";
            int start =
                    response.indexOf(search) + search.length();
            int end =
                    response.indexOf(",", start);
            String rateText =
                    response.substring(start, end);
            double rate =
                    Double.parseDouble(rateText);
            double converted =
                    amount * rate;
            resultLabel.setText(
                    "Converted Amount = " +
                            String.format("%.2f", converted)
                            + " " + to);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please Enter Valid Amount"
            );
        }
    }
    public static void main(String[] args) {
        new CurrencyConverter();
    }
}