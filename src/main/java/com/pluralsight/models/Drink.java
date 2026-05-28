package com.pluralsight.models;

public class Drink implements OrderItem {
    private String flavor;
    private String size;
    private double price;

    public Drink(String flavor, String size) {
        if (flavor == null || flavor.trim().isEmpty()) {
            throw new IllegalArgumentException("Flavor cannot be null or empty");
        }
        if (size == null || size.trim().isEmpty()) {
            throw new IllegalArgumentException("Size cannot be null or empty");
        }
        this.flavor = flavor;
        this.size = size;
        this.price = calculateDrinkPrice(size);
    }

    private double calculateDrinkPrice(String size) {
        return switch (size.toLowerCase()) {
            case "small" -> 2.00;
            case "medium" -> 2.50;
            case "large" -> 3.00;
            default -> 0.0;
        };
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String createReceipt() {
        return String.format("DRINK - %s (%s) - $%.2f%n", flavor, size, price);
    }
}