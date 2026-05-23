import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -------------------- BANK ACCOUNT CLASS --------------------
class BankAccount {
    private double balance;
    // Constructor
    public BankAccount(double balance) {
        this.balance = balance;
    }
    // Get Balance
    public double getBalance() {
        return balance;
    }
    // Deposit Method
    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }
        balance = balance + amount;
        return true;
    }
    // Withdraw Method
    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance = balance - amount;
        return true;
    }
}
// -------------------- ATM UI CLASS --------------------
public class ATM_UI extends JFrame implements ActionListener {
    // Account Object
    BankAccount account = new BankAccount(10000);
    // Labels
    JLabel title;
    JLabel subtitle;
    JLabel amountLabel;
    JLabel balanceTitle;
    JLabel balanceValue;
    // Text Field
    JTextField amountField;
    // Buttons
    JButton depositButton;
    JButton withdrawButton;
    JButton checkBalanceButton;
    JButton clearButton;
    JButton exitButton;
    // Panels
    JPanel topPanel;
    JPanel centerPanel;
    JPanel bottomPanel;
    // -------------------- CONSTRUCTOR --------------------
    ATM_UI() {
        // Frame Settings
        setTitle("Smart ATM Machine");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        // -------------------- TOP PANEL --------------------
        topPanel = new JPanel();
        topPanel.setBounds(0, 0, 700, 100);
        topPanel.setLayout(null);
        topPanel.setBackground(new Color(25, 45, 80));
        add(topPanel);
        // Title
        title = new JLabel("SMART ATM");
        title.setBounds(230, 15, 300, 40);
        title.setFont(new Font("Arial", Font.BOLD, 34));
        title.setForeground(Color.WHITE);
        topPanel.add(title);
        // Subtitle
        subtitle = new JLabel("Secure Banking System");
        subtitle.setBounds(235, 55, 250, 25);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitle.setForeground(Color.WHITE);
        topPanel.add(subtitle);
        // -------------------- CENTER PANEL --------------------
        centerPanel = new JPanel();
        centerPanel.setBounds(40, 120, 600, 220);
        centerPanel.setLayout(null);
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(180, 180, 180), 2));
        add(centerPanel);
        // Amount Label
        amountLabel = new JLabel("Enter Amount");
        amountLabel.setBounds(50, 40, 180, 35);
        amountLabel.setFont(new Font("Arial", Font.BOLD, 22));
        centerPanel.add(amountLabel);
        // Amount Text Field
        amountField = new JTextField();
        amountField.setBounds(240, 40, 280, 40);
        amountField.setFont(new Font("Arial", Font.PLAIN, 20));
        amountField.setBorder(
                BorderFactory.createLineBorder(
                        new Color(120, 120, 120), 2));
        centerPanel.add(amountField);
        // Deposit Button
        depositButton = createButton("Deposit");
        depositButton.setBounds(50, 120, 220, 50);
        centerPanel.add(depositButton);
        // Withdraw Button
        withdrawButton = createButton("Withdraw");
        withdrawButton.setBounds(310, 120, 220, 50);
        centerPanel.add(withdrawButton);
        // -------------------- BOTTOM PANEL --------------------
        bottomPanel = new JPanel();
        bottomPanel.setBounds(40, 360, 600, 80);
        bottomPanel.setLayout(null);
        bottomPanel.setBackground(new Color(245, 245, 245));
        bottomPanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(200, 200, 200), 2));
        add(bottomPanel);
        // Balance Title
        balanceTitle = new JLabel("Available Balance");
        balanceTitle.setBounds(20, 20, 220, 35);
        balanceTitle.setFont(new Font("Arial", Font.BOLD, 22));
        bottomPanel.add(balanceTitle);
        // Balance Value
        balanceValue = new JLabel("Rs. 10000");
        balanceValue.setBounds(260, 20, 180, 35);
        balanceValue.setFont(new Font("Arial", Font.BOLD, 24));
        balanceValue.setForeground(new Color(0, 120, 0));
        bottomPanel.add(balanceValue);
        // Check Balance Button
        checkBalanceButton = createButton("Check Balance");
        checkBalanceButton.setBounds(400, 10, 170, 25);
        checkBalanceButton.setFont(new Font("Arial", Font.BOLD, 14));
        bottomPanel.add(checkBalanceButton);
        // Clear Button
        clearButton = createButton("Clear");
        clearButton.setBounds(400, 42, 80, 25);
        clearButton.setFont(new Font("Arial", Font.BOLD, 13));
        bottomPanel.add(clearButton);
        // Exit Button
        exitButton = createButton("Exit");
        exitButton.setBounds(490, 42, 80, 25);
        exitButton.setFont(new Font("Arial", Font.BOLD, 13));
        bottomPanel.add(exitButton);
        // Display Frame
        setVisible(true);
    }
    // -------------------- BUTTON METHOD --------------------
    JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(25, 45, 80));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);
        return button;
    }
    // -------------------- ACTION EVENTS --------------------
    public void actionPerformed(ActionEvent e) {
        String input = amountField.getText();
        // -------------------- DEPOSIT --------------------
        if (e.getSource() == depositButton) {
            try {
                double amount = Double.parseDouble(input);
                if (account.deposit(amount)) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Rs. " + amount
                                    + " Deposited Successfully");
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Please Enter Valid Amount");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Input");
            }
        }
        // -------------------- WITHDRAW --------------------
        if (e.getSource() == withdrawButton) {
            try {
                double amount = Double.parseDouble(input);
                if (account.withdraw(amount)) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Rs. " + amount
                                    + " Withdrawn Successfully");
                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Insufficient Balance");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Input");
            }
        }
        // -------------------- CHECK BALANCE --------------------
        if (e.getSource() == checkBalanceButton) {
            JOptionPane.showMessageDialog(
                    this,
                    "Current Balance : Rs. "
                            + account.getBalance());
        }
        // -------------------- CLEAR --------------------
        if (e.getSource() == clearButton) {
            amountField.setText("");
        }
        // -------------------- EXIT --------------------
        if (e.getSource() == exitButton) {
            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Do You Want To Exit?",
                    "Exit",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
        // -------------------- UPDATE BALANCE --------------------
        balanceValue.setText(
                "Rs. " + account.getBalance());
    }
    // -------------------- MAIN METHOD --------------------
    public static void main(String[] args) {
        new ATM_UI();
    }
}