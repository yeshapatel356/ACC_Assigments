package websiteScraping;

import java.util.*;

public class Assignment_02_Task_05_KeywordSorter {
    public static List<Assignment_02_Task_05_Keyword> sortKeywords(TreeMap<String, Integer> keywordFrequency) {
        List<Assignment_02_Task_05_Keyword> keywords = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : keywordFrequency.entrySet()) {
            keywords.add(new Assignment_02_Task_05_Keyword(entry.getKey(), entry.getValue()));
        }
        Collections.sort(keywords); // Using built-in sort (uses merge sort)
        return keywords;
    }
}
