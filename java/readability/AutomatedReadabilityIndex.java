package readability;

public class AutomatedReadabilityIndex extends ReadabilityIndex {

    public AutomatedReadabilityIndex(ReadabilityStatistics statistics) {
        super(statistics);
        name = "Automated Readability Index";
    }

    @Override
    protected void calculateScore() {
        score = 4.71 * statistics.characterCount() / statistics.wordCount();
        score += 0.5 * statistics.wordCount() / statistics.sentenceCount() - 21.43;
    }
}
