package vehicle;

public class Car extends Vehicle{
    private Engine engine;
    private int numberOfSeats;

    public Car(int numberOfDoors, int numberOfWheels, String color, int numberOfSeats) {
        super(numberOfDoors, numberOfWheels, color);
        this.numberOfSeats = numberOfSeats;
        setEngine(new Engine());
        setNumberOfVehicles(getNumberOfVehicles()+1);
    }

    public Car() {
        super();
        this.setColor("red");
        this.setNumberOfWheels(6);
        this.setNumberOfDoors(4);
        engine = new Engine();
        engine.setHorsePower(150);
        engine.setElectric(true);
    }

    @Override
    public void honk() {
        System.out.println("Honk-Honk I am a " + getColor() + " car.");
    }

    //---- Getters and Setters ----//

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", numberOfSeats=" + numberOfSeats +
                '}'+
                super.toString();

    }
}
