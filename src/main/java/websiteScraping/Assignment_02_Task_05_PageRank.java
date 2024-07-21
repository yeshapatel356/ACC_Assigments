package websiteScraping;

public class Assignment_02_Task_05_PageRank implements Comparable<Assignment_02_Task_05_PageRank> {
    Assignment_02_Task_05_WebPage webPage;
    int rank;

    public Assignment_02_Task_05_PageRank(Assignment_02_Task_05_WebPage webPage, int rank) {
        this.webPage = webPage;
        this.rank = rank;
    }

    @Override
    public int compareTo(Assignment_02_Task_05_PageRank other) {
        return Integer.compare(other.rank, this.rank); // for max-heap property
    }
}
