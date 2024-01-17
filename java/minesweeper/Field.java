package minesweeper;

import java.util.Arrays;
import java.util.Random;

class Field {
    private static Field instance;
    static final int WIDTH;
    static final int HEIGHT;
    private int numberOfMines;
    private final Cell[][] field;
    private Mine[] mines;

    static {
        WIDTH = 9;
        HEIGHT = 9;
    }
    private Field() {
        field = new Cell[WIDTH][HEIGHT];
        initializeField();
    }

    public static Field getInstance() {
        if (instance == null) {
            instance = new Field();
        }
        return instance;
    }

    public int getNumberOfMines() {
        return numberOfMines;
    }

    public void setNumberOfMines(int numberOfMines) {
        this.numberOfMines = numberOfMines;
        mines = new Mine[numberOfMines];
    }

    public Cell[][] rows() {
        return field;
    }

    /**
     * Put given number of mines into the given field.
     *
     * @param numberOfMines - user input number of mines
     */
    void setMines(int numberOfMines) {
        setNumberOfMines(numberOfMines);
        for (int i = 0; i < numberOfMines; i++) {
            mines[i] = mineRandomCell();
        }
    }

    private Mine mineRandomCell() {
        Cell cell;
        do {
            cell = getCell(Coordinate.createRandom());
        } while (cell.isMined());
        return cell.mine();
    }

    /**
     * Initialize the field with default cells.
     */
    private void initializeField() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                field[i][j] = new Cell(this, new Coordinate(i, j));
            }
        }
    }

    Cell getCell(Coordinate coordinate) {
        return field[coordinate.row()][coordinate.col()];
    }

    public boolean areAllMinesMarked() {
        return Arrays.stream(mines).allMatch(Mine::isMarked);
    }

    public boolean areAllSafeCellsExplored() {
        return getNumberOfMines() == numberOfUnexploredCells();
    }

    private int numberOfUnexploredCells() {
        return (int)Arrays.stream(field).flatMap(Arrays::stream).filter(Cell::isUnexplored).count();
    }
}
