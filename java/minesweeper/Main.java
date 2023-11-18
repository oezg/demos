package minesweeper;

public class Main {

    private static final int FIELD_WIDTH = 9;
    private static final int FIELD_HEIGHT = 9;

    private static final int NUMBER_OF_MINES = 10;

    public static void main(String[] args) {
        printField(getField());
    }

    private static char[][] getField() {
        var field = new char[FIELD_HEIGHT][FIELD_WIDTH];
        var fieldsToSkip = FIELD_HEIGHT * FIELD_WIDTH / NUMBER_OF_MINES;
        var counter = fieldsToSkip / 2;

        for (int i = 0; i < FIELD_HEIGHT; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (counter < fieldsToSkip) {
                    field[i][j] = '.';
                    counter++;
                } else {
                    field[i][j] = 'X';
                    counter = 1;
                }
            }
        }

        return field;
    }

    private static void printField(char[][] field) {
        for (var row: field) {
            for (char cell :
                    row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}
