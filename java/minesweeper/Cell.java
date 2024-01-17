package minesweeper;

import java.util.List;

class Cell {

    private Mine mine;
    private int cardinality;

    private State state;

    private final Coordinate coordinate;
    private final Field field;

    Cell(Field field, Coordinate coordinate) {
        this.field = field;
        this.coordinate = coordinate;
        mine = null;
        setState(State.unmarked);
    }

    State getState() {
        return state;
    }

    void setState(State state) {
        this.state = state;
    }

    int getCardinality() {
        return cardinality;
    }

    void setCardinality(int cardinality) {
        this.cardinality = cardinality;
    }

    boolean hasMinesAround() {
        return getCardinality() > 0;
    }

    boolean isMined() {
        return mine != null;
    }

    Mine mine() {
        mine = new Mine();
        return mine;
    }

    String show(boolean withMine) {
        return switch (getState()) {
            case explored -> hasMinesAround() ? Integer.toString(getCardinality()) : "/";
            case marked -> withMine && isMined() ? "X" : "*";
            case unmarked -> withMine && isMined() ? "X" : ".";
        };
    }

    void mark() {
        switch (getState()) {
            case marked -> {
                setState(State.unmarked);
                if (mine != null) {
                    mine.setMarked(false);
                }
            }
            case unmarked -> {
                setState(State.marked);
                if (mine != null) {
                    mine.setMarked(true);
                }
            }
            case explored -> throw new IllegalArgumentException("An explored cell cannot be marked!");
        }
    }

    Mine explore() {
        if (isUnexplored() && !isMined()) {
            setState(State.explored);
            var neighbors = neighbors();
            setCardinality((int)neighbors.stream().filter(Cell::isMined).count());
            if (!hasMinesAround()) {
                neighbors.forEach(Cell::explore);
            }
        }
        return mine;
    }

    boolean isUnexplored() {
        return getState() != State.explored;
    }

    List<Cell> neighbors() {
        return coordinate.neighbors(field).stream().map(field::getCell).toList();
    }

    enum State {
        explored,
        marked,
        unmarked
    }
}
