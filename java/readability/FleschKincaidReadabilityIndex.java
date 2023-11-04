package readability;

public class FleschKincaidReadabilityIndex extends ReadabilityIndex{
    public FleschKincaidReadabilityIndex(ReadabilityStatistics statistics) {
        super(statistics);
        name = "Flesch–Kincaid readability tests";
    }

    @Override
    protected void calculateScore() {
        score = 0.39 * statistics.wordCount() / statistics.sentenceCount();
        score += 11.8 * statistics.syllableCount() / statistics.wordCount() - 15.59;
    }
}
