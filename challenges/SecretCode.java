package challenges;


import java.util.Scanner;

public class SecretCode implements Challenge {
    
    private static final String[] CODEWORDS = {"LAB", "CODE", "JAVA", "ROBOT", "BIRD", "SHUTTLE", "PIZZA"};
    private String encrypted;
    private String decrypted;
    private final int maxAttempts = 3;
    private int attemptsLeft;
    private final int timeLimit = 30000;
    private int shift;
    private boolean solved = false;

    public SecretCode() {
        attemptsLeft = maxAttempts;
        int index = (int)(Math.random() * CODEWORDS.length);
        decrypted = CODEWORDS[index];
        shift = (int)(Math.random() * 25) + 1;
        encrypted = caesarChiffre(decrypted, shift);
    }

    private String caesarChiffre(String input, int shift){
    String result = "";
    
    for (int i = 0; i < input.length(); i++) {
        char normal = input.charAt(i);
        char upper = Character.toUpperCase(normal);

        if (upper >= 'A' && upper <= 'Z') {
            int position = upper - 'A';
            int shifted = (position + shift) % 26;
            char finalChar = (char) ('A' + shifted);
            result += finalChar;
        } else {
            result += upper;
        }
    }
    return result;
    }

    public boolean tryCode(String input) {
        if (solved || attemptsLeft <= 0) {return false;}

        if (input.equalsIgnoreCase(decrypted)) {
            System.out.println("Correct! The door magically opened.");
            solved = true;
            return true;
        } else {
            attemptsLeft--;
            System.out.println("Wrong! Attempts left: " + attemptsLeft);
            return false;
        }
    }

    public void start() {
        System.out.println("SecretCode challenge started!");
        System.out.println("Encrypted word: " + encrypted);
        System.out.println("You have " + maxAttempts + " attempts, 30 seconds each. \n");

        Scanner scanner = new Scanner(System.in);

        while(attemptsLeft > 0 && !solved) {
            System.out.println("Attempt number: " + (maxAttempts - attemptsLeft + 1) + ".");
            long startTime = System.currentTimeMillis();
            String input = scanner.nextLine().toUpperCase();
            long duration = System.currentTimeMillis() - startTime;

            if (duration > timeLimit) {
                System.out.println("Your time's up!");
                break;
            }
            tryCode(input);
        }

        if (!solved) {
            System.out.println("You failed the challenge.");
        }
    }

    public boolean isSolved() {
        return solved;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public String getDescription() {
        return "Solve the Ceaser encryption and guess the correct word.";
    }

    public String getHint() {
        return "Encrypted word: " + encrypted + " | Shifted by: " + shift;
    }

    public String getName() {
        return "SecretCode";
    } 
}
