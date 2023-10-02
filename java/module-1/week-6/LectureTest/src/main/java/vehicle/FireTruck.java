package vehicle;

public class FireTruck extends Vehicle{

    private Engine engine;
    public FireTruck(int numberOfDoors, int numberOfWheels, String color) {
        super(numberOfDoors, numberOfWheels, color);
        engine = new Engine();
        engine.setHorsePower(450);
    }

    public FireTruck(){
        super();
        this.setColor("red");
        this.setNumberOfWheels(6);
        this.setNumberOfDoors(4);
        engine = new Engine();
        engine.setHorsePower(450);
    }

    public void honk() {
        System.out.println("Wee woah, wee woah I am a " + getColor() + " firetruck.");
    }

    //---- Getters and Setters ----//

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "FireTruck{" +
                "engine=" + engine +
                '}' + super.toString();
    }
}
