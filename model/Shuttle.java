/**
* Klasse für das Shuttle, das vom Roboter repariert werden muss.
* @author Adam Tuffaha & Nando Makeem Patton
*/


package model;

import java.io.Serializable;

public class Shuttle implements Serializable {

    private static final long serialVersionUID = 3230694418L;

    private final String name;

    private final Artifact[] artifacts = new Artifact[] {
            new Artifact(Artifact.NAVIGATION_MODULE),
            new Artifact(Artifact.CONTROL_SYSTEM),
            new Artifact(Artifact.ENERGY_CRYSTAL)
    };

    /**
     * Erstellt ein Shuttle und dessen Namen.
     * @param name
     */
    public Shuttle(String name) {
        this.name = name;
    }

    /**
     * Gibt den Namen des Shuttles zurück.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt die Artefakte in einem Array zurück.
     * @return artifacts
     */
    public Artifact[] getArtifacts() {
        return artifacts;
    }

    /**
     * Gibt ein Artefakt anhand des übergebenen Namens zurück.
     * Die Suche soll unabhängig von Groß- und Kleinschreibung erfolgen.
     * Wenn kein Artefakt mit dem Namen gefunden wird, soll null zurückgegeben werden.
     */
    public Artifact getArtifactByName(String artifactName) {
        System.out.println("Method not implemented yet.");
        // TODO: Durchlaufe das Array 'artifacts' und vergleiche jeden Namen (ignoriere Groß-/Kleinschreibung).
        // Gib das passende Artefakt zurück, wenn es gefunden wurde, sonst null.
        return null;
    }

    /**
     * Prüft, ob alle Artefakte installiert sind.
     * Das Shuttle ist nur startbereit, wenn jedes Artefakt installiert wurde.
     * Gibt true zurück, wenn alle installiert sind, sonst false.
     */
    public boolean isReadyToLaunch() {
        System.out.println("Method not implemented yet.");
        // TODO: Überprüfe für jedes Artefakt im Array 'artifacts', ob es installiert ist.
        // Sobald ein Artefakt nicht installiert ist, gib false zurück.
        // Falls alle installiert sind, gib true zurück.
        return false;
    }
}