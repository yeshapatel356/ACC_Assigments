package websiteScraping;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Assignment_02_Task05_KeywordFrequencyCounter {

	private static final Set<String> STOP_WORDS = new HashSet<>();

	static {
		STOP_WORDS.add("a");
		STOP_WORDS.add("an");
		STOP_WORDS.add("the");
		STOP_WORDS.add("and");
		STOP_WORDS.add("or");
		STOP_WORDS.add("but");
		STOP_WORDS.add("is");
		STOP_WORDS.add("are");
		STOP_WORDS.add("was");
		STOP_WORDS.add("were");
		STOP_WORDS.add("in");
		STOP_WORDS.add("of");
		STOP_WORDS.add("on");
		STOP_WORDS.add("for");
		STOP_WORDS.add("with");
		STOP_WORDS.add("at");
		STOP_WORDS.add("by");
		STOP_WORDS.add("to");
		STOP_WORDS.add("from");
	}

	public void parseCSV(String fileName,  Assignment_02_Task05_AVLTree avlTree) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] cells = line.split(",");
				for (String cell : cells) {
					String[] words = cell.toLowerCase().split("\\W+");
					for (String word : words) {
						if (!STOP_WORDS.contains(word) && !word.isEmpty()) {
							avlTree.insert(word);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
