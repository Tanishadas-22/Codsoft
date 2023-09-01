package AtmInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Bank Account class and ATM class remain unchanged

// ATM GUI class
class ATMGUI extends JFrame {
    private BankAccount account;
    private JTextField amountField;
    private JTextArea outputArea;
    private JLabel balanceLabel;

    public ATMGUI(BankAccount account) {
        this.account = account;
        setTitle("ATM Machine");
        setSize(400, 400); // Increased height for additional content
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout());

        balanceLabel = new JLabel("Current Balance: $" + account.getBalance());
        infoPanel.add(balanceLabel);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel amountLabel = new JLabel("Enter amount:");
        inputPanel.add(amountLabel);

        amountField = new JTextField(10);
        inputPanel.add(amountField);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDeposit();
            }
        });
        inputPanel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleWithdraw();
            }
        });
        inputPanel.add(withdrawButton);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(infoPanel, BorderLayout.NORTH);
        controlPanel.add(inputPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

    private void handleDeposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            account.deposit(amount);
            updateOutput("Deposited $" + amount + " successfully. Your new balance is: $" + account.getBalance());
        } catch (NumberFormatException ex) {
            updateOutput("Invalid amount.");
        }
    }

    private void handleWithdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (account.withdraw(amount)) {
                updateOutput("Withdrawn $" + amount + " successfully. Your new balance is: $" + account.getBalance());
            } else {
                updateOutput("Insufficient balance for withdrawal.");
            }
        } catch (NumberFormatException ex) {
            updateOutput("Invalid amount.");
        }
    }

    private void updateOutput(String message) {
        outputArea.append(message + "\n");
        balanceLabel.setText("Current Balance: $" + account.getBalance());
        amountField.setText("");
    }
}


