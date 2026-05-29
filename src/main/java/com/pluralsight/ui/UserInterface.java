package com.pluralsight.ui;

import com.pluralsight.models.*;
import com.pluralsight.util.*;

import java.util.Scanner;

public class UserInterface {

    private Scanner scanner = new Scanner(System.in);
    private Order currentOrder;
    private Receipt receipt = new Receipt();
    private InputValidator input = new InputValidator(scanner);
    private BuildSandwich sandwichBuilder = new BuildSandwich(input);

    public void start() {
        boolean running = true;
        while (running) {
            Theme.printBanner("BREADWINNER");
            System.out.println("1. Create New Order");
            System.out.println("0. Exit");
            Theme.printBorder();
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1 -> createOrder();
                    case 0 -> {
                        System.out.println(Theme.DUCK_FACE + " Goodbye! " + Theme.BREAD_LOAF);
                        running = false;
                    }
                    default -> System.out.println("Invalid option, try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public void createOrder() {
        currentOrder = new Order();
        OrderConfirmationHandler confirmationHandler = new OrderConfirmationHandler(input, receipt);
        boolean addingItems = true;

        while (addingItems) {
            Theme.printBanner("ORDER MENU");
            System.out.println("1. Add Sandwich " + Theme.BREAD_LOAF);
            System.out.println("2. Add Drink " + Theme.WATER_SPLASH);
            System.out.println("3. Add Chips");
            System.out.println("4. Checkout " + Theme.DELIVERY_VAN);
            System.out.println("5. Remove Item");
            System.out.println("0. Cancel Order " + Theme.FLYER);
            Theme.printBorder();
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1 -> addSandwich();
                    case 2 -> addDrink();
                    case 3 -> addChips();
                    case 4 -> {
                        OrderConfirmationHandler handler = new OrderConfirmationHandler(input, new Receipt());
                        int result = handler.confirmOrder(currentOrder);

                        if (result == OrderConfirmationHandler.CONFIRMED) {
                            addingItems = false;
                        } else if (result == OrderConfirmationHandler.CANCELLED) {
                            System.out.println("Order cancelled. Starting fresh.");
                            currentOrder = new Order();
                        } else if (result == OrderConfirmationHandler.EDIT_REQUESTED) {
                            System.out.println("Returning to menu to add more items.");
                        }
                    }
                    case 5 -> confirmationHandler.removeItem(currentOrder);
                    case 0 -> {
                        System.out.println("Order canceled.");
                        currentOrder = null;
                        addingItems = false;
                    }
                    default -> System.out.println("Invalid option, try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private void addSandwich() {
        Sandwich sandwich = sandwichBuilder.build();
        currentOrder.addItem(sandwich);
        System.out.printf("Sandwich added! $%.2f%n", sandwich.getPrice());
    }

    private void addDrink() {
        System.out.println("\n--- Add Drink ---");
        String flavor = input.menuSelection(Drink.FLAVORS, "Select drink:");
        String[] sizes = {"small", "medium", "large"};
        String size = input.menuSelection(sizes, "Select size:");
        Drink drink = new Drink(flavor, size);
        currentOrder.addItem(drink);
        System.out.printf(Theme.WATER_SPLASH + " %s (%s) added - $%.2f%n",
                flavor, size, drink.getPrice());
    }

    private void addChips() {
        System.out.println("\n--- Add Chips ---");
        String flavor = input.menuSelection(Chip.FLAVORS, "Select chips:");
        Chip chips = new Chip(flavor);
        currentOrder.addItem(chips);
        System.out.printf(Theme.DUCK_YELLOW + "%s chips added - $%.2f" + Theme.RESET + "%n",
                flavor, chips.getPrice());
    }
}