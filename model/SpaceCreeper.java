package model;

public class SpaceCreeper extends Enemy {
        public SpaceCreeper() {
        super("SpaceCreeper", 100);
    }
    public void fight(Robot robot) {
        System.out.println(name + " Is attacking you!");
        robot.takeDamage(10);
    }
}

