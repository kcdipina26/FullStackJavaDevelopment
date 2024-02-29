package com.techelevator;

import java.util.Objects;

public class Spaceship {

    private final int COUNT_DOWN_TIME = 5;
    private String name;
    private double fuelEfficiency; // Fuel efficiency in AUs per gallon
    private Engine engine = new Engine();  // composition
    private FuelTank fuelTank; // composition
    private Navigation navigation; // composition


    public Spaceship(String name, double fuelEfficiency, String currentLocation, String destination, int initialFuel) {
        this.name = name;
        this.fuelEfficiency = fuelEfficiency;
        this.fuelTank = new FuelTank(initialFuel);
        this.navigation = new Navigation(currentLocation, destination);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFuelEfficiency() {
        return fuelEfficiency;
    }

    public void setFuelEfficiency(double fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
    }

    // Intentional bug: incorrect fuel calculation
    public double calculateFuel(double distance) {
        double result = distance * fuelEfficiency;
        return result;
    }


    public void countDown(int seconds) {
        System.out.println("Countdown to takeoff:");
        try {
            for (int i = seconds; i > 0; i--) {
                System.out.print(i + "...");
                Thread.sleep(1000); // Wait for 1 second
            }
            System.out.println("Takeoff!");
        } catch (InterruptedException e) {
            System.out.println("Countdown was interrupted");
        }
    }


    public void startEngine() {
        if (fuelTank.getFuelLevel() > 0) {
            engine.start();
        } else {
            System.out.println("Cannot start engine. No fuel.");
        }
    }


    public void stopEngine() {
        engine.stop();
    }



    public void takeOff() {
        if (!engine.isRunning()) {
            System.out.println("Cannot take off. Engine is not running.");
            return;
        }
        if (fuelTank.getFuelLevel() <= 0) {
            System.out.println("Cannot take off. No fuel.");
            return;
        }
        countDown(COUNT_DOWN_TIME); // Start a 5-second countdown
        // Takeoff logic
        System.out.println(name + " is taking off towards " + navigation.getDestination() + "!");
        fuelTank.consumeFuel(50); // Example fuel consumption
    }


    public void land() {
        if (engine.isRunning()) {
            System.out.println(name + " is landing on " + navigation.getDestination() + "!");
            fuelTank.consumeFuel(30); // Example fuel consumption
        } else {
            System.out.println("Cannot land. Engine is not running.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spaceship spaceship = (Spaceship) o;
        return Double.compare(spaceship.fuelEfficiency, fuelEfficiency) == 0 && name.equals(spaceship.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fuelEfficiency);
    }
}
