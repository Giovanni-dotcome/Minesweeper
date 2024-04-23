import java.util.Random;

public class BoardStatus {
    private static final int BOARD_SIZE = 9;
    private static final int MINE_CELL = 'X';
    public boolean[][] board;

    public void setMines(int numberMines) {
        Random random = new Random();

        while (numberMines > 0) {
            int randomY = random.nextInt(BOARD_SIZE);
            int randomX = random.nextInt(BOARD_SIZE);

            if (!board[randomY][randomX]) {
                board[randomY][randomX] = true;
                numberMines--;
            }
        }
    }

    public boolean lost(char[][] printableBoard) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (printableBoard[i][j] == MINE_CELL && board[i][j]) {
                    System.out.println("You stepped on a mine and failed!\n");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean onlyMinesUnexplored(char[][] printableBoard) {
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                if (!board[i][j] && printableBoard[i][j] == '.')
                    return false;
        return true;
    }

    private boolean allMinesMarked(char[][] printableBoard) {
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                if (board[i][j] && printableBoard[i][j] != '*')
                    return false;
        return true;
    }

    public boolean won(char[][] printableBoard) {
        if (onlyMinesUnexplored(printableBoard) || allMinesMarked(printableBoard)) {
            System.out.println("Congratulations! You found all the mines!\n");
            return true;
        }
        return false;
    }

    public BoardStatus(int numberMines) {
        board = new boolean[BOARD_SIZE][BOARD_SIZE];
        setMines(numberMines);
    }
}
