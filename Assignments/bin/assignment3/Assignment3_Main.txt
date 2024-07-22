package assignment3;

import java.util.*;

public class Assignment3_Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Assignment3_PageRankCalculator calculator = new Assignment3_PageRankCalculator();
        String[] fileNames_yesha = {"hotelPricingScrap_Page1.csv", "hotelPricingScrap_Page2.csv", "hotelPricingScrap_Page3.csv", "hotelPricingScrap_Page4.csv"};
        boolean continueChecking_yesha = true;

        while (continueChecking_yesha) {
            // Prompting the user to input keywords for ranking files.
            System.out.print("Enter keywords separated by commas to rank files: ");
            String input_yesha = scanner.nextLine().trim().toLowerCase();

            // Splitting user input into individual keywords and trimming whitespace from each.
            String[] keywordArray_yesha = input_yesha.split(",");
            List<String> keywords_yesha = new ArrayList<>();

            // Trim each keyword to remove leading and trailing spaces
            for (String keyword_yesha : keywordArray_yesha) {
                keywords_yesha.add(keyword_yesha.trim());
            }

            // Processing files and calculating page rankings based on entered keywords.
            calculator.processFiles(fileNames_yesha, keywords_yesha);

            // Displaying page rankings and querying the user to check rankings again.
            calculator.displayPageRankings(keywords_yesha);

            // Ask user if they want to check rankings again
            System.out.print("Do you want to check rankings again? (yes/no): ");
            String choice_yesha = scanner.nextLine().trim().toLowerCase();

            // Check if user wants to continue checking rankings
            if (!choice_yesha.equals("yes") && !choice_yesha.equals("y")) {
                continueChecking_yesha = false;
            }
        }

        // Thank user for using the program
        System.out.println("Thank you for using the program. Goodbye!");
        scanner.close();
    }
}
