package websiteScraping;
import java.util.*;

public class Assignment_02_Task05_PageRankCalculator {
    private Assignment_02_Task05_AVLTree avlTree;
    private PriorityQueue<PageRankEntry> maxHeap;

    public Assignment_02_Task05_PageRankCalculator() {
        this.avlTree = new Assignment_02_Task05_AVLTree();
        // Initialize the maxHeap with a custom comparator
        this.maxHeap = new PriorityQueue<>(new MaxHeapComparator());
    }

    public void processCSV(String[] fileNames) {
        Assignment_02_Task05_KeywordFrequencyCounter extractor = new Assignment_02_Task05_KeywordFrequencyCounter();

        for (String fileName : fileNames) {
            extractor.parseCSV(fileName, avlTree);
        }

        List<Map.Entry<String, Integer>> sortedKeywords = avlTree.getSortedKeywords();

        // Calculate rank of each page based on frequency of search keywords
        // For simplicity, assume each page is represented by its filename
        Map<String, Integer> pageRanks = new HashMap<>();

        for (Map.Entry<String, Integer> entry : sortedKeywords) {
            String keyword = entry.getKey();
            int frequency = entry.getValue();

            // Assuming each keyword frequency contributes equally to page rank
            // Adjust this logic based on specific requirements (e.g., weighting)
            List<String> pages = getPageListFor(keyword); // Implement this method

            for (String page : pages) {
                pageRanks.put(page, pageRanks.getOrDefault(page, 0) + frequency);
            }
        }

        // Build the max-heap based on page ranks
        for (Map.Entry<String, Integer> entry : pageRanks.entrySet()) {
            maxHeap.offer(new PageRankEntry(entry.getKey(), entry.getValue()));
        }
    }

    public void displayHighestRankedPages(int count) {
        System.out.println("Top " + count + " Highest Ranked Pages:");
        int numPages = Math.min(count, maxHeap.size());

        for (int i = 0; i < numPages; i++) {
            PageRankEntry entry = maxHeap.poll();
            System.out.println(entry.getPageName() + " - Rank: " + entry.getRank());
        }
    }

    // Method to get a list of pages related to a keyword
    private List<String> getPageListFor(String keyword) {
        // Implement this method based on your data structure or actual data source
        // For simplicity, returning dummy data
        if (keyword.equals("hotel")) {
            return Arrays.asList("page1.html", "page2.html", "page3.html");
        } else if (keyword.equals("pricing")) {
            return Arrays.asList("page2.html", "page3.html", "page4.html");
        } else {
            return Collections.emptyList();
        }
    }

    // Custom comparator class for max-heap (descending order)
    private static class MaxHeapComparator implements Comparator<PageRankEntry> {
        @Override
        public int compare(PageRankEntry e1, PageRankEntry e2) {
            // Compare ranks in descending order
            return Integer.compare(e2.getRank(), e1.getRank());
        }
    }

    // Helper class to represent PageRank entry
    private static class PageRankEntry implements Comparable<PageRankEntry> {
        private String pageName;
        private int rank;

        public PageRankEntry(String pageName, int rank) {
            this.pageName = pageName;
            this.rank = rank;
        }

        public String getPageName() {
            return pageName;
        }

        public int getRank() {
            return rank;
        }

        @Override
        public int compareTo(PageRankEntry other) {
            return Integer.compare(this.rank, other.rank);
        }
    }
}
