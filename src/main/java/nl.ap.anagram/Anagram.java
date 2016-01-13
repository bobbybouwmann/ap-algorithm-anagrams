package nl.ap.anagram;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Anagram {

    protected Object[] results;

    public Anagram(String filename) {
        // Initialize a scanner for the file.
        Scanner scanner = getScannerFromFile(filename);

        // Find all words that look the same.
        Map<String, HashSet<String>> map = findAnagrams(scanner);

        // Set the results.
        printResults(map);
    }

    /**
     * Find the anagrams for the given scanner.
     *
     * @param scanner Scanner
     * @return Map
     */
    private Map findAnagrams(Scanner scanner) {
        // Initialize a hash map.
        Map<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();

        // Read the content from the scanner.
        while (scanner.hasNext()) {
            // Make sure we get the next word that only consist our of letters.
            // Als make sure it's lowercase.
            String word = scanner.next().replaceAll("[^a-zA-Z]+", "").toLowerCase();

            // Sort the word in alphabetical order.
            String sortedWord = this.sorting(word);

            // Check if the current sorted word exists in our list.
            // If not we need to create a new place for it.
            HashSet<String> list = map.get(sortedWord);

            // Make a place for it.
            if (list == null) {
                map.put(sortedWord, list = new HashSet<String>());
            }

            // Add the word to the list
            list.add(word);
        }

        return map;
    }

    /**
     * Set the results for programme.
     *
     * @param results Object[]
     */
    private void setResultsFromMap(HashSet<String> results) {
        this.results = results.toArray();
    }

    /**
     * Get a scanner for the given filename.
     *
     * @param filename String
     * @return Scanner
     */
    private Scanner getScannerFromFile(String filename) {
        Scanner scanner = null;

        try {
            scanner = new Scanner(new File(this.getFileLocation(filename)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return scanner;
    }

    /**
     * Sort a string in alphabetical order.
     *
     * @param s String
     * @return String sorted word.
     */
    private String sorting(String s) {
        char[] a = s.toCharArray();

        Arrays.sort(a);

        return new String(a);
    }

    /**
     * Get the path to the text file.
     *
     * @param file String filename.
     * @return String filepath.
     */
    private String getFileLocation(String file) {
        return System.getProperty("user.dir") + "/src/main/java/nl.ap.anagram/" + file;
    }

    /**
     * Print the results of the found anagrams.
     *
     * @param map Map<String, HashSet<String>>
     */
    private void printResults(Map<String, HashSet<String>> map) {
        for (String key : map.keySet()) {
            Set<String> words = map.get(key);

            if (words.size() > 1) {
                System.out.print("Results for '" + key + "': ");

                for (String word : words) {
                    System.out.print(word + ", ");
                }

                System.out.println();
            }
        }
    }
}
