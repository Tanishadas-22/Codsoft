package WordCounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordCounter {
    public static int countTotalWords(String input) {
        String[] words = splitWords(input);
        return words.length;
    }

    public static int countTotalWordsFromFile(String filePath) throws FileNotFoundException {
        String input = readFile(filePath);
        String[] words = splitWords(input);
        return words.length;
    }

    public static Set<String> getUniqueWords(String input) {
        String[] words = splitWords(input);
        return getUniqueWords(words);
    }

    public static Set<String> getUniqueWordsFromFile(String filePath) throws FileNotFoundException {
        String input = readFile(filePath);
        String[] words = splitWords(input);
        return getUniqueWords(words);
    }

    public static Map<String, Integer> getWordFrequency(String input) {
        String[] words = splitWords(input);
        return getWordFrequency(words);
    }

    public static Map<String, Integer> getWordFrequencyFromFile(String filePath) throws FileNotFoundException {
        String input = readFile(filePath);
        String[] words = splitWords(input);
        return getWordFrequency(words);
    }

    private static String[] splitWords(String input) {
        return input.split("[\\s\\p{Punct}]+");
    }

    private static Set<String> getUniqueWords(String[] words) {
        Set<String> uniqueWords = new HashSet<>();
        for (String word : words) {
            uniqueWords.add(word.toLowerCase());
        }
        return uniqueWords;
    }

    private static Map<String, Integer> getWordFrequency(String[] words) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            String lowercaseWord = word.toLowerCase();
            wordFrequency.put(lowercaseWord, wordFrequency.getOrDefault(lowercaseWord, 0) + 1);
        }
        return wordFrequency;
    }

    private static String readFile(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        StringBuilder fileContent = new StringBuilder();
        while (scanner.hasNextLine()) {
            fileContent.append(scanner.nextLine()).append("\n");
        }
        scanner.close();
        return fileContent.toString();
    }
}
