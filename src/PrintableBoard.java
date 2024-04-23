public class PrintableBoard {
    private static final int BOARD_SIZE = 9;
    private static final int EMPTY_CELL = '/';
    private static final int MINE_CELL = 'X';
    private static final int MARKED_CELL = '*';
    private static final int UNEXPLORED_CELL = '.';
    private static final boolean[][] revealed = new boolean[BOARD_SIZE][BOARD_SIZE];
    public char[][] board;

    public void markCell(int y, int x) {
        if (board[y][x] == MARKED_CELL)
            board[y][x] = UNEXPLORED_CELL;
        else if (board[y][x] == UNEXPLORED_CELL)
            board[y][x] = MARKED_CELL;
    }

    public PrintableBoard() {
        board = new char[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) board[i][j] = UNEXPLORED_CELL;
        }
    }

    private int countMinesAround(int y, int x, boolean[][] boardState) {
        int minesAround = 0;
        for (int i = Math.max(0, y-1); i <= Math.min(BOARD_SIZE-1, y+1); i++) {
            for (int j = Math.max(0, x-1); j <= Math.min(BOARD_SIZE-1, x+1); j++) {
                if (boardState[i][j]) minesAround++;
            }
        }
        return minesAround;
    }


    public void freeCell(int y, int x, boolean[][] boardStatus) {
        if (revealed[y][x])
            return;

        revealed[y][x] = true;

        if (boardStatus[y][x]) {
            board[y][x] = MINE_CELL;
            return;
        }

        int numMines = countMinesAround(y, x, boardStatus);

        if (numMines > 0) {
            board[y][x] = (char) ('0' + numMines);
            return;
        }

        board[y][x] = EMPTY_CELL;

        for (int i = Math.max(0, y-1); i <= Math.min(BOARD_SIZE-1, y+1); i++)
            for (int j = Math.max(0, x-1); j <= Math.min(BOARD_SIZE-1, x+1); j++)
                freeCell(i, j, boardStatus);
    }

    public void print() {
        System.out.println(" |123456789|\n" + "-|---------|");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print((char) ('1' + i));
            System.out.print('|');
            for (int j = 0; j < BOARD_SIZE; j++)
                System.out.print(board[i][j]);
            System.out.println('|');
        }
        System.out.println("-|---------|");
    }
}
