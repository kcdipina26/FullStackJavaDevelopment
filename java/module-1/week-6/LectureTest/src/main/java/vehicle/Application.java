package vehicle;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new FireTruck());
        vehicles.add(new FireTruck(4, 6, "Green"));
        vehicles.add(new Car());
        vehicles.add(new Car(4,4, "blue",4));


        System.out.println(Vehicle.getNumberOfVehicles());

        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof FireTruck) {
                System.out.println(vehicle);
            }

        }
        for (Vehicle vehicle : vehicles) {
            vehicle.honk();

        }


    }

}
