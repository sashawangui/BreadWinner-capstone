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
            System.out.println(Theme.FLYER_RED + "1. Meat " + Theme.FIRE + Theme.RESET);
            System.out.println(Theme.DUCK_YELLOW + "2. Cheese" + Theme.RESET);
            System.out.println(Theme.WATER_GREEN + "3. Veggies" + Theme.RESET);
            System.out.println(Theme.SKY_BLUE + "4. Sauce / Side" + Theme.RESET);
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
        System.out.println(Theme.FLYER_RED + name + (extra ? " (extra)" : "") + " added!" + Theme.RESET);
    }

    private void addCheeseTopping(Sandwich sandwich) {
        String name = input.menuSelection(CHEESES, "Select cheese:");
        boolean extra = input.yesOrNo("Extra portion? (yes/no): ");
        sandwich.addTopping(new Topping(name, "cheese", extra));
        System.out.println(Theme.DUCK_YELLOW + name + (extra ? " (extra)" : "") + " added!" + Theme.RESET);
    }

    private void addVeggieTopping(Sandwich sandwich) {
        String name = input.menuSelection(VEGGIES, "Select veggie:");
        boolean extra = input.yesOrNo("Extra portion? (yes/no): ");
        sandwich.addTopping(new Topping(name, "regular", extra));
        System.out.println(Theme.WATER_GREEN + name + (extra ? " (extra)" : "") + " added!" + Theme.RESET);
    }

    private void addSideTopping(Sandwich sandwich) {
        String[] categories = {"Sauce", "Au Jus"};
        String category = input.menuSelection(categories, "Sauce or Side:");
        if (category.equals("Sauce")) {
            String sauce = input.menuSelection(SAUCES, "Select sauce:");
            sandwich.addTopping(new Topping(sauce, "sauce", false));
            System.out.println(Theme.SKY_BLUE + sauce + " added!" + Theme.RESET);
        } else {
            sandwich.addTopping(new Topping("au jus", "side", false));
            System.out.println(Theme.SKY_BLUE + "Au jus added!" + Theme.RESET);
        }
    }
}