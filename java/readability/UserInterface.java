package readability;

import java.util.Map;
import java.util.Scanner;

public class UserInterface {

    private static final Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }
    public UserInterface() {

    }

    public void printReadabilityScores(ReadabilityScore[] scores) {
        for (var score : scores) {
            System.out.printf(
                    "%s: %.2f (about %d-year-olds).\n",
                    score.nameIndex(),
                    score.readabilityScore(),
                    score.ageEstimate()
                    );
        }
    }

    public ReadabilityScoreType getReadabilityScoreType() {
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        var x = scanner.nextLine();
        System.out.println();
        try {
            return ReadabilityScoreType.valueOf(x);
        } catch (IllegalArgumentException e) {
            System.out.println("Please enter one of the readability score names!");
            return getReadabilityScoreType();
        }
    }

    public void printText(String text) {
        System.out.println("The text is:");
        System.out.println(text);
        System.out.println();
    }

    public void printReadabilityStatistics(ReadabilityStatistics statistics) {
        for (Map.Entry<String, Long> entry: statistics.getStats().entrySet()) {
            System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
        }
    }
}
