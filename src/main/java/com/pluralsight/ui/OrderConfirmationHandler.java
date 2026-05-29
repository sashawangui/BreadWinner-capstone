package com.pluralsight.ui;

import com.pluralsight.models.Order;
import com.pluralsight.models.OrderItem;
import com.pluralsight.util.InputValidator;
import com.pluralsight.util.Receipt;
import com.pluralsight.util.Theme;

import java.util.ArrayList;

public class OrderConfirmationHandler {
    private final InputValidator input;
    private final Receipt receipt;

    public OrderConfirmationHandler(InputValidator input, Receipt receipt) {
        this.input = input;
        this.receipt = receipt;
    }

    // Runs the confirmation/edit/cancel flow for an order.
     // true if order was confirmed and placed, false if canceled.

    public boolean confirmOrder(Order order) {
        while (true) {
            displayOrder(order);
            System.out.println("\n--- Confirm Order ---");
            System.out.println("1. Yes, place order");
            System.out.println("2. Edit order");
            System.out.println("3. Cancel order");
            int choice = input.getValidInt("Enter your choice: ");

            switch (choice) {
                case 1 -> {
                    receipt.saveReceipt(order);
                    System.out.println(Theme.DUCK_FACE + " Thank you for your order! " + Theme.BREAD_LOAF);
                    return true; // confirmed
                }
                case 2 -> editOrder(order);
                case 3 -> {
                    if (cancelOrder(order)) {
                        return false; // cancelled
                    }
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    private void displayOrder(Order order) {
        System.out.println("\n========== YOUR ORDER ==========");
        for (OrderItem item : order.getItems()) {
            System.out.print(item.createReceipt());
        }
        System.out.println("================================");
        System.out.printf("TOTAL: $%.2f%n", order.getTotalPrice().doubleValue());
        System.out.println("================================");
    }

    private void editOrder(Order order) {
        System.out.println("\n--- Edit Order ---");
        System.out.println("1. Add more items");
        System.out.println("2. Remove an item");
        System.out.println("3. Clear entire order and start over");
        int choice = input.getValidInt("What would you like to do? ");

        switch (choice) {
            case 1 -> System.out.println("Returning to order menu. Add items or checkout when ready.");
            case 2 -> removeItem(order);
            case 3 -> {
                order.clear();
                System.out.println("Order cleared. Returning to order menu.");
            }
            default -> System.out.println("Invalid choice, returning to order menu.");
        }
        // After edit, we just return – confirmation loop will re-show order.
    }

    public void removeItem(Order order) {
        ArrayList<OrderItem> items = order.getItems();
        if (items.isEmpty()) {
            System.out.println("No items to remove.");
            return;
        }

        System.out.println("\n--- Remove Item ---");
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%d. %s", i + 1, items.get(i).createReceipt());
        }

        int choice = input.getValidInt("Enter item number to remove (0 to cancel): ");
        if (choice == 0) return;

        if (choice >= 1 && choice <= items.size()) {
            order.removeItem(choice - 1);
            System.out.println("Removed from order!");
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private boolean cancelOrder(Order order) {
        System.out.println("\n--- Cancel Order ---");
        System.out.println("1. Yes, cancel entire order");
        System.out.println("2. No, go back");
        int choice = input.getValidInt("Are you sure? ");

        if (choice == 1) {
            order.clear();
            System.out.println("Order cancelled.");
            return true; // cancelled
        } else {
            return false; // go back to confirmation
        }
    }
}