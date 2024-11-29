package model;

public final class EmptyCell implements Cell {


    private final boolean revealed;

    public EmptyCell(boolean revealed) {
        this.revealed = revealed;
    }

    public static EmptyCell createEmptyCell() {
        return new EmptyCell(false);
    }

    @Override
    public Cell reveal() {
        return new EmptyCell(true);
    }

    @Override
    public boolean isRevealed() {
        return revealed;
    }

    @Override
    public boolean isMine() {
        return false;
    }
}
