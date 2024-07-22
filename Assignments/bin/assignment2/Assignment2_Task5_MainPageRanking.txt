package assignment2;

import java.util.*;

public class Assignment2_Task5_MainPageRanking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Assignment2_Task5_PageRankCalculator calculator = new Assignment2_Task5_PageRankCalculator();
        String[] fileNames = {"hotelPricingScrap_Page1.csv", "hotelPricingScrap_Page2.csv", "hotelPricingScrap_Page3.csv", "hotelPricingScrap_Page4.csv"};

        boolean continueChecking = true;

        while (continueChecking) {
            // Prompt user for keywords input
            System.out.print("Enter keywords separated by commas to rank CSV files: ");
            String userInput = scanner.nextLine().trim().toLowerCase();

            // Split user input into keywords
            String[] keywordArray = userInput.split(",");
            List<String> keywordsList = new ArrayList<>();
            for (String keywordFromArray : keywordArray) {
            	// Trim each keyword to remove leading/trailing spaces
                keywordsList.add(keywordFromArray.trim()); 
            }

            // Process CSV files and calculate page rankings based on the keywords
            calculator.processCSV(fileNames, keywordsList);

            // Display page rankings for the entered keywords
            calculator.displayPageRankings(keywordsList);

            // Prompt the user to see if they want another attempt.
            System.out.print("Would you like to check the rankings once more? (y/n) ");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (!choice.equals("yes") && !choice.equals("y")) {
                continueChecking = false;
            }
        }

        System.out.println("Thanks for using the program! See ya later.");
        scanner.close();
    }
}