public class Game {
    public static boolean firstMove = true;

    public void start() {
        PrintableBoard printableBoard = new PrintableBoard();
        UserInput userInput = new UserInput();
        userInput.setNumberMines();
        BoardStatus boardStatus = new BoardStatus(userInput.numberMines);
        printableBoard.print();

        while (!boardStatus.won(printableBoard.board) && !boardStatus.lost(printableBoard.board)) {
            userInput.setMove();

            if (userInput.freeMove) {
                if (firstMove && boardStatus.board[userInput.y][userInput.x]) {
                    boardStatus.setMines(1);
                    boardStatus.board[userInput.y][userInput.x] = false;
                }
                firstMove = false;

                printableBoard.freeCell(userInput.y, userInput.x, boardStatus.board);
            }
            else
                printableBoard.markCell(userInput.y, userInput.x);
            printableBoard.print();
        }

    }
}
