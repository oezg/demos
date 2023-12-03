package minesweeper;

public class Main {

    private static final Game game;
    private static final Console console;

    static {
        game = new Game();
        console = new Console();
    }

    public static void main(String[] args) {
        game.begin(console.readNumberOfMines());
        console.printField();
        while (!game.isOver()) {
            game.mark(console.readCoordinates());
            console.printField();
        }
        console.congratulate();
    }
}