package websiteScraping;

import java.util.*;
import java.io.*;

public class Assignment_02_Task_05_PageRanking {
    public static void main(String[] args) {
        try {
            List<Assignment_02_Task_05_WebPage> webPages = Assignment_02_Task_05_CSVReader.readCSV("hotelPricing_scrap_page1.csv");

            List<String> searchKeywords = Arrays.asList("hotel", "inn");

            Assignment_02_Task_05_PageRankingSystem rankingSystem = new Assignment_02_Task_05_PageRankingSystem();
            rankingSystem.rankPages(webPages, searchKeywords);

            List<Assignment_02_Task_05_WebPage> topRankedPages = rankingSystem.getTopRankedPages(2);

            for (Assignment_02_Task_05_WebPage page : topRankedPages) {
                System.out.println("Ranked Page Content: " + page.getContent());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
