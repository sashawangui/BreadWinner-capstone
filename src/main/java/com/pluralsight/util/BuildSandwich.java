package com.pluralsight.util;

import com.pluralsight.models.*;

public class BuildSandwich {

    private static final String[] MEATS =
            {"steak", "ham", "salami", "roast beef", "chicken", "bacon"};
    private static final String[] CHEESES =
            {"american", "provolone", "cheddar", "swiss"};
    private static final String[] VEGGIES =
            {"lettuce", "peppers", "onions", "tomatoes",
                    "jalapeños", "cucumbers", "pickles", "guacamole", "mushrooms"};
    private static final String[] SAUCES =
            {"mayo", "mustard", "ketchup", "ranch",
                    "thousand islands", "vinaigrette"};
    private static final String[] BREADS =
            {"white", "wheat", "rye", "wrap"};
    private static final String[] SIZES = {"4", "8", "12"};

    private InputValidator input;

    public BuildSandwich(InputValidator input) {
        this.input = input;
    }

    public Sandwich build() {
        String bread = input.menuSelection(BREADS, "Select bread:");
        int size;
        while (true) {
            size = input.getValidInt("Enter sandwich size (4, 8, or 12 inches): ");
            if (size == 4 || size == 8 || size == 12) break;
            System.out.println("Invalid size. Please enter 4, 8, or 12.");
        }
        boolean toasted = input.yesOrNo("Toast? (yes/no): ");

        Sandwich sandwich = new Sandwich(bread, toasted, size);

        boolean addingToppings = true;
        while (addingToppings) {
            System.out.println("\n--- Add Toppings ---");
            System.out.println("1. Meat");
            System.out.println("2. Cheese");
            System.out.println("3. Veggies");
            System.out.println("4. Sauce / Side");
            System.out.println("0. Done");

            int choice = input.getValidInt("Choice: ");
            switch (choice) {
                case 1 -> addMeatTopping(sandwich);
                case 2 -> addCheeseTopping(sandwich);
                case 3 -> addVeggieTopping(sandwich);
                case 4 -> addSideTopping(sandwich);
                case 0 -> addingToppings = false;
                default -> System.out.println("Invalid option.");
            }
        }
        return sandwich;
    }
    private void addMeatTopping(Sandwich sandwich) {
        String name = input.menuSelection(MEATS, "Select meat:");
        boolean extra = input.yesOrNo("Extra portion? (yes/no): ");
        sandwich.addTopping(new Topping(name, "meat", extra));
        System.out.println(name + (extra ? " (extra)" : "") + " added!");    }

    private void addCheeseTopping(Sandwich sandwich) {
        String name = input.menuSelection(CHEESES, "Select cheese:");
        boolean extra = input.yesOrNo("Extra portion? (yes/no): ");
        sandwich.addTopping(new Topping(name, "cheese", extra));
        System.out.println(name + " added!");
    }

    private void addVeggieTopping(Sandwich sandwich) {
        String name = input.menuSelection(VEGGIES, "Select veggie:");
        boolean extra = input.yesOrNo("Extra portion? (yes/no): ");
        sandwich.addTopping(new Topping(name, "regular", extra));
        System.out.println(name + (extra ? " (extra)" : "") + " added!");
    }

    private void addSideTopping(Sandwich sandwich) {
        String[] categories = {"Sauce", "Au Jus"};
        String category = input.menuSelection(categories, "Sauce or Side:");
        if (category.equals("Sauce")) {
            String sauce = input.menuSelection(SAUCES, "Select sauce:");
            sandwich.addTopping(new Topping(sauce, "sauce", false));
            System.out.println(sauce + " added!");
        } else {
            sandwich.addTopping(new Topping("au jus", "side", false));
            System.out.println("Au jus added!");
        }
    }
}