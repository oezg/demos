package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadabilityScoreApp {

    private final UserInterface ui;

    private final ReadabilityController controller;
    static ReadabilityScoreApp app;
    private ReadabilityScoreApp() {
        ui = new UserInterface();
        controller = new ReadabilityController();
    }

    public static ReadabilityScoreApp getApp() {
        if (app == null) {
            app = new ReadabilityScoreApp();
        }
        return app;
    }

    public void run(String filename) {
        String text = getText(filename);
        ui.printText(text);

        var stats = controller.getReadabilityStatistics(text);
        ui.printReadabilityStatistics(stats);

        var scoreType = ui.getReadabilityScoreType();
        var scores = controller.getReadabilityScores(scoreType);
        ui.printReadabilityScores(scores);

        if (scoreType == ReadabilityScoreType.all) {
            ui.printAverageEstimatedAge(scores);
        }
    }

    private String getText(String filename) {
        try {
            return new String(
                    Files.readAllBytes(
                            Paths.get(filename)
                    )
            );
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return "";
    }
}
