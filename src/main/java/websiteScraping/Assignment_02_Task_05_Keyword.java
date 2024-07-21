package websiteScraping;

public class Assignment_02_Task_05_Keyword implements Comparable<Assignment_02_Task_05_Keyword> {
    String word;
    int frequency;

    public Assignment_02_Task_05_Keyword(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(Assignment_02_Task_05_Keyword other) {
        return Integer.compare(other.frequency, this.frequency); // for descending order
    }
}
