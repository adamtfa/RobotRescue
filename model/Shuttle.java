/**
* Klasse für das Shuttle, das vom Roboter repariert werden muss.
* @author Adam Tuffaha & Nando Makeem Patton
*/

package model;

import java.io.Serializable;
//import model.Artifact;
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
        for (int i = 0; i < artifacts.length; i++) {
         if(artifacts[i].getName().equalsIgnoreCase(artifactName)){
            return artifacts[i];
         }   
        }
        System.out.println("Method not implemented yet.");
        return null;
    }

    /**
     * Prüft, ob alle Artefakte installiert sind.
     * Das Shuttle ist nur startbereit, wenn jedes Artefakt installiert wurde.
     * Gibt true zurück, wenn alle installiert sind, sonst false.
     */
    public boolean isReadyToLaunch() {
        
        for (int i = 0; i < artifacts.length; i++) {
            if(!artifacts[i].isInstalled()){
                return false;
            }
        }
        return true;
    }

    /**
     * Gibt den Fund- und Installationsstatus jedes Artefakts aus.
     */
    public void foundArtifacts() {
        for(Artifact a : artifacts) {
            if(a.isInstalled()) {
                System.out.println("The module " + a.getName() + " is installed.");
            } else if (a.isFound()){
                System.out.println("The module " + a.getName() + " was found but not installed.");
            }else {
                System.out.println("The module " + a.getName() + " is yet to be found.");
            }
        }
    }
}