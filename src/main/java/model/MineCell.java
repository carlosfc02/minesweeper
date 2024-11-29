package model;

public final class MineCell implements Cell {
    private final Boolean revealed;

    public MineCell(boolean revealed) {
        this.revealed = revealed;
    }

    public static MineCell createMineCell() {
        return new MineCell(false);
    }
    @Override
    public Cell reveal() {
        return new MineCell(true);
    }

    @Override
    public boolean isRevealed() {
        return revealed;
    }

    @Override
    public boolean isMine() {
        return true;
    }
}
