package model;

public interface Cell {
    Cell reveal();
    boolean isRevealed();
    boolean isMine();
}
