/**
* Klasse für die Feinde, die vom Roboter bekämpft werden können.
* @author Adam Tuffaha & Nando Makeem Patton
*/


package model;

public abstract class Enemy {
    public String name;
    public int lifePoints;
    
    /**
     * Abstrakte Methode zum Angriff – muss in Unterklassen implementiert werden.
     * @param robot Der Roboter, der angegriffen wird
     */
    public Enemy(String name, int lifePoints) {
        this.name = name;
        this.lifePoints = lifePoints;
    }

    /**
     * Abstrakte Methode zum Angriff – muss in Unterklassen implementiert werden.
     * @param robot Der Roboter, der angegriffen wird
     */
    public abstract void fight(Robot robot);

    /**
     * Verarbeitet erlittenen Schaden.
     * @param amount Die Höhe des Schadens
     */
    public void takeDamage(int amount) {
        lifePoints = lifePoints - amount;
        if(lifePoints <= 0) {
            lifePoints = 0;
        }
        System.out.println(name + " Has taken " + amount + "of damage!");
        System.out.println("It has " + lifePoints + " remaining.");
    }

    /**
     * Prüft, ob der Gegner besiegt wurde.
     * @return true, wenn Lebenspunkte ≤ 0
     */
    public boolean isDefeated() {
        return lifePoints <= 0;
    }
}

