package websiteScraping;

import java.util.*;
import java.util.regex.*;

public class Assignment_02_Task_05_WebPage {
	 String content;
	    TreeMap<String, Integer> keywordFrequency;

	    public Assignment_02_Task_05_WebPage(String content) {
	        this.content = content;
	        this.keywordFrequency = new TreeMap<>();
	        parseContent();
	    }

	    private void parseContent() {
	        Pattern pattern = Pattern.compile("\\w+");
	        Matcher matcher = pattern.matcher(content.toLowerCase());
	        while (matcher.find()) {
	            String keyword = matcher.group();
	            keywordFrequency.put(keyword, keywordFrequency.getOrDefault(keyword, 0) + 1);
	        }
	    }

	    public TreeMap<String, Integer> getKeywordFrequency() {
	        return keywordFrequency;
	    }

	    public String getContent() {
	        return content;
	    }
}
