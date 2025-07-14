/**
* Klasse für den Spielablauf.
* @author Adam Tuffaha & Nando Makeem Patton
*/

package app;

import app.RobotApp;
import games.Game;
import games.Nim;
import games.TicTacToe;
import java.util.Scanner;

import challenges.Challenge;
import model.Artifact;
import model.ElderGuardian;
import model.Enemy;
import model.Robot;
import model.Room;
import model.Shuttle;
import model.SpaceCreeper;

public class RobotGame {
    private final RobotApp robotApp;
    private final Robot robot;
    private final Room[] rooms = new Room[3];
    private final Shuttle shuttle;
    private final Challenge challenge;
    private boolean gameRunning = true;
    private boolean gameFinished = false;

    /**
     * Initialisiert einen neuen Roboter und ein Shuttle.
     */
    public RobotGame(String robotName, String shuttleName) {
        this.robotApp = new RobotApp();
        this.robot = new Robot(robotName);
        this.shuttle = new Shuttle(shuttleName);
        this.challenge = null;
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
        Scanner scanner = new Scanner(System.in);
        gameRunning = true;

        while (gameRunning && robot.isOperational() && !gameFinished) {
            System.out.println("\n========================================");
            System.out.println("Your robot's name is: " + robot.getName());
            System.out.println("(1) Explore station");
            System.out.println("(2) Show status");
            System.out.println("(3) Recharge energy");
            System.out.println("(4) Repair");
            System.out.println("(5) Exit to main menu");
            System.out.println("========================================");
            System.out.print("Please choose a number between 1 and 5: ");

            String input = scanner.nextLine();
            handleUserInput(input);
        }
        System.out.println("Exiting to main menu\n");
    }

    /**
     * Liest die Benutzereingabe der Konsole.
     * @return userInput
     */
    private String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput;
    }

    /**
     * Prüft und verarbeitet den Input. (gültig/ungültig etc.)
     * @param input
     */
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
                robotApp.showMainMenu();
                gameRunning = false;
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

    /**
     * Methode zur Erkundung der Raumstation.
     */
    public void exploreStation() {
        double chance = Math.random();
        
        if(chance < 0.5) {
            System.out.println("Nothing happened, continue exploring.");
        } else if (chance < 0.75) {
            System.out.println("An enemy appeared, you have to fight him!");
            enemyEncounter();
        } else {
            System.out.println("A locked room is ahead, complete a challenge and unlock it!");
            roomEncounter();
        }
        int newEnergy = robot.getEnergy() - 10;
        robot.setEnergy(newEnergy);
        }

    /**
     * Methode, um den Status des Roboters im derzeitigen Spiel anzuzeigen.
     */
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

    /**
     * Methode, um das Menü zur Reparatur des Roboters und des Shuttles anzuzeigen.
     */
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
                    if(robot.getExperiencePoints() >= 50) {
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
                    }
                    break;
                case "3":
                    done = true;
                    break;
                default:
                    System.out.println("Invalid input. Please choose a correct number between 1 and 3.");
                        break;
                    }
                }
            }
    
    /**
     * Generiert zufällig einen Feind der beiden möglichen Arten.
     * @return SpaceCreeper oder ElderGuardian
     */
    private Enemy generateEnemy(){
        double Random = Math.random();
        
        if(Random < 0.5) {
            return new SpaceCreeper();
        }else{
            return new ElderGuardian();
        }
    }

    /**
     * Generiert zufällig eins der beiden Minigames.
     * @return TicTacToe oder Nim
     */
    private Game generateGame() {
        double random = Math.random();

        if(random < 0.5) {
            return new Nim();
        }else{
            return new TicTacToe();
        }
    }

    /**
     * Generiert zufällig einen der drei Räume für die Challenges und Artefakte.
     * @param number
     * @return einen der drei Räume
     */
    private Room generateRoom(int number) {
        double random = Math.random();

        if (random < 0.33) {
            return new Room(number, new challenges.BinaryCode(), "Navigation module");
        } else if (random < 0.66) {
            return new Room(number, new challenges.MorseCode(), "Control system");
        } else {
            return new Room(number, new challenges.SecretCode(), "Energy crystal");
        }
    }

    /**
     * Kampfmethode, so lange bis entweder der Roboter oder der Feind außer gefecht gesetzt wurde.
     * @param enemy
     * @return true/false je nach Ausgang des Kampfes
     */
    private boolean fightingMechanic(Enemy enemy) {
        while (robot.isOperational() && !enemy.isDefeated()) {
            System.out.println("You attack!");
            if (Math.random() < 0.8) {
                int damage = 10;
                System.out.println("You dealt " + damage + " damage!");
                enemy.takeDamage(damage);
            } else {
                System.out.println("You missed!");
            }

            if (enemy.isDefeated()) {
                System.out.println(" Enemy defeated!");
                return true;
            }

            enemy.fight(robot);

            if (!robot.isOperational()) {
                System.out.println(" You were destroyed...");
                return false;
            }
        }
        return false;
    }

    /**
     * Zeigt ein Menü mit Optionen an, die bei der Konfrontation mit einem Feind zur Auswahl stehen.
     */
    public void enemyEncounter() {
        Scanner scanner = new Scanner(System.in);
        Enemy enemy = generateEnemy();
        Game game = generateGame();
        boolean finished = false;

        System.out.println("\nWarning! Enemy encountered: " + enemy.name);

        while (!finished) {
            System.out.println("\nWhat do you want to do?");
            System.out.println("(1) Fight");
            System.out.println("(2) Play a minigame");
            System.out.println("(3) Try to flee (10% chance)");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": {
                    boolean victory = fightingMechanic(enemy);
                    if (victory) {
                        robot.addExperiencePoints(5);
                    } else {
                        robot.addExperiencePoints(1);
                    }
                    finished = true;
                    break;
                }
                case "2": {
                    System.out.println("A game is being selected...");
                    if (game instanceof TicTacToe) {
                        System.out.println("TicTacToe was selected.");
                    } else if (game instanceof Nim) {
                        System.out.println("Nim was selected.");
                    }

                    while (!game.isFinished()) {
                        game.playNextRound();
                    }

                    if (game.isWon()) {
                        System.out.println("You've won the game and defeated the " + enemy.name + "!");
                        enemy.takeDamage(enemy.lifePoints);
                        robot.addExperiencePoints(5);
                    } else if (game.isLost()) {
                        System.out.println("You've been defeated in the game.");
                        robot.addExperiencePoints(1);
                    } else {
                        System.out.println("The game ended in a tie.");
                    }

                    finished = true;
                    break;
                }
                case "3": {
                    if (Math.random() < 0.1) {
                        System.out.println("You successfully fled!");
                    } else {
                        System.out.println("You failed to flee. A fight begins!");
                        boolean victory = fightingMechanic(enemy);
                        if (victory) {
                            robot.addExperiencePoints(5);
                        } else {
                            robot.addExperiencePoints(1);
                        }
                    }
                    finished = true;
                    break;
                }
                default:
                    System.out.println("Invalid input. Please choose 1, 2, or 3.");
            }
        }
    }

    /**
     * Roboter trifft auf einen Raum und muss die dazugehörige Challenge absolvieren.
     */
    public void roomEncounter() {
        //TODO: Räume können mehrmals vorkommen.
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] == null) {
                Room newRoom = generateRoom(i + 1);
                newRoom.setDiscovered(true);
                rooms[i] = newRoom;

                System.out.println("You discovered Room " + newRoom.getNumber() + "!");
                System.out.println("Challenge: " + newRoom.getChallenge().getName());
                System.out.println(newRoom.getChallenge().getDescription());

                newRoom.getChallenge().start();

                if (newRoom.getChallenge().isSolved()) {
                    newRoom.setOpen(true);
                    System.out.println("Success! Room " + newRoom.getNumber() + " is now open.");
                } else {
                    System.out.println("Challenge failed. Room " + newRoom.getNumber() + " remains locked.");
                }
            }
        }
    }
}
