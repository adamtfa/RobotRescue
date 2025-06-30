/**
* Klasse für die Räume, die vom Roboter betreten werden können.
* @author Adam Tuffaha & Nando Makeem Patton
*/


package model;

import challenges.Challenge;
import java.io.Serializable;

public class Room implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden. 
    private static final long serialVersionUID = 3947234501234725063L;

    private final int number;
    private final Challenge challenge;
    private final String artifact;
    private boolean open = false;
    private boolean discovered = false;

    public Room(int number, Challenge challenge, String artifact) {
        this.number = number;
        this.challenge = challenge;
        this.artifact = artifact;
    }

    public int getNumber(){
        return number;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public String getArtifact() {
        return artifact;
    }

    public boolean isOpen(){
        return open;
    }

    public void setOpen(boolean open) {
        if(open && !this.open) {
            this.open = true;
        }
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(boolean discovered) {
        if(discovered && !this.discovered) {
            this.discovered = true;
        }
    }
}

