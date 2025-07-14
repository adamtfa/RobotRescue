/**
 * Klasse für den Gegner ElderGuardian, der dem Roboter Energie entzieht.
 * @author Adam Tuffaha & Nando Makeem Patton
 */
package model;

public class ElderGuardian extends Enemy{
    /**
     * Konstruktor setzt Name und Lebenspunkte.
     */
    public ElderGuardian() {
        super("ElderGuardian", 10);
    }

    /**
     * Führt den Angriff auf den Roboter aus. Der Gegner entzieht dem Roboter Energie.
     */
    public void fight(Robot robot) {
        System.out.println(name + " Is draining your energy!");
        robot.drainEnergy(15);
    }
}
