package minesweeper;

import java.util.Scanner;

public class Console {

    private final Field field;
    private final Scanner SCANNER;

    {
        SCANNER = new Scanner(System.in);
        field = Field.getInstance();
    }

    /**
     * Read number of mines from user. Repeat until an integer is entered between 0 and number of cells in the field.
     *
     * @return - number of mines
     */
    int readNumberOfMines() {
        int number;
        do {
            System.out.print("How many mines do you want on the field? ");
            number = SCANNER.nextInt();
        } while (number < 0 || number > field.WIDTH * field.HEIGHT);
        return number;
    }

    void printField() {
        int lineNumber = 1;
        System.out.println(" |123456789|");
        System.out.println("-|---------|");
        for (var row: field.rows()) {
            System.out.printf("%d|", lineNumber++);
            for (Cell cell : row) {
                System.out.print(cell);
            }
            System.out.println("|");
        }
        System.out.println("-|---------|");
    }

    public Coordinate readCoordinates() {
        while (true) {
            System.out.print("Set/delete mines marks (x and y coordinates): ");
            int x = SCANNER.nextInt();
            int y = SCANNER.nextInt();
            if (x < 1 || y < 1) {
                System.out.println("Coordinates must be positive.");
            } else if (x > field.WIDTH || y > field.HEIGHT) {
                System.out.println("Coordinates cannot be bigger than the size of the field.");
            } else if (!field.isMarkable(y - 1, x - 1)) {
                System.out.println("There is a number here!");
            } else {
                return new Coordinate(x - 1, y - 1);
            }
        }
    }

    public void congratulate() {
        System.out.println("Congratulations! You found all the mines!");
    }
}
