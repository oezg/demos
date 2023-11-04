package readability;

public class Main {
    public static void main(String[] args) {
        var app = ReadabilityScoreApp.getApp();
        app.run(args[0]);
    }
}
