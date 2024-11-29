import model.Board;

import java.util.Scanner;

public class MockMineSweeperGame {
    private final Board board;

    public MockMineSweeperGame(int rows, int columns, int mines) {
        this.board = new Board(rows, columns, mines);
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        Board currentBoard = board;

        while (true){
            currentBoard.printBoard();
            System.out.println("Enter row and column to reveal: ");
            int row = scanner.nextInt();
            int column = scanner.nextInt();

            try {
                currentBoard = currentBoard.revealCell(row, column);
            } catch (Exception e) {
                System.out.println("Invalid move. Try again");;
            }
        }
    }
}
