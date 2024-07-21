package websiteScraping;

import java.io.*;
import java.util.*;

public class Assignment_02_Task_05_CSVReader {
    public static List<Assignment_02_Task_05_WebPage> readCSV(String filePath) throws IOException {
        List<Assignment_02_Task_05_WebPage> webPages = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        // Skip header line
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 1) {
                String content = parts[1];
                webPages.add(new Assignment_02_Task_05_WebPage(content));
            }
        }
        reader.close();
        return webPages;
    }
}
