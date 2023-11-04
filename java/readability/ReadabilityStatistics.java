package readability;

import java.util.Map;

import static java.util.Map.entry;

public record ReadabilityStatistics(long wordCount, long sentenceCount, long characterCount, long syllableCount, long polysyllableCount) {

    public Map<String, Long> getStats() {
        return Map.ofEntries(
                entry("Words", wordCount),
                entry("Sentences", sentenceCount),
                entry("Characters", characterCount),
                entry("Syllables", syllableCount),
                entry("Polysyllables", polysyllableCount)
        );
    }

}
