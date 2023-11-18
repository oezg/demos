package minesweeper;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int FIELD_WIDTH = 9;
    private static final int FIELD_HEIGHT = 9;
    private static final char MINE = 'X';
    private static final char SAFE_CELL = '.';
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();


    public static void main(String[] args) {
        printField(getMinedField(createField(), getNumberOfMines()));
    }

    /**
     * Read number of mines from user. Repeat until an integer is entered between 0 and number of cells in the field.
     *
     * @return - number of mines
     */
    private static int getNumberOfMines() {
        System.out.print("How many mines do you want on the field? ");

        try {
            int number = scanner.nextInt();
            if (number < 0) {
                throw new IllegalArgumentException("Number of mines cannot be negative.");
            } else if (number > FIELD_WIDTH * FIELD_HEIGHT) {
                throw new IllegalArgumentException("Number of mines cannot exceed the number of cells.");
            } else {
                return number;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getNumberOfMines();
        }
    }

    /**
     * Put given number of mines into the given field.
     *
     * @param field - 2D array of cells
     * @param numberOfMines - user input number of mines
     * @return - the field with mines
     */
    private static char[][] getMinedField(char[][] field, int numberOfMines) {
        for (int i = 0; i < numberOfMines; i++) {
            mineField(field);
        }

        return field;
    }

    /**
     * Mine a random cell in the field recursively
     *
     * @param field - 2D array of cells
     */
    private static void mineField(char[][] field) {
        int i = random.nextInt(FIELD_HEIGHT);
        int j = random.nextInt(FIELD_WIDTH);
        if (field[i][j] == SAFE_CELL) {
            field[i][j] = MINE;
        } else {
            mineField(field);
        }
    }

    /**
     * Create a field with safe cells.
     *
     * @return field - 2D array of cells
     */
    private static char[][] createField() {
        var field = new char[FIELD_HEIGHT][FIELD_WIDTH];

        for (int i = 0; i < FIELD_HEIGHT; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                field[i][j] = SAFE_CELL;
            }
        }

        return field;
    }

    /**
     * Print the field.
     *
     * @param field - 2D array of cells
     */
    private static void printField(char[][] field) {
        for (var row: field) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}
