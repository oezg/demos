package minesweeper;

class Input {

    private State state;
    private Coordinate coordinate;
    private Command command;
    private String message;
    private Field field;

    Input(int x, int y, String word) {
        field = Field.getInstance();
        state = State.bad;
        if (x < 1 || y < 1) {
            message = "Coordinates must be positive.";
            return;
        } else if (x > Field.WIDTH || y > Field.HEIGHT) {
            message = "Coordinates cannot be bigger than the size of the field.";
            return;
        } else {
            coordinate = new Coordinate(y - 1, x - 1);
        }

        if (!field.getCell(coordinate).isUnexplored()) {
            message = "The cell at the given coordinate is already explored"
        }
        // todo stop mark explored
        // todo stop explore explored

        if ("free".equals(word)) {
            command = Command.explore;
        } else if ("mine".equals(word)) {
            command = Command.mark;
        } else {
            message = "'free' or 'mine' must be given as a command";
            return;
        }

        state = State.ok;
    }

    boolean isMark() {
        return command == Command.mark;
    }

    Coordinate coordinate() {
        return coordinate;
    }

    boolean isOk() {
        return state == State.ok;
    }

    String message() {
        return message;
    }

    enum State {
        ok,
        bad,
    }

    enum Command {
        mark,
        explore
    }
}
