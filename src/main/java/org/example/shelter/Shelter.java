package org.example.shelter;

import org.example.model.AdoptionStatus;
import org.example.model.Animal;
import org.example.model.AnimalId;

import java.util.ArrayList;
import java.util.List;

public class Shelter<T extends Animal> {
    private final List<T> animals = new ArrayList<>();

    public void addAnimal(T animal) {
        animals.add(animal);
    }

    public List<T> getAllAnimals() {
        // Unmodifiable shallow copy so callers can't change the shelter's internal list
        return List.copyOf(animals);
    }

    // We did not really cover streams, but I would use them otherwise
    public List<T> findBySpecies(String species) {
        List<T> result = new ArrayList<>();
        for (T animal : animals) {
            if (animal.getSpecies().equalsIgnoreCase(species)) {
                result.add(animal);
            }
        }
        return result;
    }

    // We did not really cover streams, but I would use them otherwise
    public List<T> findAvailableAnimals() {
        List<T> result = new ArrayList<>();
        for (T animal : animals) {
            if (animal.getAdoptionStatus() == AdoptionStatus.AVAILABLE) {
                result.add(animal);
            }
        }
        return result;
    }

    public void markAsAdopted(String id) {
        AnimalId animalId = new AnimalId(id);
        for (T animal : animals) {
            if (animal.getId().equals(animalId)) {
                animal.markAsAdopted();
                return;
            }
        }
        throw new IllegalArgumentException("No animal found with id: " + id);
    }
}
