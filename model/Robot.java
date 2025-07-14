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

    /**
     * Konstruktor – erstellt einen Roboter mit Namen und Startwerten.
     * @param name Name des Roboters
     */
    public Robot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }


    /**
     * Lädt den Roboter um einen bestimmten Energiewert auf (max. 100).
     * @param energy Menge der Energie, die aufgeladen wird
     */
    public void recharge(int energy) {
        this.energy += energy;
        if(this.energy > 100) {
            this.energy = 100;
        }
    }

    /**
     * Verringert die Energie des Roboters, mit Zufallsmodifikator (Chance auf mehr/weniger Verlust).
     * @param energy Menge der Basis-Energie, die verloren geht
     */
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
    
    /**
     * Verursacht Schaden am Roboter, mit Zufallsmodifikator.
     * @param damage Menge des eingehenden Schadens
     */
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

    /**
     * Repariert den Roboter um einen bestimmten Schadenswert.
     * @param damage Menge des zu reparierenden Schadens
     */
    public void repairDamage(int damage) {
        this.damage -= damage;
        if(this.damage < 0) {
            this.damage = 0;
        }
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }
    
    /**
     * Fügt dem Roboter Erfahrungspunkte hinzu.
     * @param experiencePoints Punkte, die hinzugefügt werden sollen
     */
    public void addExperiencePoints(int experiencePoints) {
        this.experiencePoints += experiencePoints;
    }

    public boolean isOperational() {
        return operational;
    }
        
    /**
     * Setzt den Energielevel direkt (begrenzter Bereich 0–100).
     * @param value Neue Energie
     */
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
