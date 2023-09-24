package com.techelevator;

public  abstract class Wall {
    private final String name;
    private final String color;

    public Wall(String name, String color) {
        this.name = name;
        this.color = color;

    }
    public abstract int getArea();
    public String getName() {
        return name;
    }

    public String getColor(){
        return color;
    }
}
