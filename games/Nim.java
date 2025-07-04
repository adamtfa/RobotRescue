package games;

import java.util.Random;
import java.util.Scanner;

public class Nim implements Game {

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private int[] heaps = {3, 4, 5};
    private boolean isFinished = false;
    private boolean won = false;
    private boolean lost = false;
    private int currentRound = 0;

    public void playNextRound() {
        printHeaps();
        System.out.println("Choose a heap (1-3) and how many stones to remove:");

        int heap = scanner.nextInt() - 1;
        int amount = scanner.nextInt();
        scanner.nextLine(); // flush

        if (isValidMove(heap, amount)) {
            heaps[heap] -= amount;
            if (isGameOver()) {
                System.out.println("You removed the last stone. You win!");
                isFinished = true;
                won = true;
                return;
            }
        } else {
            System.out.println("Invalid move. Try again.");
            return;
        }

        enemyMove();
        if (isGameOver()) {
            System.out.println("The enemy took the last stone. You lose!");
            isFinished = true;
            lost = true;
        }

        currentRound++;
    }

    private void enemyMove() {
        System.out.println("Enemy's turn:");
        while (true) {
            int heap = random.nextInt(3);
            if (heaps[heap] > 0) {
                int amount = random.nextInt(heaps[heap]) + 1;
                heaps[heap] -= amount;
                System.out.println("Enemy removed " + amount + " from heap " + (heap + 1));
                break;
            }
        }
    }

    private boolean isValidMove(int heap, int amount) {
        return heap >= 0 && heap < heaps.length && amount > 0 && amount <= heaps[heap];
    }

    private boolean isGameOver() {
        for (int heap : heaps) {
            if (heap > 0) return false;
        }
        return true;
    }

    private void printHeaps() {
        System.out.println("Current heaps:");
        for (int i = 0; i < heaps.length; i++) {
            System.out.print("Heap " + (i + 1) + ": ");
            for (int j = 0; j < heaps[i]; j++) {
                System.out.print("*");
            }
            System.out.println(" (" + heaps[i] + ")");
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
