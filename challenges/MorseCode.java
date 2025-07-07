package challenges;


import java.util.Scanner

public class MorseCode {
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



    public boolean Start() {
        System.out.println("=====titel====");
        System.out.println("========");
        System.out.println("=====titel====");
        System.out.println("du hast 40 sec zet");


        Scanner scanner = new Scanner(System.in);
        while(attemptsLeft >0 ){
            System.out.println("eigabe nr");

            long startTime=System.currentTimeMillis();
            String input = scanner.nextLine().toUpperCase();
            long duration = System.currentTimeMillis() -startTime;

            if (duration>timeLimit){
                System.out.println("zeit");
                return false;
            }

            if(input.equals(solution)){
                System.err.println("Richtigtür aufg");
                solved = true;
                return true;
            }else{
                System.out.println("wroooont");
                attemptsLeft--;
            }
            

        }
        System.out.println("alleversauche weg");
        return false;
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
        return "Every  letter consists of periods (.) and hyphens (-). For example: A = .-";
    }

    public String getName() {
        return "MorseCode";
    }
} 
