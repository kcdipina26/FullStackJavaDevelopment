package com.techelevator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class SpaceshipTest {
    @Test
    public void testCalculateFuel() {
        Spaceship spaceship = new Spaceship("TestShip", 100, "Earth", "The Moon", 100000);
        double distanceToMoon = 238855; // Distance to the Moon in miles
        double expectedFuel = distanceToMoon / 100; // Expected fuel should be distance / efficiency


        Assertions.assertEquals(expectedFuel, spaceship.calculateFuel(distanceToMoon));


    }
}
