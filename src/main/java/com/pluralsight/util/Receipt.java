package com.pluralsight.util;

import com.pluralsight.models.Order;
import com.pluralsight.models.OrderItem;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receipt {
    public void saveReceipt(Order order) {
        File receiptsDir = new File("receipts");
        if (!receiptsDir.exists()) {
            receiptsDir.mkdirs();
        }

        String today = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String filePath = "receipts" + File.separator + today + ".txt";
        String orderTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("h:mm a"));

        File file = new File(filePath);
        boolean fileHasContent = file.exists() && file.length() > 0;

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(filePath, true))) {

            if (fileHasContent) {
                writer.newLine();
                writer.write("-".repeat(40));
                writer.newLine();
                writer.newLine();
            }

            writer.write(String.format("BREADWINNER - %s", orderTime));
            writer.newLine();
            writer.write("=".repeat(40));
            writer.newLine();

            for (OrderItem item : order.getItems()) {
                writer.write(item.createReceipt());
            }

            writer.write("=".repeat(40));
            writer.newLine();
            writer.write(String.format("TOTAL: $%.2f%n",
                    order.getTotalPrice().doubleValue()));
            writer.newLine();
            writer.write("Thank you for visiting BreadWinner!\uD83D\uDE9A\uD83D\uDCA8 ");
            writer.newLine();

            System.out.println("\n========== YOUR RECEIPT ==========");
            for (OrderItem item : order.getItems()) {
                System.out.print(item.createReceipt());
            }
            System.out.println("==================================");
            System.out.printf("TOTAL: $%.2f%n", order.getTotalPrice().doubleValue());
            System.out.println("==================================");
            System.out.println("Thank you for visiting BreadWinner! 🚚💨");

        } catch (Exception e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }
}