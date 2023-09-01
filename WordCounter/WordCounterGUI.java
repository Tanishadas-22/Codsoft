package WordCounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;
import java.util.Scanner;



public class WordCounterGUI {
    private JFrame frame;
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;

    public WordCounterGUI() {
        frame = new JFrame("Word Counter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        JLabel inputLabel = new JLabel("Enter text or provide a file path:");
        topPanel.add(inputLabel, BorderLayout.NORTH);

        inputTextArea = new JTextArea(10, 50);
        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);
        topPanel.add(inputScrollPane, BorderLayout.CENTER);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout()); // Use FlowLayout for button positioning

        JButton countButton = new JButton("Count Words");
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countWords();
            }
        });
        buttonPanel.add(countButton);

        // New Load File Button
        JButton loadButton = new JButton("Load from File");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFromFile();
            }
        });
        buttonPanel.add(loadButton);

        topPanel.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel to the main panel

        frame.add(topPanel, BorderLayout.NORTH);

        outputTextArea = new JTextArea(10, 50);
        outputTextArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);
        frame.add(outputScrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void countWords() {
        String input = inputTextArea.getText();
        if (!input.isEmpty()) {
            int totalWords = WordCounter.countTotalWords(input);
            Set<String> uniqueWords = WordCounter.getUniqueWords(input);
            Map<String, Integer> wordFrequency = WordCounter.getWordFrequency(input);

            outputTextArea.setText("Total words: " + totalWords + "\n\n");
            outputTextArea.append("Total unique words: " + uniqueWords.size() + "\n\n");
            outputTextArea.append("Word frequency:\n");
            for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
                outputTextArea.append(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        }
    }

    private void loadFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                Scanner scanner = new Scanner(selectedFile);
                StringBuilder fileContent = new StringBuilder();
                while (scanner.hasNextLine()) {
                    fileContent.append(scanner.nextLine()).append("\n");
                }
                scanner.close();
                inputTextArea.setText(fileContent.toString());
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(frame, "Error loading file: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WordCounterGUI();
            }
        });
    }
}
