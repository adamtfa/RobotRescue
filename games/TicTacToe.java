package games;

public class TicTacToe implements Game {

    private final char[][] board = new char[3][3];
    private boolean finished = false;
    private boolean won = false;
    private boolean lost = false;
    private boolean tie = false;
    private int currentRound = 0;
    private int currentPlayer = 1;

    public TicTacToe(){
        for (int i=0; i<3 ;i++){
            for (int j = 0; j<3; j++){
                board[i][j] = ' ';
            }
        }
    }

    private void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = '-';
                if (board[i][j] == 1) {
                    c = 'X';
                } else if (board[i][j] == 2) {
                    c = 'O';
                }
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public void playNextRound() {
        System.out.println("Round: " + currentRound);
        printBoard();
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isWon() {
        return won;
    }

    public boolean isLost() {
        return lost;
    }

    public boolean isTie() {
        return tie;
    }
}
