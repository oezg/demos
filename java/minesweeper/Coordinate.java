package minesweeper;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

record Coordinate(int row, int col) {
    private static final Random RANDOM = new Random();
    static Coordinate createRandom() {
        return new Coordinate(RANDOM.nextInt(Field.HEIGHT), RANDOM.nextInt(Field.WIDTH));
    }
    List<Coordinate> neighbors(Field field) {
        Coordinate[] neighbors = {
                new Coordinate(-1, -1),
                new Coordinate(-1, 0),
                new Coordinate(-1, 1),
                new Coordinate(0, -1),
                new Coordinate(0, 1),
                new Coordinate(1, -1),
                new Coordinate(1, 0),
                new Coordinate(1, 1),
        };
        return Arrays.stream(neighbors).map(this::add).filter(Coordinate::isValid).toList();
    }

    Coordinate add(Coordinate other) {
        return new Coordinate(row() + other.row(), col() + other.col());
    }

    boolean isValid() {
        return row() < Field.HEIGHT && row() >= 0 && col() < Field.WIDTH && col() >= 0;
    }
}
