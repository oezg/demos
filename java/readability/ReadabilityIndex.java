package readability;

public abstract class ReadabilityIndex {
    protected ReadabilityStatistics statistics;
    protected String name;
    protected double score;

    public ReadabilityIndex(ReadabilityStatistics statistics) {
        this.statistics = statistics;
        calculateScore();
    }

    public ReadabilityScore getReadabilityScore() {
        return new ReadabilityScore(
                name, score, estimateAge()
        );
    }

    protected abstract void calculateScore();

    protected int estimateAge() {
        return (int)Math.ceil(score) + 5;
    }
}
