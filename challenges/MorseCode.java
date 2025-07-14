package challenges;


import java.util.Scanner;

public class MorseCode implements Challenge {
    private final String[] words = { "LAB", "CODE", "JAVA", "ROBOT", "BIRD", "SHUTTLE", "PIZZA" };
    private final String[] morseCodes = {
        " .-.. .- -...",
        " -.-. --- -.. .", 
        " .--- .- ...- .-",
        " .-. --- -... --- -", 
        "... .. .-. -..", 
        " ... .... ..- - - .-.. .",
        " .--. .. --.. --.. .-"
    };

    private final String solution;
    private final int maxAttempts = 3;
    private int attemptsLeft;
    private final int timeLimit = 30000;
    private boolean solved = false;
    private final String morse;
    
    public MorseCode() {
        int index = (int)(Math.random() * words.length);
        solution = words[index];
        morse = morseCodes[index];
        attemptsLeft = maxAttempts;
    }

    public boolean tryCode(String input) {
        if (solved || attemptsLeft <= 0) return false;

        if (input.equalsIgnoreCase(solution)) {
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
        System.out.println("MorseCode challenge started!");
        System.out.println("Decrypt code: " + morse);
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
        return "Decrypt the following morsecode: " + morse;
    }

    public String getHint() {
        return "Every letter consists of periods (.) and hyphens (-). For example: A = .-";
    }

    public String getName() {
        return "MorseCode";
    }
} 
