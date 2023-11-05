package readability;

import java.util.LinkedHashMap;
import java.util.Map;

public record ReadabilityStatistics(long wordCount, long sentenceCount, long characterCount, long syllableCount, long polysyllableCount) {

    public Map<String, Long> getStats() {
        return new LinkedHashMap<>() {{
            put("Words", wordCount);
            put("Sentences", sentenceCount);
            put("Characters", characterCount);
            put("Syllables", syllableCount);
            put("Polysyllables", polysyllableCount);
        }};
    }
}
