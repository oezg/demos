package minesweeper;

import java.util.ArrayList;
import java.util.Random;

final class Field {

    private static final Random RANDOM = new Random();
    private static Field instance;

    private final Cell[][] field;

    final int WIDTH;
    final int HEIGHT;


    private Field() {
        WIDTH = HEIGHT = 9;
        field = new Cell[WIDTH][HEIGHT];
        initializeField();
    }

    public static Field getInstance() {
        if (instance == null) {
            instance = new Field();
        }
        return instance;
    }

    public Cell[][] rows() {
        return field;
    }

    /**
     * Put given number of mines into the given field.
     *
     * @param numberOfMines - user input number of mines
     */
    Coordinate[] mine(int numberOfMines) {
        var coordinates = new Coordinate[numberOfMines];
        for (int i = 0; i < numberOfMines; i++) {
            coordinates[i] = mineRandomCell();
        }
        return coordinates;
    }

    private Coordinate mineRandomCell() {
        int row, col;
        do {
            row = RANDOM.nextInt(HEIGHT);
            col = RANDOM.nextInt(WIDTH);
        } while(field[row][col].isMined());
        field[row][col].mine();
        return new Coordinate(col, row);
    }

    void update() {
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {

                // Do not change mines
                if (field[row][col].isMined()) {
                    continue;
                }

                updateCell(row, col);
            }
        }
    }

    /**
     * Update cell with the count of mines in adjacent cells.
     *
     * @param row - from top
     * @param col - from left
     */
    private void updateCell(int row, int col) {
        Cell cell = field[row][col];

        // Count the mines in the adjacent cells
        int adjacentMines = getAdjacentCells(row, col)
                .stream()
                .mapToInt(c -> c.isMined() ? 1 : 0)
                .sum();

        // Updating the cell with the number of adjacent mines
        cell.setAdjacentMines(adjacentMines);
    }

    /**
     * Find neighbors of each cell.
     *
     * @param row - row number of cell from top
     * @param col - column number of cell from left
     * @return - a list of neighboring chars
     */
    private ArrayList<Cell> getAdjacentCells(int row, int col) {
        var adjacentCells = new ArrayList<Cell>();

        // Find coordinates of the top left corner of the 3x3 neighbors matrix relative to the field
        var top = row - 1;
        var left = col - 1;
        for (int offsetRow = 0; offsetRow < 3; offsetRow++) {
            for (int offsetCol = 0; offsetCol < 3; offsetCol++) {
                if (isAdjacent(offsetRow, offsetCol, top, left)) {

                    // Append the cell if an adjacent cell is found
                    adjacentCells.add(field[offsetRow+top][offsetCol+left]);
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
    private boolean isAdjacent(int offsetRow, int offsetCol, int top, int left) {

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
        if (offsetRow + top >= HEIGHT) {
            return false;
        }

        // Right column is in the field
        if (offsetCol + left >= WIDTH) {
            return false;
        }

        return true;
    }

    public boolean isMarkable(int row, int col) {
        return field[row][col].getAdjacentMines() == 0;
    }

    public void mark(Coordinate coordinate) {
        field[coordinate.getRow()][coordinate.getCol()].mark();
    }


    /**
     * Initialize the field with default cells.
     */
    private void initializeField() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                field[i][j] = new Cell();
            }
        }
    }
}
