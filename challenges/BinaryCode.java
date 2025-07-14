/**
 * Implementiert die BinaryCode-Challenge mithilfe des gegebenen Interfaces.
 * Der Spieler muss einen 3-stelligen Dezimalcode erraten, der aus drei zufällig generierten Ziffern besteht.
 * Diese Ziffern werden als 4-Bit-Binärzahlen angezeigt.
 * 
 * @author Adam Tuffaha & Nando Makeem Patton
 */

package challenges;

import java.util.Scanner;

public class BinaryCode implements Challenge {
    
    private final int[] digits = new int[3];
    private final String solution;
    private final int maxAttempts = 3;
    private int attemptsLeft;
    private final int timeLimit = 30000;
    private boolean solved = false;

    /**
     * Konstruktor, generiert den 3-stelligen Lösungscode aus zufälligen Ziffern (0-9).
     */
    public BinaryCode(){
        String solutions = "";
        attemptsLeft = maxAttempts;

        for(int i = 0; i < 3; i++){
            digits[i] = (int)(Math.random() * 10);
            solutions = solutions + digits[i];
        }
        solution = solutions;
    }

     /**
     * Prüft, ob der eingegebene Code korrekt ist und aktualisiert den Status.
     *
     * @param input der eingegebene Code als String
     * @return true, wenn der Code korrekt ist, sonst false
     */
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


    /**
     * Startet die Challenge, zeigt die Binärzahlen an und verarbeitet Eingaben mit Zeitlimit und Versuchen.
     */
    public void start(){
        System.out.println("BinaryCode challenge started!");
        System.out.println("Enter the 3 digit code, calculated from the binary numbers.");

        Scanner scanner= new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            String binary = Integer.toBinaryString(digits[i]);
            while (binary.length() < 4) {    
                binary = "0" + binary;
                
            }
            System.out.println(binary);
        }

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
        return "You'll see a combination of 3 binary numbers (each 4 bits). " +
               "Convert each to decimal and append them together to form a 3-digit code.";
    }

    public String getHint() {
        return "Convert every binary number to a decimal and append them together.";
    }

    public String getName() {
        return "BinaryCode";
    }
}
