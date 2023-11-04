package readability;

public class ColemanLiauReadabilityIndex extends ReadabilityIndex{
    public ColemanLiauReadabilityIndex(ReadabilityStatistics statistics) {
        super(statistics);
        name = "Coleman–Liau index";
    }

    @Override
    protected void calculateScore() {
        score = 5.88 * statistics.characterCount() / statistics.wordCount();
        score -= 29.6 * statistics.sentenceCount() / statistics.wordCount() + 15.8;
    }
}
