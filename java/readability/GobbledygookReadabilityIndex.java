package readability;

public class GobbledygookReadabilityIndex extends ReadabilityIndex {
    public GobbledygookReadabilityIndex(ReadabilityStatistics statistics) {
        super(statistics);
        name = "Simple Measure of Gobbledygook";
    }

    @Override
    protected void calculateScore() {
        score = 1.043 * Math.sqrt(30.0 * statistics.polysyllableCount() / statistics.sentenceCount());
        score += 3.1291;
    }
}
