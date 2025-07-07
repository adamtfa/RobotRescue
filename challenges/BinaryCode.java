package challenges;

import java.util.Scanner;

public class BinaryCode {
    private final int[] digits = new int[3];
    private final String solution;
    private final int maxAttempts = 3;
    private int attemptsLeft;
    private final int timeLimit = 30000;
    private boolean solved = false;

    public BinaryCode(){
        String solutions="";
        attemptsLeft = maxAttempts;

        for(int i = 0; i<3;i++){
            digits[i]=(int)(Math.random()*10);
            solutions= solutions+digits[i];
        }

        solution= solutions;
    }

    public boolean start(){
        Scanner scanner= new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            String binary = Integer.toBinaryString(digits[i]);

            while (binary.length()<4) {
                
                binary = "0" + binary;
                
            }
            System.out.println(binary);

        }

        while (
            attemptsLeft > 0
        ) { 
            long startTime = System.currentTimeMillis();
            String input = scanner.nextLine().toUpperCase();
            long endTime = System.currentTimeMillis();

            long durationTime = endTime - startTime;

            if(durationTime > timeLimit*1000){
                System.out.println("zeit aus");
                return false;
            }

            if(input.equals(solution)){
                System.err.println("Richtig");
                solved = true;
                return true;
            }else{
                System.out.println("wroooont");
                attemptsLeft--;
            }
            
        }
        return false;

    }
    
}
