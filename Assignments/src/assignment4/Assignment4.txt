package assignment4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assignment4 {

    public static void main(String[] args) {
        String fileName_yesha = "hoteltext.txt";
        List<String> extractedEmailAddresses_yesha = extractEmailsFromFile(fileName_yesha);

        // Displaying the extracted email addresses
        System.out.println("Extracted Email Addresses:");
        for (String emailAddress_yesha : extractedEmailAddresses_yesha) {
            System.out.println(emailAddress_yesha);
        }
    }

    public static List<String> extractEmailsFromFile(String fileName_yesha) {
        List<String> extractedEmailAddresses_yesha = new ArrayList<>();
        BufferedReader bufferedReader_yesha = null;

        try {
            bufferedReader_yesha = new BufferedReader(new FileReader(fileName_yesha));
            StringBuilder fileContentBuilder_yesha = new StringBuilder();
            String line_yesha = bufferedReader_yesha.readLine();

            while (line_yesha != null) {
                fileContentBuilder_yesha.append(line_yesha);
                fileContentBuilder_yesha.append(System.lineSeparator());
                line_yesha = bufferedReader_yesha.readLine();
            }

            String fileContent_yesha = fileContentBuilder_yesha.toString();
            // Regular expression to find email addresses
            String emailRegex_yesha = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b";
            Pattern emailPattern_yesha = Pattern.compile(emailRegex_yesha);
            Matcher emailMatcher_yesha = emailPattern_yesha.matcher(fileContent_yesha);

            while (emailMatcher_yesha.find()) {
                String emailAddress_yesha = emailMatcher_yesha.group();
                extractedEmailAddresses_yesha.add(emailAddress_yesha);
            }

        } catch (IOException e_yesha) {
            e_yesha.printStackTrace();
        } finally {
            try {
                if (bufferedReader_yesha != null) {
                    bufferedReader_yesha.close();
                }
            } catch (IOException e_yesha) {
                e_yesha.printStackTrace();
            }
        }

        return extractedEmailAddresses_yesha;
    }
}
