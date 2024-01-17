package minesweeper;

import java.util.Scanner;

public class Console {

    private static final Scanner SCANNER = new Scanner(System.in);;
    private static final Field field = Field.getInstance();

    /**
     * Read number of mines from user. Repeat until an integer is entered between 0 and number of cells in the field.
     *
     * @return - number of mines
     */
    static int readNumberOfMines() {
        System.out.print("How many mines do you want on the field? ");
        int number = SCANNER.nextInt();
        if (number < 0 || number > Field.WIDTH * Field.HEIGHT) {
            return readNumberOfMines();
        }
        return number;
    }

    static void printField() {
        printFieldWithMines(false);
    }

    static void printFieldWithMines(boolean withMines) {
        int lineNumber = 1;
        System.out.println();
        System.out.println(" |123456789|");
        System.out.println("-|---------|");
        for (var row : field.rows()) {
            System.out.printf("%d|", lineNumber++);
            for (Cell cell : row) {
                System.out.print(cell.show(withMines));
            }
            System.out.println("|");
        }
        System.out.println("-|---------|");
    }

    static Input readInput() {
        Input input;

        while (true) {
            System.out.print("Set/unset mines marks or claim a cell as free: ");
            int x = SCANNER.nextInt();
            int y = SCANNER.nextInt();
            String word = SCANNER.next();

            input = new Input(x, y, word);
            if (input.isOk()) {
                return input;
            }

            System.out.println(input.message());
        }
    }

    static void endGame(Game.State state) {
        SCANNER.close();

        switch (state) {
            case win -> {
                printField();
                System.out.println("Congratulations! You found all the mines!");
            }
            case loose -> {
                printFieldWithMines(true);
                System.out.println("You stepped on a mine and failed!");
            }
            default -> System.out.println("ERROR: Game ended unexpectedly!");
        }
    }
}
