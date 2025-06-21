/**
* Klasse für das Hauptmenü, zeigt den Spielstand des Spiels.
* @author Adam Tuffaha & Nando Makeem Patton
*/
package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import model.Robot;

public class RobotApp {

    public static final String SAVE_FILE_NAME = "save";
    private RobotGame game;
    private boolean gameRunning = true;

    /**
     * Main-Methode des Spiels.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the robot adventure");
        System.out.println("========================================\n");

        RobotApp app = new RobotApp();

        while (true) {
            app.showMainMenu();
            String choice = app.readUserInput();
            app.handleUserInput(choice);
            System.out.println("====================");
        }
    }

    /**
     * Zeigt das Hauptmenü und dessen verfügbare Aktionen.
     */
    private void showMainMenu() {
        System.out.println("You're in the main menu");
        System.out.println("What do you want to do next?");
        System.out.println("(1) Start new game");
        System.out.println("(6) Quit");
        System.out.println("");
        System.out.println("Please choose a number between 1 and 6: ");
    }

    /**
     * Liest die Benutzereingabe der Konsole.
     * @return userInput
     */
    private String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        // TBD
        return userInput;
    }

    /**
     * Prüft und verarbeitet den Input. (gültig/ungültig etc.)
     * @param input
     */
    private void handleUserInput(String input) {
        switch (input) {
            case "1":
                this.startNewGame();
                break;
            case "2":
                break;
            // ...
            case "6":
                break;
            default:
                System.out.println("Invalid input. Please choose a correct number between 1 and 6");
                break;
        }
    }

    /**
     * Erstellt ein neues Spiel. 
     */
    private void startNewGame() {
        this.game = new RobotGame();
        continueGame();
    }

    /**
     * Setzt ein laufendes Spiel fort.
     */
    private void continueGame() {
        this.game.setGameRunning(true);
        this.game.run();
    }

    /**
     * Löscht ein bereits vorhandenes Spiel.
     */
    private void deleteGame() {
        if (new File(SAVE_FILE_NAME).delete()) {
            System.out.println("Game deleted!");
        }
    }

    /**
     * Speichert den aktuellen Spielstand.
     */
    private void saveGame() {
        try (FileOutputStream fos = new FileOutputStream(SAVE_FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
            oos.flush();
        } catch (Exception ex) {
            System.err.println("Something went wrong while saving the game: " + ex.getMessage());
            return;
        }
        System.out.println("Game saved!");
    }

    /**
     * Lädt den Spielstand eines bereits vorhandenen Spiels.
     */
    private void loadGame() {
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
    private boolean isGameRunning() {
        return game != null;
    }

    /**
     * Prüft, ob das Spiel beendet ist/wurde.
     * @return true
     */
    private boolean isGameFinished() {
        return game != null && game.isGameFinished();
    }

    /**
     * Prüft, ob es einen gespeicherten Spielstand gibt.
     * @return true
     */
    private boolean hasSavedGame() {
        return new File(SAVE_FILE_NAME).exists();
    }

}