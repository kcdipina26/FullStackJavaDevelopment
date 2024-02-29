package com.techelevator;

import java.util.Locale;
import java.util.Scanner;

public class SpaceAppCli {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Space Mission Simulator!");



        Spaceship falcon = new Spaceship("Millennium Falcon", 10, "Earth", "The Moon", 2500); // Very inefficient fuel usage for demonstration
        Mission spaceMission = new Mission(falcon);
        spaceMission.addCrewMember(new CrewMember("Han Solo"));

        spaceMission.updateMissionLog("Mission preflight checklist started.");
        spaceMission.updateMissionLog("Mission preflight checklist completed.");

        double fuelNeeded = falcon.calculateFuel(1100);
        System.out.println("Fuel needed for the Moon: " + fuelNeeded + " gallons");
        System.out.println(spaceMission.getMissionStatus());

        boolean missionInProgress = false;
        System.out.print("Press Y to begin mission (press any other key to abort): ");
        String userSelection = scanner.nextLine();
        if(userSelection.toLowerCase().equals("y")){
            missionInProgress = true;
        }


        while (missionInProgress) {
            System.out.println("\n1. Start Engine\n2. Take Off\n3. Land\n4. Stop Engine\n5. Exit Simulation");
            System.out.print("Choose an action: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    falcon.startEngine();
                    break;
                case 2:
                    falcon.takeOff();
                    break;
                case 3:
                    falcon.land();
                    break;
                case 4:
                    falcon.stopEngine();
                    break;
                case 5:
                    missionInProgress = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        System.out.println("Mission simulation ended.");


    }
}