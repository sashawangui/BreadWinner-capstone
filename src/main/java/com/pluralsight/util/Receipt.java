package com.pluralsight.util;

import com.pluralsight.models.Order;
import com.pluralsight.models.OrderItem;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receipt {
    public void saveReceipt(Order order) {
        // Create receipts folder if it doesn't exist
        File receiptsDir = new File("receipts");
        if (!receiptsDir.exists()) {
            receiptsDir.mkdirs();
        }

        // Generate daily filename using the current date
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String filePath = "receipts" + File.separator + today + ".txt";

        // Get current time for this specific order
        String orderTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        // before opening writer check if the file exists and has content
        File file = new File(filePath);
        boolean fileHasContent = file.exists() && file.length() > 0;

        // Append to daily file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {

            // Add separator between orders
            if (fileHasContent) {
                writer.write("\n" + "-".repeat(60) + "\n\n");
            }

            // Write receipt header
            writer.write("=".repeat(60));
            writer.newLine();
            writer.write(String.format("BREADWINNER RECEIPT - Order #%s", orderTime));
            writer.newLine();
            writer.write("=".repeat(60));
            writer.newLine();
            writer.write(String.format("Date: %s%n",
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            writer.write(String.format("Time: %s%n", orderTime));
            writer.write("-".repeat(60));
            writer.newLine();
            writer.newLine();

            // Write each order item
            for (OrderItem item : order.getItems()) {
                writer.write(item.createReceipt());
                writer.newLine();
            }

            // Write total
            writer.newLine();
            writer.write("-".repeat(60));
            writer.newLine();
            writer.write(String.format("TOTAL: $%.2f", order.getTotalPrice().doubleValue()));
            writer.newLine();
            writer.write("=".repeat(60));
            writer.newLine();
            writer.write("Thank you for visiting!\uD83D\uDE9A\uD83D\uDCA8 ");
            writer.newLine();

            System.out.println("Receipt saved to: " + filePath);

        } catch (Exception e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }
}