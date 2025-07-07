package challenges;


import java.util.Scanner;

public class secretCode {
    
    private static final String[] CODEWORDS = {"LAB", "CODE", "JAVA", "ROBOT", "BIRD", "SHUTTLE", "PIZZA"};
    private String encrypted;
    private String decrypted;
    private final int maxAttempts = 3;
    private int attemptsLeft;
    private final int timeLimit = 30000;
    private int shift;
    private boolean solved = false;

    public secretCode(){
        attemptsLeft = maxAttempts;
        int index = (int)(Math.random() * CODEWORDS.length);
        decrypted = CODEWORDS[index];
        shift = (int)(Math.random() * 25) + 1;
        encrypted = caesarChiffre(decrypted, shift);
    }

    private String caesarChiffre(String input, int shift){
        String result = " ";
        
        for(int i = 0; i< input.length();i++){

            char normal = input.charAt(i);
            char upper = Character.toUpperCase(normal);
            
            if (upper >= 'A' && upper <= 'Z'){
                int position = upper - 'A';
                int shifted = position + shift;
                if(shifted>=26){
                    shifted= shifted-26;
                }
                char finalChar = (char)('A'+ shifted);
                return result + finalChar;
            }else{
                result = result+upper;
            }
        }
        return result;
    }

    public boolean start(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("");

        while(attemptsLeft > 0){
            long startTime = System.currentTimeMillis();
            String input = scanner.nextLine().toUpperCase();
            long endTime = System.currentTimeMillis();

            long durationTime = endTime - startTime;

            if(durationTime > timeLimit*1000){
                System.out.println("zeit aus");
                return false;
            }

            if(input.equals(decrypted)){
                System.err.println("Richtig");
                solved = true;
                return true;
            }else{
                System.out.println("wroooont");
                attemptsLeft--;
            }
        }
        System.out.println("alle versuche weg");
        return false;
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
