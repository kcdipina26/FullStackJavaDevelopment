package com.techelevator;

public class RectangleWall extends Wall {
    private final int length;
    private final int height;

    public RectangleWall(String name, String color, int length, int height) {
        super(name, color);
        this.length = length;
        this.height = height;
    }

    @Override
    public int getArea(){
        return length * height;

    }
    @Override
    public String toString() {
        return getName() + "(" + length + "x" + height + ") rectangle";
    }
}
