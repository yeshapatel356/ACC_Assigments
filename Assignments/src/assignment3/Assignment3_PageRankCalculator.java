package assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Assignment3_PageRankCalculator {
    private List<Map.Entry<String, Integer>> pageRankEntries_yesha; // List to store page rank entries

    public Assignment3_PageRankCalculator() {
        this.pageRankEntries_yesha = new ArrayList<>(); // Initialize list to store page rank entries
    }

    public void processFiles(String[] fileNames_yesha, List<String> keywords_yesha) {
        // Clear existing page rank entries before processing new files
        pageRankEntries_yesha.clear();

        // Map to hold total counts of keywords in each file
        Map<String, Integer> totalKeywordCounts_yesha = new HashMap<>();

        // Process each file and count occurrences of keywords using Boyer-Moore algorithm
        for (String fileName_yesha : fileNames_yesha) {
            int totalOccurrences_yesha = 0;
            try (BufferedReader reader_yesha = new BufferedReader(new FileReader(fileName_yesha))) {
                StringBuilder contentBuilder_yesha = new StringBuilder();
                String line_yesha;
                // Read and concatenate each line of the file
                while ((line_yesha = reader_yesha.readLine()) != null) {
                    contentBuilder_yesha.append(line_yesha).append(" ");
                }
                String fileContent_yesha = contentBuilder_yesha.toString().toLowerCase(); // Convert content to lowercase
                // Calculate total occurrences of each keyword in the file content
                for (String keyword_yesha : keywords_yesha) {
                    Assignment3_BoyerMoore bmAlgorithm_yesha = new Assignment3_BoyerMoore(keyword_yesha);
                    totalOccurrences_yesha += bmAlgorithm_yesha.countOccurrences(fileContent_yesha);
                }
            } catch (IOException e_yesha) {
                e_yesha.printStackTrace(); // Handle errors in file reading
            }

            // Store file name and total occurrences in the map
            totalKeywordCounts_yesha.put(fileName_yesha, totalOccurrences_yesha);
        }

        // Convert map entries to list for sorting
        List<Map.Entry<String, Integer>> sortedEntries_yesha = new ArrayList<>(totalKeywordCounts_yesha.entrySet());

        // Sort entries based on total occurrences in descending order
        sortedEntries_yesha.sort((entry1_yesha, entry2_yesha) -> entry2_yesha.getValue().compareTo(entry1_yesha.getValue()));

        // Store sorted entries in pageRankEntries list
        pageRankEntries_yesha.addAll(sortedEntries_yesha);
    }

    public void displayPageRankings(List<String> keywords_yesha) {
        // Display rankings of files for the entered keywords
        System.out.println("Page Rankings for Keywords " + keywords_yesha + ":");
        for (int rank_yesha = 0; rank_yesha < pageRankEntries_yesha.size(); rank_yesha++) {
            Map.Entry<String, Integer> entry_yesha = pageRankEntries_yesha.get(rank_yesha);
            String fileName_yesha = entry_yesha.getKey();
            int totalOccurrences_yesha = entry_yesha.getValue();

            // Display rank (rank_yesha + 1 because rank_yesha is zero-based index) and total occurrences
            System.out.println("Rank " +(rank_yesha + 1) + ": " + fileName_yesha  + ", Total Occurrences: " + totalOccurrences_yesha);
        }
    }
}
