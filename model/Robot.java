/**
* Klasse für den Roboter.
* @author Adam Tuffaha & Nando Makeem Patton
*/

package model;

import java.io.Serializable;

public class Robot implements Serializable {



    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private final String name;
    private int energy = 50;
    private int damage = 0;
    private int experiencePoints = 0;
    private boolean operational = true; 
    private static final long serialVersionUID = -5081867320134061285L;

    public Robot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

    public void recharge(int energy) {
        this.energy += energy;
        if(this.energy > 100) {
            this.energy = 100;
        }
    }

    public void drainEnergy(int energy) {
        double chance = Math.random();
        int energyLoss;

        if(chance < 0.1) {
            energyLoss = energy * 2;
        } else if (chance < 0.2) {
            energyLoss = energy / 2;
        } else {
            energyLoss = energy;
        }

        this.energy -= energyLoss;

        if(this.energy <= 0) {
            this.energy = 0;
            operational = false;            
        }
    }

    public int getDamage() {
        return damage;
    }

    public void takeDamage(int damage) {
        double chance = Math.random();
        int takenDamage;

        if(chance < 0.1) {
            takenDamage = damage * 2;
        } else if (chance < 0.2) {
            takenDamage = damage / 2;
        } else {
            takenDamage = damage;
        }

        this.damage += takenDamage;

        if(this.damage >= 100) {
            this.damage = 100;
            operational = false;
        }
    }

    public void repairDamage(int damage) {
        this.damage -= damage;
        if(this.damage < 0) {
            this.damage = 0;
        }
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void addExperiencePoints(int experiencePoints) {
        this.experiencePoints += experiencePoints;
    }

    public boolean isOperational() {
        return operational;
    }

    public void setEnergy(int value) {
        if (value < 0)  {
            value = 0;
        }
        if (value > 100) {
            value = 100;
        }
    this.energy = value;
    }
}
