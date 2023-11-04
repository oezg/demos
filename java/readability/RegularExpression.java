package readability;

public enum RegularExpression {
    CHARACTER("\\S"),
    NUMBER("\\d+([.,]\\d*)?"),
    SENTENCE("[^.!?]+"),
    VOWEL("[aeiouyAEIOUY]+"),
    WHITESPACE("\\s+"),
    WORD("\\w+"),
    WORD_END_E("[^aeiouyAEIOUY]+e\\b")
    ;

    RegularExpression(String pattern) {
        this.pattern = pattern;
    }

    private final String pattern;

    public String getPattern() {
        return this.pattern;
    }
}
