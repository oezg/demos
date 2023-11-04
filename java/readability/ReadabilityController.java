package readability;

import java.util.Arrays;
import java.util.regex.Pattern;

public class ReadabilityController {

    private static final int POLYSYLLABLE_THRESHOLD = 2;

    private String text;
    private ReadabilityStatistics statistics;

    public ReadabilityScore[] getReadabilityScores(ReadabilityScoreType scoreType) {
        return Arrays.stream(getReadabilityIndexes(scoreType))
                .map(ReadabilityIndex::getReadabilityScore)
                .toArray(ReadabilityScore[]::new);
    }

    private ReadabilityIndex[] getReadabilityIndexes(ReadabilityScoreType scoreType) {
        return switch (scoreType) {
            case ARI -> new ReadabilityIndex[] {new AutomatedReadabilityIndex(statistics)};
            case FK -> new ReadabilityIndex[] {new FleschKincaidReadabilityIndex(statistics)};
            case SMOG -> new ReadabilityIndex[] {new GobbledygookReadabilityIndex(statistics)};
            case CL -> new ReadabilityIndex[] {new ColemanLiauReadabilityIndex(statistics)};
            case all -> new ReadabilityIndex[] {
                    new AutomatedReadabilityIndex(statistics),
                    new FleschKincaidReadabilityIndex(statistics),
                    new GobbledygookReadabilityIndex(statistics),
                    new ColemanLiauReadabilityIndex(statistics)
            };
        };
    }

    public ReadabilityStatistics getReadabilityStatistics(String text) {
        this.text = text;
        this.statistics = new ReadabilityStatistics(
                countWords(),
                countSentences(),
                countCharacters(),
                countSyllables(),
                countPolysyllables()
        );
        return  statistics;
    }

    private long countWords() {
        return countMatching(RegularExpression.WORD.getPattern());
    }

    private long countSentences() {
        return countMatching(RegularExpression.SENTENCE.getPattern());
    }

    private long countCharacters() {
        return countMatching(RegularExpression.CHARACTER.getPattern());
    }

    private long countSyllables() {
        return countSyllables(text);
    }

    private long countSyllables(String text) {
        long vowelsCount = countMatching(RegularExpression.VOWEL.getPattern(), text);
        long numberCount = countMatching(RegularExpression.NUMBER.getPattern(), text);
        long wordEndE = countMatching(RegularExpression.WORD_END_E.getPattern(), text);
        return vowelsCount + numberCount - wordEndE;
    }

    private long countPolysyllables() {
        return Arrays.stream(text.split(RegularExpression.WHITESPACE.getPattern()))
                .filter(word -> countSyllables(word) > POLYSYLLABLE_THRESHOLD).count();
    }

    private long countMatching(String pattern, String text) {
        return Pattern.compile(pattern).matcher(text).results().count();
    }

    private long countMatching(String pattern) {
        return countMatching(pattern, text);
    }
}
