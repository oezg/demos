package minesweeper;

public class Cell {

    private boolean marked;
    private boolean mined;
    private int adjacentMines;

    {
        marked = false;
        mined = false;
        adjacentMines = 0;
    }

    public boolean isMarked() {
        return marked;
    }

    public boolean isMined() {
        return mined;
    }

    public void mine() {
        this.mined = true;
    }

    public int getAdjacentMines() {
        return adjacentMines;
    }

    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }

    @Override
    public String toString() {
        if (isMarked()) {
            return "*";
        } else if (!isMined() && getAdjacentMines() > 0) {
            return String.valueOf(getAdjacentMines());
        } else {
            return ".";
        }
    }

    public void mark() {
        marked = !marked;
    }
}
