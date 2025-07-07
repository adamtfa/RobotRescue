/**
* Klasse für die Artefakte, die vom Roboter gefunden werden können.
* @author Adam Tuffaha & Nando Makeem Patton
*/

package model;

import java.io.Serializable;

public class Artifact implements Serializable {

    private static final long serialVersionUID = 3743681153808233500L;

    public static final String NAVIGATION_MODULE = "Navigation module";
    public static final String CONTROL_SYSTEM = "Control system";
    public static final String ENERGY_CRYSTAL = "Energy crystal";

    private final String name;
    private boolean found;
    private boolean installed;

    /**
     * Initialisiert ein Artefakt.
     * @param name
     */
    public Artifact(String name) {
        this.name = name;
        this.found = false;
        this.installed = false;
    }

    /**
     * Gibt den Namen des Artefaktes zurück.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt zurück, ob das Artefakt gefunden wurde.
     * @return
     */
    public boolean isFound() {
        return found;
    }

    /**
     * Setzt den Status des Artefaktes auf "gefunden".
     * @param found
     */
    public void setFound(boolean found) {
        this.found = found;
    }

    /**
     * Gibt den Status der Verwendung zurück (bereits installiert/nicht installiert).
     * @return Status der Verwwndung
     */
    public boolean isInstalled() {
        return installed;
    }

    /**
     * Prüft, ob das Artefakt bereits installiert bzw. verwendet wurde.
     */
    public void install() {
        if (found) {
            this.installed = true;
        }
    }
}