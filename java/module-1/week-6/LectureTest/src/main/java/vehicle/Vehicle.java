package vehicle;

public abstract class Vehicle implements Honks {
    private int numberOfDoors;
    private int numberOfWheels;
    private String color;
    private static int numberOfVehicles;

    public Vehicle(int numberOfDoors, int numberOfWheels, String color) {
        this.numberOfDoors = numberOfDoors;
        this.numberOfWheels = numberOfWheels;
        this.color = color;
        numberOfVehicles++;
    }

    public Vehicle() {
        numberOfVehicles++;
    }

    //---- Getters and Setters ----//
    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public void setNumberOfWheels(int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public static void setNumberOfVehicles(int numberOfVehicles) {
        Vehicle.numberOfVehicles = numberOfVehicles;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "numberOfDoors=" + numberOfDoors +
                ", numberOfWheels=" + numberOfWheels +
                ", color='" + color + '\'' +
                '}';
    }
}
