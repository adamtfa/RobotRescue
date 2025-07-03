package model;

public class ElderGuardian extends Enemy{
        public ElderGuardian() {
        super("ElderGuardian", 150);
    }
    public void fight(Robot robot) {
        System.out.println(name + " Is draining your energy!");
        robot.drainEnergy(15);
    }
}
