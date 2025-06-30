/**
* Klasse für die Feinde, die vom Roboter bekämpft werden können.
* @author Adam Tuffaha & Nando Makeem Patton
*/


package model;

public abstract class Enemy {
    public String name;
    public int lifePoints;
    public abstract void fight(Robot robot);

    public Enemy(String name, int lifePoints) {
        this.name = name;
        this.lifePoints = lifePoints;
    }

    public void takeDamage(int amount) {
        lifePoints = lifePoints - amount;
        if(lifePoints <= 0) {
            lifePoints = 0;
        }

        System.out.println(name + " Has taken " + amount + "of damage!");
        System.out.println("It has " + lifePoints + " remaining.");
    }

    public boolean isDefeated() {
        return lifePoints <= 0;
    }

}

class SpaceCreeper extends Enemy {
    public SpaceCreeper() {
        super("SpaceCreeper", 100);
    }
    public void fight(Robot robot) {
        System.out.println(name + " Is attacking you!");
        robot.takeDamage(10);
    }
}

class ElderGuardian extends Enemy {
    public ElderGuardian() {
        super("ElderGuardian", 150);
    }
    public void fight(Robot robot) {
        System.out.println(name + " Is draining your energy!");
        robot.drainEnergy(15);
    }
}