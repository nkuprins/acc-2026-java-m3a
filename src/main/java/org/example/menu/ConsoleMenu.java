package org.example.menu;

import org.example.model.*;
import org.example.shelter.Shelter;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final Shelter<Animal> shelter;
    private final Scanner scanner =  new Scanner(System.in);
    private final List<MenuOption> options = List.of(
            new MenuOption(1, "Add animal"),
            new MenuOption(2, "List all animals"),
            new MenuOption(3, "Find animals by species"),
            new MenuOption(4, "List available animals"),
            new MenuOption(5, "Mark animal as adopted"),
            new MenuOption(0, "Exit")
    );
    public ConsoleMenu(Shelter<Animal> shelter) {
        this.shelter = shelter;
    }

    public void start(){
        boolean running = true;
        while (running) {
            System.out.println();
            printMenu();
            System.out.print("Choose an option: ");
            int option;
            try {
                option = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid option.");
                continue;
            }
            switch (option) {
                case 1:
                    addAnimal();
                    break;
                case 2:
                    printAnimals(shelter.getAllAnimals());
                    break;
                case 3:
                    findBySpecies();
                    break;
                case 4:
                    printAnimals(shelter.findAvailableAnimals());
                    break;
                case 5:
                    markAsAdopted();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        System.out.println("Goodbye!");
    }

    private void addAnimal(){
        System.out.print("Species (dog/cat/bird): ");
        String species = scanner.nextLine().trim().toLowerCase();
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Age: ");
        int age;
        try {
            age = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Age must be a number.");
            return;
        }

        Animal animal;
        switch (species) {
            case "dog":
                animal = new Dog(new AnimalId(), name, age);
                break;
            case "cat":
                animal = new Cat(new AnimalId(), name, age);
                break;
            case "bird":
                animal = new Bird(new AnimalId(), name, age);
                break;
            default:
                System.out.println("Unknown species: " + species);
                return;
        }
        shelter.addAnimal(animal);
        System.out.println("Added: " + animal);
    }

    private void findBySpecies(){
        System.out.print("Species: ");
        String species = scanner.nextLine().trim();
        printAnimals(shelter.findBySpecies(species));
    }

    private void markAsAdopted(){
        System.out.print("Animal id: ");
        String id = scanner.nextLine().trim();
        try {
            shelter.markAsAdopted(id);
            System.out.println("Animal marked as adopted.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printAnimals(List<Animal> animals){
        if (animals.isEmpty()) {
            System.out.println("No animals found.");
            return;
        }
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    private void printMenu(){
        for (MenuOption option : options) {
            System.out.println(option.number() + ". " + option.label());
        }
    }
}
