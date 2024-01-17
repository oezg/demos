package minesweeper;

public class Main {

    private static final Game game = new Game();;

    public static void main(String[] args) {
        game.initialize(Console.readNumberOfMines());
        while (game.isNotOver()) {
            Console.printField();
            game.handle(Console.readInput());
        }
        Console.endGame(game.getState());
    }
}