package assignment2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Assignment_02_Task05_KeywordExtractor {
    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
            "a", "an", "the", "and", "or", "but", "is", "are", "was", "were",
            "in", "of", "on", "for", "with", "at", "by", "to", "from", "all", 
            "any", "both", "each", "few", "most", "some", "such","be", "been", 
            "have", "has", "had", "having", "do", "does", "did", "doing","will", 
            "would", "shall", "should", "may", "might", "must", "can", "could"
    ));

    public void parseCSV(String hotelFileName, Assignment2_Task5_AVLTree avlTree) {
        try (BufferedReader objectBufferedReader = new BufferedReader(new FileReader(hotelFileName))) {
            String fileLine;
            while ((fileLine = objectBufferedReader.readLine()) != null) {
                String[] cells = fileLine.split(",");
                for (String cell : cells) {
                    String[] words = cell.toLowerCase().split("\\W+");
                    for (String word : words) {
                        if (!STOP_WORDS.contains(word) && !word.isEmpty()) {
                            avlTree.insertKeyword(word);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}