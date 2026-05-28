package com.pluralsight.ui;

import com.pluralsight.models.*;
import com.pluralsight.util.BuildSandwich;
import com.pluralsight.util.InputValidator;
import com.pluralsight.util.Receipt;

import java.util.ArrayList;
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
            System.out.println("\n=================================");
            System.out.println("     BREADWINNER SANDWICHES");
            System.out.println("=================================");
            System.out.println("1. Create New Order");
            System.out.println("0. Exit");
            System.out.println("=================================");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1 -> createOrder();
                    case 0 -> {
                        System.out.println("Goodbye! 🥪");
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
        boolean addingItems = true;

        while (addingItems) {
            System.out.println("\n=================================");
            System.out.println("         BREADWINNER");
            System.out.println("=================================");
            System.out.println("1. Add Sandwich");
            System.out.println("2. Add Drink");
            System.out.println("3. Add Chips");
            System.out.println("4. Checkout");
            System.out.println("5. Remove Item");
            System.out.println("0. Cancel Order");
            System.out.println("=================================");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1 -> addSandwich();
                    case 2 -> addDrink();
                    case 3 -> addChips();
                    case 4 -> {
                        if (!currentOrder.getItems().isEmpty()) {
                            checkout();
                            addingItems = false;
                        } else {
                            System.out.println("Your order is empty!");
                        }
                    }
                    case 5 -> removeItem();
                    case 0 -> {
                        System.out.println("Order cancelled.");
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
        String flavor = input.getValidString("Enter flavor (Coke, Sprite, etc.): ");
        String[] sizes = {"small", "medium", "large"};
        String size = input.menuSelection(sizes, "Select size:");
        Drink drink = new Drink(flavor, size);
        currentOrder.addItem(drink);
        System.out.printf("%s (%s) added - $%.2f%n", flavor, size, drink.getPrice());
    }

    private void addChips() {
        System.out.println("\n--- Add Chips ---");
        String flavor = input.getValidString("Enter chip flavor (BBQ, Plain, etc.): ");
        Chip chips = new Chip(flavor);
        currentOrder.addItem(chips);
        System.out.printf("%s chips added - $%.2f%n", flavor, chips.getPrice());
    }

    private void removeItem() {
        if (currentOrder.getItems().isEmpty()) {
            System.out.println("Your order is empty!");
            return;
        }

        System.out.println("\n--- Remove Item ---");
        ArrayList<OrderItem> items = currentOrder.getItems();
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%d. %s", i + 1, items.get(i).createReceipt());
        }

        int choice = input.getValidInt("Enter item number to remove (0 to cancel): ");
        if (choice == 0) return;

        if (choice >= 1 && choice <= items.size()) {
            OrderItem removed = items.get(choice - 1);
            currentOrder.removeItem(choice - 1);
            System.out.println("Removed from order!");
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private void checkout() {
        displayOrder();
        if (input.yesOrNo("Confirm order? (yes/no): ")) {
            receipt.saveReceipt(currentOrder);
            System.out.println("Thank you for your order! \uD83E\uDD6A");
        } else {
            System.out.println("Order cancelled.");
            currentOrder = null;
        }
    }

    private void displayOrder() {
        System.out.println("\n========== YOUR ORDER ==========");
        for (OrderItem item : currentOrder.getItems()) {
            System.out.print(item.createReceipt());
        }
        System.out.println("================================");
        System.out.printf("TOTAL: $%.2f%n",
                currentOrder.getTotalPrice().doubleValue());
        System.out.println("================================");
    }
}