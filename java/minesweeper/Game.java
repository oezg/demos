package minesweeper;

import java.util.*;

public class Game {

    private final Field field = Field.getInstance();
    private Coordinate[] mineCoordinates;
    private final Set<Coordinate> markedCoordinates;

    public Game() {
        markedCoordinates = new HashSet<>();
    }

    public void begin(int numberOfMines) {
        mineCoordinates = field.mine(numberOfMines);
        field.update();
    }


    public void mark(Coordinate coordinate) {
        field.mark(coordinate);
        if (!markedCoordinates.add(coordinate)) {
            markedCoordinates.remove(coordinate);
        }
    }

    public boolean isOver() {
        if (mineCoordinates.length != markedCoordinates.size()) {
            return false;
        }

        return Arrays.stream(mineCoordinates)
                .allMatch(markedCoordinates::contains);
    }
}
