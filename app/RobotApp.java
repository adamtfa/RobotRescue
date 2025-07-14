/**
* Klasse für das Hauptmenü.
* @author Adam Tuffaha & Nando Makeem Patton
*/

package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class RobotApp {

    public static final String SAVE_FILE_NAME = "save";
    private RobotGame game;

    /**
     * Main-Methode des Spiels.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the robot adventure");
        System.out.println("========================================\n");

        RobotApp app = new RobotApp();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            app.showMainMenu();
            String choice = scanner.nextLine();
            app.handleUserInput(choice);

            // Wenn das Spiel beendet wurde, Spielstatus zurücksetzen
            if (app.game != null && app.game.isGameFinished()) {
                app.game = null;
            }
        }
    }

    /**
     * Zeigt das Hauptmenü und dessen verfügbare Aktionen.
     */
    public void showMainMenu() {
        System.out.println("You're in the main menu");
        System.out.println("What do you want to do next?");
        System.out.println("(1) Start new game");

        if (isGameRunning()) {
            System.out.println("(2) Continue game");
        }

        if (hasSavedGame()) {
            System.out.println("(3) Load game");
        }

        if (isGameRunning()) {
            System.out.println("(4) Save game");
        }

        if (hasSavedGame()) {
            System.out.println("(5) Delete game");
        }

        System.out.println("(6) Quit");
        System.out.println("\n========================================");
        System.out.print("Please choose a number between 1 and 6: ");
    }

    /**
     * Liest die Benutzereingabe der Konsole.
     * @return Scanner Eingabe.
     */
    private String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Prüft und verarbeitet den Input. (gültig/ungültig etc.)
     * @param input
     */
    public void handleUserInput(String input) {
        switch (input) {
            case "1":
                this.startNewGame();
                break;
            case "2":
                if (isGameRunning()) {
                    this.continueGame();
                } else {
                    System.out.println("There's currently no game to continue.");
                }
                break;
            case "3":
                if (hasSavedGame()) {
                    this.loadGame();
                } else {
                    System.out.println("There's currently no game to load.");
                }
                break;
            case "4":
                if (isGameRunning()) {
                    this.saveGame();
                } else {
                    System.out.println("There's currently no game to save.");
                }
                break;
            case "5":
                if (hasSavedGame()) {
                    this.deleteGame();
                } else {
                    System.out.println("There's currently no game to delete.");
                }
                break;
            case "6":
                System.out.println("See you!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input. Please choose a correct number between 1 and 6.");
                break;
        }
    }

    /**
     * Erstellt ein neues Spiel. 
     */
    public void startNewGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a name for your robot: ");
        String robotName = scanner.nextLine();
        System.out.println("Choose a name for your shuttle: ");
        String shuttleName = scanner.nextLine();

        this.game = new RobotGame(robotName, shuttleName);
        game.run();
    }

    /**
     * Setzt ein laufendes Spiel fort.
     */
    public void continueGame() {
        if (game != null && game.isGameRunning()) {
            game.run();
        } else {
            System.out.println("No running game to continue.");
        }
    }

    /**
     * Löscht ein bereits vorhandenes Spiel.
     */
    public void deleteGame() {
        if (new File(SAVE_FILE_NAME).delete()) {
            System.out.println("Game deleted!");
        }
    }

    /**
     * Speichert den aktuellen Spielstand.
     */
    public void saveGame() {
        try (FileOutputStream fos = new FileOutputStream(SAVE_FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
            oos.flush();
            System.out.println("Game saved!");
        } catch (Exception ex) {
            System.err.println("Something went wrong while saving the game: " + ex.getMessage());
        }
    }

    /**
     * Lädt den Spielstand eines bereits vorhandenen Spiels.
     */
    public void loadGame() {
        try (FileInputStream fis = new FileInputStream(SAVE_FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            this.game = (RobotGame) ois.readObject();
            System.out.println("Game loaded!");
        } catch (Exception ex) {
            System.err.println("Something went wrong while loading the game: " + ex.getMessage());
        }
    }

    /**
     * Prüft, ob das Spiel läuft.
     * @return true
     */
    public boolean isGameRunning() {
        return game != null && game.isGameRunning();
    }

    /**
     * Prüft, ob das Spiel beendet ist/wurde.
     * @return true
     */
    public boolean isGameFinished() {
        return game != null && game.isGameFinished();
    }

    /**
     * Prüft, ob es einen gespeicherten Spielstand gibt.
     * @return true
     */
    public boolean hasSavedGame() {
        return new File(SAVE_FILE_NAME).exists();
    }
}
