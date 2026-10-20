package org.example.model;

public final class Capybara extends Animal {
    public Capybara(AnimalId id, String name, int age) {
        super(id, name, age);
    }

    public Capybara(String name, int age) {
        super(name, age);
    }

    @Override
    public String getSpecies() {
        return "Capybara";
    }
}
