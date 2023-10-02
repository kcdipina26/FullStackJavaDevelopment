package vehicle;

public class Engine {
    public double horsePower;
    public boolean isElectric;

    //---- Getters and Setters ----//

    public double getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(double horsePower) {
        this.horsePower = horsePower;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "horsePower=" + horsePower +
                ", isElectric=" + isElectric +
                '}';
    }
}
