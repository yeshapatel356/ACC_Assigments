package assignment2;

import java.util.*;

public class Assignment2_Task5_PageRankCalculator {
	private List<Map.Entry<String, Integer>> pageRankEntries; // Store entries for sorting

	public Assignment2_Task5_PageRankCalculator() {
		this.pageRankEntries = new ArrayList<>();
	}

	public void processCSV(String[] fileNames, List<String> keywords) {
		Assignment_02_Task05_KeywordExtractor extractor = new Assignment_02_Task05_KeywordExtractor();

		// Clear existing page rank entries
		pageRankEntries.clear();

		// Initialize map to store total keyword counts per file
		Map<String, Integer> totalKeywordCounts = new HashMap<>();

		// Process each CSV file and calculate total keyword count
		for (String fileName : fileNames) {
			Assignment2_Task5_AVLTree avlTree = new Assignment2_Task5_AVLTree();
			extractor.parseCSV(fileName, avlTree);

			// Calculate total keyword count for the current file
			int totalKeywordCount = 0;
			for (String keyword : keywords) {
				totalKeywordCount += avlTree.getFrequency(keyword);
			}

			// Store file name and total keyword count in the map
			totalKeywordCounts.put(fileName, totalKeywordCount);
		}

		// Convert map entries to list for sorting
		List<Map.Entry<String, Integer>> entries = new ArrayList<>(totalKeywordCounts.entrySet());

		// Sort entries based on total keyword count in descending order
		entries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

		// Store sorted entries in pageRankEntries
		pageRankEntries.addAll(entries);
	}

	public void displayPageRankings(List<String> keywords) {
		// Display rankings of CSV files for the entered keywords
		System.out.println("Page Rankings for Keywords " + keywords + ":");
		for (int i = 0; i < pageRankEntries.size(); i++) {
			Map.Entry<String, Integer> entry = pageRankEntries.get(i);
			String fileName = entry.getKey();
			int totalKeywordCount = entry.getValue();

			// Display rank (i + 1 because i is zero-based index) and total keyword count
			System.out.println(fileName + " - Rank: " + (i + 1) + ", Total Keyword Count: " + totalKeywordCount);
		}
	}
}