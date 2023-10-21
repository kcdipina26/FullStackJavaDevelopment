package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    // DO NOT REMOVE the DEFAULT_NUMBER_OF_... constants!!!!!
    public static final int DEFAULT_NUMBER_OF_COMPACT_SLOTS = 3;
    public static final int DEFAULT_NUMBER_OF_MIDSIZE_SLOTS = 5;
    public static final int DEFAULT_NUMBER_OF_FULLSIZE_SLOTS = 2;

    /*
    Fill in the class details here...
     */
    private String name;
    private boolean open = false;
    private int numberOfCompactSlots = DEFAULT_NUMBER_OF_COMPACT_SLOTS;
    private int numberOfMidsizeSlots = DEFAULT_NUMBER_OF_MIDSIZE_SLOTS;
    private int numberOfFullsizeSlots = DEFAULT_NUMBER_OF_FULLSIZE_SLOTS;

    private List<Car> compactCars = new ArrayList<>();
    private List<Car> midsizeCars = new ArrayList<>();
    private List<Car> fullsizeCars = new ArrayList<>();

    // Constructors
    public ParkingLot(String name) {
        this.name = name;
    }

    public ParkingLot(String name, boolean open) {
        this.name = name;
        this.open = open;
    }

    public ParkingLot(String name, boolean open, int numberOfCompactSlots, int numberOfMidsizeSlots, int numberOfFullsizeSlots) {
        this.name = name;
        this.open = open;
        this.numberOfCompactSlots = numberOfCompactSlots;
        this.numberOfMidsizeSlots = numberOfMidsizeSlots;
        this.numberOfFullsizeSlots = numberOfFullsizeSlots;
    }

    // Getters and setters
    public String getName() {
        return this.name;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public int getNumberOfCompactSlots() {
        return this.numberOfCompactSlots;
    }

    public int getNumberOfMidsizeSlots() {
        return this.numberOfMidsizeSlots;
    }

    public int getNumberOfFullsizeSlots() {
        return this.numberOfFullsizeSlots;
    }

    // Methods
    public int getLotSize() {
        return numberOfCompactSlots + numberOfMidsizeSlots + numberOfFullsizeSlots;
    }

    public int numberOfAvailableSlots(String carType) {
        switch (carType) {
            case Car.COMPACT:
                return numberOfCompactSlots - compactCars.size();
            case Car.MIDSIZE:
                return numberOfMidsizeSlots - midsizeCars.size();
            case Car.FULLSIZE:
                return numberOfFullsizeSlots - fullsizeCars.size();
            default:
                return 0;
        }
    }

    public boolean park(Car car) throws ParkingLotException {
        if (!open) {
            throw new ParkingLotException("Parking lot is closed.");
        }

        switch (car.getType()) {
            case Car.COMPACT:
                if (compactCars.size() < numberOfCompactSlots) {
                    compactCars.add(car);
                    return true;
                }
                break;
            case Car.MIDSIZE:
                if (midsizeCars.size() < numberOfMidsizeSlots) {
                    midsizeCars.add(car);
                    return true;
                }
                break;
            case Car.FULLSIZE:
                if (fullsizeCars.size() < numberOfFullsizeSlots) {
                    fullsizeCars.add(car);
                    return true;
                }
                break;
        }
        return false;
    }

    public Car exit(String license) {
        for (Car car : compactCars) {
            if (car.getLicense().equals(license)) {
                compactCars.remove(car);
                return car;
            }
        }
        for (Car car : midsizeCars) {
            if (car.getLicense().equals(license)) {
                midsizeCars.remove(car);
                return car;
            }
        }
        for (Car car : fullsizeCars) {
            if (car.getLicense().equals(license)) {
                fullsizeCars.remove(car);
                return car;
            }
        }
        return null;
    }
}

