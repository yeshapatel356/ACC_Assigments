package websiteScraping;

import java.util.*;

public class Assignment_02_Task_05_PageRankingSystem {
    PriorityQueue<Assignment_02_Task_05_PageRank> maxHeap;

    public Assignment_02_Task_05_PageRankingSystem() {
        this.maxHeap = new PriorityQueue<>();
    }

    public void rankPages(List<Assignment_02_Task_05_WebPage> webPages, List<String> searchKeywords) {
        for (Assignment_02_Task_05_WebPage webPage : webPages) {
            int rank = calculateRank(webPage, searchKeywords);
            maxHeap.add(new Assignment_02_Task_05_PageRank(webPage, rank));
        }
    }

    private int calculateRank(Assignment_02_Task_05_WebPage webPage, List<String> searchKeywords) {
        int rank = 0;
        TreeMap<String, Integer> keywordFrequency = webPage.getKeywordFrequency();
        for (String keyword : searchKeywords) {
            rank += keywordFrequency.getOrDefault(keyword, 0);
        }
        return rank;
    }

    public List<Assignment_02_Task_05_WebPage> getTopRankedPages(int n) {
        List<Assignment_02_Task_05_WebPage> topRankedPages = new ArrayList<>();
        for (int i = 0; i < n && !maxHeap.isEmpty(); i++) {
            topRankedPages.add(maxHeap.poll().webPage);
        }
        return topRankedPages;
    }
}
