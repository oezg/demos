package readability;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class UserInterface {

    private static final Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }
    public UserInterface() {

    }

    public void printText(String text) {
        System.out.println("The text is:");
        System.out.println(text);
    }

    public void printReadabilityStatistics(ReadabilityStatistics statistics) {
        for (Map.Entry<String, Long> entry: statistics.getStats().entrySet()) {
            System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
        }
    }

    public ReadabilityScoreType getReadabilityScoreType() {
        System.out.print("\nEnter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        var x = scanner.nextLine();
        System.out.println();
        try {
            return ReadabilityScoreType.valueOf(x);
        } catch (IllegalArgumentException e) {
            System.out.println("Please enter one of the readability score names!");
            return getReadabilityScoreType();
        }
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

    public void printAverageEstimatedAge(ReadabilityScore[] scores) {
        var averageAge = Arrays.stream(scores).mapToDouble(ReadabilityScore::ageEstimate).average().orElse(0);
        System.out.printf("\nThis text should be understood in average by %.2f-year-olds.\n", averageAge);
    }
}
