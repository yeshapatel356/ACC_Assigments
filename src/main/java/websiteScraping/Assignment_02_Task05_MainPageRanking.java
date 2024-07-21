package websiteScraping;

import java.util.*;

import java.util.Scanner;

public class Assignment_02_Task05_MainPageRanking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Assignment_02_Task05_PageRankCalculator calculator = new Assignment_02_Task05_PageRankCalculator();
        String[] fileNames = {"hotelPricingScrap_Page1.csv", "hotelPricingScrap_Page2.csv"};

        calculator.processCSV(fileNames);

        // Ask user for keyword input
        System.out.print("Enter a keyword to search page rankings: ");
        String keyword = scanner.nextLine().toLowerCase();

        // Display top 5 highest ranked pages for the entered keyword
       // calculator.displayHighestRankedPages(keyword, 5);

        scanner.close();
    }
}
