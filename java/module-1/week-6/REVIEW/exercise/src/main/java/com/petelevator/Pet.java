package com.petelevator;
import java.util.ArrayList;
import java.util.List;

public class Pet {
    private String name;
    private String species;
    private List<String> vaccinations;

    public Pet(String name, String species) {
        this.name = name;
        this.species = species;
        this.vaccinations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String listVaccinations() {
        return String.join(", ", vaccinations);
    }

    public void addVaccination(String vaccination) {
        vaccinations.add(vaccination);
    }
}


