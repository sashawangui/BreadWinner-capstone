package com.pluralsight.models;

public class Chip implements OrderItem{
    private String type;
    private double price;
    public static final String[] FLAVORS = {
            "BBQ", "Plain", "Salt & Vinegar", "Sour Cream & Onion",
            "Cheddar", "Spicy Jalapeño"
    };

    public Chip(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Chip type cannot be null or empty");
        }
        this.type = type;
        this.price = 1.50;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String createReceipt() {
        return String.format("CHIPS - %s - $%.2f%n", type, price);
    }
}