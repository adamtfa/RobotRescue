/**
* Klasse für den Spielablauf.
* @author Adam Tuffaha & Nando Patton
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

    public RobotGame() {
        this.robot = new Robot();
        this.shuttle = new Shuttle("name");
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public void run() {
        System.out.println("The game has started. Or not?");
    }

    public Robot getRobot() {
        return robot;
    }
}
