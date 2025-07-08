package games;

import java.util.Scanner;

public class TicTacToe implements Game {

    private final char[][] board = new char[3][3];
    private final Scanner scanner = new Scanner(System.in);
    private boolean isFinished = false;
    private boolean won = false;
    private boolean lost = false;
    private int currentRound = 0;

    public TicTacToe() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void playNextRound() {
        printBoard();
        System.out.println("Choose your move (row and column: 1 2 for middle row, right column):");
        int row = scanner.nextInt() - 1;
        int col = scanner.nextInt() - 1;
        scanner.nextLine();

        if (isValidMove(row, col)) {
            board[row][col] = 'X';
            if (checkWin('X')) {
                System.out.println("You win!");
                isFinished = true;
                won = true;
                return;
            }
            if (isBoardFull()) {
                System.out.println("It's a tie.");
                isFinished = true;
                return;
            }

            makeEnemyMove();
            if (checkWin('O')) {
                System.out.println("You lose!");
                isFinished = true;
                lost = true;
            } else if (isBoardFull()) {
                System.out.println("It's a tie.");
                isFinished = true;
            }
        } else {
            System.out.println("Invalid move. Try again.");
        }

        currentRound++;
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    private void makeEnemyMove() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'O';
                    return;
                }
            }
        }
    }

    private boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }

    private void printBoard() {
        System.out.println("Board:");
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i < 2) System.out.println("---+---+---");
        }
    }

    public boolean isFinished() {
        return isFinished;
    }

    public boolean isWon() {
        return won;
    }

    public boolean isLost() {
        return lost;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public boolean isTie() {
        return isFinished && !won && !lost;
    }
}    
