/*package model;

import challenges.Challenge;

public class LockedRoom extends Room{

    private final Challenge challenge;
    private final Artifact artifact;
    private final int roomNumber;
    private boolean isOpened = false;


    public LockedRoom(int roomNumber, Challenge challenge, Artifact artifact){
        super(roomNumber, challenge, "getArtifact()");
        this.roomNumber = roomNumber;
        this.challenge = challenge;
        this.artifact = artifact;
    }

    public int getRoomNumber(){
        return roomNumber;
    }

    public boolean isOpend(){
        return isOpened;
    }


    public Artifact enter(Robot robot){
        if(isOpened){
            System.out.println("this is Room" + roomNumber + " it was already open");
            return null;
        }

        System.out.println("This is Room" + roomNumber + "its locked");
        System.out.println("to open the room you have to complete the challenge");

        if(success)
        
    }

    
}
*/