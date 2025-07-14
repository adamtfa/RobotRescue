package model;

public class SpaceCreeper extends Enemy {

    /**
     * Konstruktor – erstellt einen SpaceCreeper mit 10 Lebenspunkten.
     */
    public SpaceCreeper() {
        super("SpaceCreeper", 10);
    }

    /**
     * Führt den Angriff aus – verursacht festen Schaden beim Roboter.
     * @param robot Der angegriffene Roboter
     */
    public void fight(Robot robot) {
        System.out.println(name + " Is attacking you!");
        robot.takeDamage(10);
    }
}

