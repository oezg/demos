package minesweeper;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int FIELD_WIDTH = 9;
    private static final int FIELD_HEIGHT = 9;
    private static final char MINE = 'X';
    private static final char SAFE_CELL = '.';
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();
    private static final char[][] FIELD = new char[FIELD_HEIGHT][FIELD_WIDTH];


    public static void main(String[] args) {
        initializeField();
        mineField(getNumberOfMines());
        changeEmptyCellsToCountOfAdjacentMines();
        printField();
    }

    /**
     * Read number of mines from user. Repeat until an integer is entered between 0 and number of cells in the field.
     *
     * @return - number of mines
     */
    private static int getNumberOfMines() {
        System.out.print("How many mines do you want on the field? ");

        try {
            int number = SCANNER.nextInt();
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
     * @param numberOfMines - user input number of mines
     */
    private static void mineField(int numberOfMines) {
        for (int i = 0; i < numberOfMines; i++) {
            mineRandomCell();
        }
    }

    /**
     * Mine a random cell in the field recursively
     */
    private static void mineRandomCell() {
        int i = RANDOM.nextInt(FIELD_HEIGHT);
        int j = RANDOM.nextInt(FIELD_WIDTH);
        if (FIELD[i][j] == SAFE_CELL) {
            FIELD[i][j] = MINE;
        } else {
            mineRandomCell();
        }
    }

    /**
     * Initialize the field with safe cells.
     */
    private static void initializeField() {
        for (int i = 0; i < FIELD_HEIGHT; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                FIELD[i][j] = SAFE_CELL;
            }
        }
    }

    /**
     * Print the field.
     */
    private static void printField() {
        for (var row: FIELD) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    /**
     * Update each cell with the count of mines in adjacent cells.
     */
    private static void changeEmptyCellsToCountOfAdjacentMines() {
        for (int row = 0; row < FIELD_HEIGHT; row++) {
            for (int col = 0; col < FIELD_WIDTH; col++) {

                // Do not change mines
                if (FIELD[row][col] == 'X') {
                    continue;
                }

                updateCellWithCountOfAdjacentMines(row, col);
            }
        }
    }

    /**
     * Update cell with the count of mines in adjacent cells.
     *
     * @param row - from top
     * @param col - from left
     */
    private static void updateCellWithCountOfAdjacentMines(int row, int col) {
        // Count the mines in the adjacent cells
        int adjacentMines = getAdjacentCells(row, col).stream().mapToInt(x -> x == 'X' ? 1 : 0).sum();
        if (adjacentMines > 0) {

            // Updating the cell with the number of adjacent mines
            FIELD[row][col] = (char)('0' + adjacentMines);
        }
    }

    /**
     * Find neighbors of each cell.
     *
     * @param row - row number of cell from top
     * @param col - column number of cell from left
     * @return - a list of neighboring chars
     */
    private static ArrayList<Character> getAdjacentCells(int row, int col) {
        var adjacentCells = new ArrayList<Character>();

        // Find coordinates of the top left corner of the 3x3 neighbors matrix relative to the field
        var top = row - 1;
        var left = col - 1;
        for (int offsetRow = 0; offsetRow < 3; offsetRow++) {
            for (int offsetCol = 0; offsetCol < 3; offsetCol++) {
                if (isAdjacent(offsetRow, offsetCol, top, left)) {

                    // Append the cell if an adjacent cell is found
                    adjacentCells.add(FIELD[offsetRow+top][offsetCol+left]);
                }
            }
        }

        return adjacentCells;
    }

    /**
     * Check if the cell is different from the current cell, and it is within the field.
     *
     * @param offsetRow - offset from top of the 3x3 neighbors matrix.
     * @param offsetCol - offset from left of the 3x3 neighbors matrix.
     * @param top - top of the 3x3 neighbors matrix.
     * @param left - left of the 3x3 neighbors matrix.
     * @return - true if the cell is a neighbor.
     */
    private static boolean isAdjacent(int offsetRow, int offsetCol, int top, int left) {

        // Cell is not a neighbor of its own
        if (offsetRow == 1 && offsetCol == 1) {
            return false;
        }

        // Top row is in the field
        if (offsetRow + top < 0) {
            return false;
        }

        // Left column is in the field
        if (offsetCol + left < 0) {
            return false;
        }

        // Bottom row is in the field
        if (offsetRow + top >= FIELD_HEIGHT) {
            return false;
        }

        // Right column is in the field
        if (offsetCol + left >= FIELD_WIDTH) {
            return false;
        }

        return true;
    }
}
