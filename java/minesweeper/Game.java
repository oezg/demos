package minesweeper;

public class Game {

    final Field field;

    private State state;

    State getState() {
        return state;
    }

    void setState(State state) {
        this.state = state;
    }

    public Game() {
        state = State.unchanged;
        field = Field.getInstance();
    }

    void initialize(int numberOfMines) {
        field.setMines(numberOfMines);
    }

    void handle(Input input) {
        Cell cell = field.getCell(input.coordinate());
        if (input.isMark()) {
            cell.mark();
            if (field.areAllMinesMarked()) {
                setState(State.win);
            }
        } else {
            if (cell.explore() != null) {
                setState(State.loose);
            }
            if (field.areAllSafeCellsExplored()) {
                setState(State.win);
            }
        }
    }

    public boolean isNotOver() {
        return getState() == State.unchanged;
    }

    enum State {
        win,
        loose,
        unchanged
    }
}
