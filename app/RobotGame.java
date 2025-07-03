/**
* Klasse für den Spielablauf.
* @author Adam Tuffaha & Nando Makeem Patton
*/

package app;

import java.util.Random;
import java.util.Scanner;

import games.TicTacToe;
import games.Game;
import model.Robot;
import model.Room;
import model.Shuttle;
import model.Enemy;
import model.SpaceCreeper;
import model.Artifact;
import model.ElderGuardian;

public class RobotGame {
    private final Robot robot;
    private final Room[] rooms = new Room[3];
    private final Shuttle shuttle;
    private boolean gameRunning = true;
    private boolean gameFinished = false;

    /**
     * Initialisiert einen neuen Roboter und ein Shuttle.
     */
    public RobotGame(String name) {
        this.robot = new Robot(name);
        this.shuttle = new Shuttle("name");
    }

    /**
     * Gibt zurück, ob das Spiel läuft.
     * @return true
     */
    public boolean isGameRunning() {
        return gameRunning;
    }

    /**
     * Setzt den Status des Spiels auf "laufend".
     * @param gameRunning
     */
    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    /**
     * Gibt den Status des aktuellen Spiels zurück.
     * @return true, wenn das Spiel beendet ist 
     */
    public boolean isGameFinished() {
        return gameFinished;
    }

    /**
     * Setzt den Spielstand auf "abgeschlossen".
     * @param gameFinished
     */
    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    /**
     * Gibt auf der Konsole aus, dass bzw. ob das Spiel gestartet ist.
     */
    public void run() {
        System.out.println("========================================\n");
        System.out.println("The game has started. Or not?");
        
        if(isGameRunning() == true) {
            System.out.println("Your robot's name is: " + robot.getName());
            System.out.println("(1) Explore station");
            System.out.println("(2) Show status");
            System.out.println("(3) Recharge energy");
            System.out.println("(4) Repair");
            System.out.println("(5) Exit to main menu");
            System.out.println("\n========================================");
            System.out.println("Please choose a number between 1 and 5: ");
        }

        while(true) {
            String choice = readUserInput();
            handleUserInput(choice);
        }
    }

    private String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        // TBD
        return userInput;
    }

    private void handleUserInput(String input) {
        switch (input) {
            case "1":
                this.exploreStation();
                break;
            case "2":
                this.showStatus();
                break;
            case "3":
                robot.recharge(10);
                System.out.print("Your current energy is: " + robot.getEnergy());
                break;
            case "4":
                this.repairMenu();
                break;
            case "5":
                //TODO: Zurück zum Hauptmenü gehen!
                break;
            default:
                System.out.println("Invalid input. Please choose a correct number between 1 and 5.");
                break;
        }
    }

    /**
     * Gibt den erstellten Roboter zurück.
     * @return der erstellte Roboter
     */
    public Robot getRobot() {
        return robot;
    }

    public void exploreStation() {
        double chance = Math.random();
        
        if(chance < 0.5) {
            System.out.println("Nothing happened, continue exploring.");
        } else if (chance < 0.75) {
            System.out.println("An enemy appeared, you have to fight him!");
        } else {
            System.out.println("A locked room is ahead, complete a challenge and unlock it!");
        }
        int newEnergy = robot.getEnergy() - 10;
        robot.setEnergy(newEnergy);
        }

    public void showStatus() {
        System.out.println("Robot: " + robot.getName());
        System.out.println("========================================");
        System.out.println("Current energy level: " + robot.getEnergy());
        System.out.println("Damage received: " + robot.getDamage());
        System.out.println("EXP: " + robot.getExperiencePoints());
        System.out.println("========================================");
        System.out.println("Shuttle: " + shuttle.getName());
        System.out.println("========================================");
        shuttle.foundArtifacts();
        System.out.println("========================================");
        System.out.println("Rooms discovered but not opened: ");
        for(Room r : rooms) {
            if(r != null && r.isDiscovered() && !r.isOpen()) {
                System.out.println("Room " + r.getNumber() + " - Challenge: " + r.getChallenge().getName());
            }
        }
        System.out.println("Rooms opened: ");
        for(Room r : rooms) {
            if(r != null && r.isOpen()) {
                System.out.println("Room " + r.getNumber() + " - Challenge: " + r.getChallenge().getName());
            }
        }
        System.out.println("Rooms not discovered yet:");
        for(Room r : rooms) {
            if(r != null && !r.isDiscovered()) {
            System.out.println("Room " + r.getNumber());
            }
        }
        System.out.println("========================================");
    }

    public void repairMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;

        while(!done) {
            System.out.println("========================================");
            System.out.println("(1) Repair damage");
            System.out.println("(2) Install artifact");
            System.out.println("(3) Continue exploring");
            System.out.println("========================================");
            
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    if(robot.getDamage() > 0) {
                        robot.repairDamage(10);
                        System.out.println("Your current damage level is: " + robot.getDamage());
                    } else {
                        System.out.println("There is no damage inflicted upon you.");
                    }
                    break;
                case "2":
                    if(robot.getExperiencePoints() < 50) {
                        System.out.println("You need a minimum of 50 EXP to carry out this action!");
                        break;
                    }
                    //if(robot.getExperiencePoints() >= 50) {
                        for(Artifact a: shuttle.getArtifacts()) {
                            if(a.isFound() && !a.isInstalled()) {
                                a.install();
                                System.out.println("Installed: " + a.getName());
                            }
                        }
                        if(shuttle.isReadyToLaunch()) {
                            System.out.println("Your shuttle is ready to launch.");
                            System.out.println("Congratulations, you have cleared the game!");
                            gameFinished = true;
                            done = true;
                        }
                    //}
                    break;
                case "3":
                    done = true;
                    //TODO: Menü anzeigen.
                    break;
                default:
                    System.out.println("Invalid input. Please choose a correct number between 1 and 3.");
                        break;
                    }
                }
            }
                
    private Enemy generateEnemy(){
        
        double Random = Math.random();
        
        if(Random < 0.5){
            return new SpaceCreeper();
        }else{
            return new ElderGuardian();
        }
    }

    private Game generateGame() {

        double random = Math.random();

        if(random < 0.5){
            return new TicTacToe();
        }else{
            return new Nim();
        }
    }

    private void fightingMechanic() {

        Enemy enemies = generateEnemy();
        boolean finished = false;
        double random = Math.random();

        while (robot.isOperational() && !enemies.isDefeated()) { 
                    
            System.out.println("You attack!");
            if (random < 0.8){
                int damage = 10;
                System.out.println("Your hit has dealt " + damage + " damage!");
                enemies.takeDamage(damage);
            } else {
                System.out.println("You missed!");
            }

            if (enemies.isDefeated()){
                System.out.println("Enemy defeated!");
                robot.addExperiencePoints(5);
                finished = true;
                break;
            }

            enemies.fight(robot);

            if(!robot.isOperational()) {
                System.out.println("You died");
                break;
            }

        }
    }

    public void enemyEncounter(Enemy enemy){

        Enemy enemies = generateEnemy();
        Game games = generateGame();
        double random = Math.random();
        //Game Nim = new Nim();

        System.out.println("Warning! Enemy encountered: " +  enemies.name);

        boolean finished = false;

        while (finished){
            System.out.println("\nWhat do you want to do?");
            System.out.println("(1) Fight");
            System.out.println("(2) Play a minigame");
            System.out.println("(3) Try to flee (10% chance)");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();

            switch(choice){
                case "1":
                    fightingMechanic();
                    break;
                case "2":
                    System.out.println("A game is being selected...");
                    if(games instanceof TicTacToe) {
                        System.out.println("TicTacToe was selected.");
                    } else if (games instanceof Nim) {
                            System.out.println("Nim was selected.");
                    }

                    while (!games.isFinished()) {
                        games.playNextRound();
                    }

                    if (games.isWon()) {
                     System.out.println("You've won the game and defeated the " + enemies.name + " !");
                        robot.addExperiencePoints(5);
                        enemies.takeDamage(enemy.lifePoints);
                    } else if (games.isLost()) {
                        System.out.println("You've been defeated, try again!");
                        robot.addExperiencePoints(1);
                    } else {
                        System.out.println("The game ended in a tie.");
                    }
                    break;
                case "3":
                    if (random < 0.1) {
                        System.out.println("You have successfully managed to flee the enemy!");
                    } else {
                        fightingMechanic();
                    }
                    break;
            }
        }
    }
}
