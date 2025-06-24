/**
* Klasse für den Spielablauf.
* @author Adam Tuffaha & Nando Makeem Patton
*/

package app;

import model.Robot;
import model.Room;
import model.Shuttle;

public class RobotGame {
    private final Robot robot;
    private final Room[] rooms = new Room[3];
    private final Shuttle shuttle;
    private boolean gameRunning = true;
    private boolean gameFinished = false;

    /**
     * Initialisiert einen neuen Roboter und ein Shuttle.
     */
    public RobotGame() {
        this.robot = new Robot("name");
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
        System.out.println("The game has started. Or not?");
    }

    /**
     * Gibt den erstellten Roboter zurück.
     * @return der erstellte Roboter
     */
    public Robot getRobot() {
        return robot;
    }
}
