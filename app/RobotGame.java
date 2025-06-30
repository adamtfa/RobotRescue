/**
* Klasse für den Spielablauf.
* @author Adam Tuffaha & Nando Makeem Patton
*/

package app;

import java.util.Scanner;

import model.Robot;
import model.Room;
import model.Shuttle;
import model.Artifact;
import model.Enemy;

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
}
