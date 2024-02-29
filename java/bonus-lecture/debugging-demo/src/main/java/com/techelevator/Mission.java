package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class Mission {

    private Spaceship spaceship;
    private List<CrewMember> crewMembers;
    private String missionLog;

    public Mission(Spaceship spaceship) {
        this.spaceship = spaceship;
        this.crewMembers = new ArrayList<>();
        this.missionLog = "";
    }

    public List<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    public void addCrewMember(CrewMember member) {
        crewMembers.add(member);
    }

    public void updateMissionLog(String logEntry) {
        missionLog += logEntry + "\n";
    }

    // Intentional bug: Missing correct crew member count
    public String getMissionStatus() {
        String statusMessage = "Mission on " + spaceship.getName() + " with crew members.\nLog:\n" + missionLog;
        return statusMessage;
    }


}
