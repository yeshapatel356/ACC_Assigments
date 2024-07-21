package websiteScraping;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HotelTrivagoSraper {

	public static void main(String[] args) {
		// Set up WebDriver for Chrome
		WebDriverManager.chromedriver().setup();

		// Scrape data from page 1
		scrapeData("https://www.trivago.ca/en-CA/srl/hotels-windsor-canada?search=200-37652;dr-20240701-20240703",
				"hotelPricingScrap.csv");


		// Scrape data from page 2
		scrapeData("https://www.trivago.ca/en-CA/srl/hotels-windsor-canada?search=200-37652;dr-20240701-20240703;pa-2",
				"hotelPricingScrap.csv");
	}

	// Method to check if a file is empty
    public static boolean isEmptyFile(String filename) {
        File file = new File(filename);
        return file.length() == 0;
    }

	public static void scrapeData(String url, String csvFileName) {
		// Create a new instance of the ChromeDriver
		WebDriver driver = new ChromeDriver();

		// Navigate to the target URL
		driver.get(url);

		int waitInSeconds = 50;
		// Print Open Website successfully
		System.out.println("--------Status: Successfully Open Website www.trivago.ca and waiting for " + waitInSeconds
				+ " sec--------\n");

		driver.manage().timeouts().implicitlyWait(waitInSeconds, java.util.concurrent.TimeUnit.SECONDS);

		// Print status wait complete
		System.out.println("--------Status: Waiting period completed successfully --------\n");

		// Find all hotel elements
		List<WebElement> hotelTrivagoElements = driver
				.findElements(By.cssSelector("li[data-testid='accommodation-list-element']"));

		try {
			// Create FileWriter object of file hotelPricing_Scrap.csv
			FileWriter csvWriter = new FileWriter(csvFileName,true);

			// Write the CSV file header if the file is empty
            if (isEmptyFile(csvFileName)) {
                csvWriter.append("Hotel Name,Hotel Price (CA) per night\n");
            }

			// Initialize option counter
			int serialNum = 1;

			// Print the status of scraping 1st page
			System.out.println("--------Scraping page of trivago hotels list from trivago.ca--------\n");

			// Iterate through each hotel element
			for (WebElement hotelTrivagoElement : hotelTrivagoElements) {
				// Get hotel name
				String hotelTrivagoName = hotelTrivagoElement.findElement(By.cssSelector("span[itemprop='name']"))
						.getText();

				// Escape double quotes in the hotel name
				hotelTrivagoName = hotelTrivagoName.replace("\"", "\"\"");

				// Get hotel price
				String hotelTrivagoPrice = hotelTrivagoElement
						.findElement(By.cssSelector("span[data-testid='recommended-price']")).getText();

				// Escape double quotes in the hotel price
				hotelTrivagoPrice = hotelTrivagoPrice.replace("\"", "\"\"");

				// Write the extracted data to the CSV file
				csvWriter.append(hotelTrivagoName).append("\",\"")
						.append(hotelTrivagoPrice).append("\"\n");

				// Print the extracted data with option number
				System.out.println("Option " + (serialNum++));
				System.out.println("Hotel Name: " + hotelTrivagoName);
				System.out.println("Hotel Price per night: " + hotelTrivagoPrice + " CA");
				System.out
						.println("<------------------------------------------------------------------------------>");
			}

			// Close the CSV writer
			csvWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
		// Close the browser
		driver.quit();
	}
}
