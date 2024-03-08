package com.techelevator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class MissionTest {

    @Test
    public void testGetMissionStatus() {
        Spaceship spaceship = new Spaceship("TestShip", 1.0, "Earth", "The Moon", 100000);
        Mission mission = new Mission(spaceship);
        mission.addCrewMember(new CrewMember("John Doe"));
        mission.addCrewMember(new CrewMember("Jane Doe"));
        mission.updateMissionLog("Mission started.");

        String status = mission.getMissionStatus();

        // Check if the mission status contains the correct number of crew members
        Assertions.assertTrue(status.contains("2 crew members"));

        // Additionally, check if the log is appended correctly
        Assertions.assertTrue(status.contains("Mission started."));
    }
}
