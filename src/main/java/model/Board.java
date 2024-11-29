package model;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public final class Board {
    private final List<List<Cell>> grid;

    public Board(int rows, int columns, int mines) {
        this.grid = generateBoard(rows,columns,mines);
    }

    public Board(List<List<Cell>> grid) {
        this.grid = grid;
    }

    private List<List<Cell>> generateBoard(int rows, int columns, int mines) {
        Cell[][] tempGrid = new Cell[rows][columns];
        placeMines(rows, columns, mines, tempGrid);
        placeEmptyCells(rows, columns, tempGrid);

        return getUnmodifiabledList(tempGrid);
    }

    public Board revealCell(int row, int column){
        List<List<Cell>> newGrid = grid.stream()
                .map(originalRow -> originalRow.stream()
                    .map(cell -> isRevealed(row, column, cell))
                    .toList())
                .toList();

        return new Board(newGrid);
    }

    public void printBoard() {
        for (List<Cell> row : grid){
            for (Cell cell : row) {
                System.out.print(cell.isRevealed() ? (cell.isMine() ? "M " : "R "): "X ");
            }
            System.out.println();
        }
    }

    private Cell isRevealed(int row, int column, Cell cell) {
        return cell == grid.get(row).get(column) ? cell.reveal() : cell;
    }

    private static List<List<Cell>> getUnmodifiabledList(Cell[][] tempGrid) {
        return Collections.unmodifiableList(
                Stream.of(tempGrid)
                        .map(row -> Collections.unmodifiableList(List.of(row)))
                        .toList()
        );
    }

    private void placeEmptyCells(int rows, int columns, Cell[][] tempGrid) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (gridHasPlacement(i, j, tempGrid)){
                    tempGrid[i][j] = EmptyCell.createEmptyCell();
                }
            }
        }
    }

    private void placeMines(int rows, int columns, int mines, Cell[][] tempGrid) {
        int placedMines = 0;
        Random random = new Random();
        while (placedMines < mines){
            int [] placement = randomizePlacement(rows, columns, random);
            int row = placement[0];
            int column = placement[1];
            if (gridHasPlacement(row, column, tempGrid)){
                tempGrid[row][column] = MineCell.createMineCell();
                placedMines++;
            }
        }
    }

    private Boolean gridHasPlacement(int rows, int columns, Cell[][] tempGrid){
        return tempGrid[rows][columns] == null;
    }

    private static int[] randomizePlacement(int rows, int columns, Random random) {
        rows = random.nextInt(rows);
        columns = random.nextInt(columns);
        return new int[]{rows,columns};
    }
}
