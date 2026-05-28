package com.pluralsight;

import com.pluralsight.models.Order;
import com.pluralsight.models.Chip;
import com.pluralsight.models.Drink;
import com.pluralsight.models.Sandwich;
import com.pluralsight.util.Receipt;
import org.junit.jupiter.api.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTest {

    private Order order;
    private Receipt receipt;
    private String expectedFilePath;

    @BeforeEach
    public void setup() {
        order = new Order();
        receipt = new Receipt();
        String today = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        expectedFilePath = "receipts" + File.separator + today + ".txt";

        File file = new File(expectedFilePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testReceiptFolderCreated() {
        order.addItem(new Chip("BBQ"));
        receipt.saveReceipt(order);
        assertTrue(new File("receipts").exists());
        assertTrue(new File("receipts").isDirectory());
    }

    @Test
    public void testReceiptFileCreated() {
        order.addItem(new Chip("BBQ"));
        receipt.saveReceipt(order);
        assertTrue(new File(expectedFilePath).exists());
    }

    @Test
    public void testReceiptContainsTotal() throws Exception {
        order.addItem(new Chip("BBQ"));
        receipt.saveReceipt(order);

        String content = new String(
                new FileInputStream(expectedFilePath).readAllBytes()
        );
        assertTrue(content.contains("TOTAL: $1.50"));
    }

    @Test
    public void testReceiptContainsSandwich() throws Exception {
        order.addItem(new Sandwich("white", true, 8));
        receipt.saveReceipt(order);

        String content = new String(
                new FileInputStream(expectedFilePath).readAllBytes()
        );
        assertTrue(content.contains("white"));
        assertTrue(content.contains("Toasted"));
    }

    @Test
    public void testReceiptContainsDrink() throws Exception {
        order.addItem(new Drink("Coke", "large"));
        receipt.saveReceipt(order);

        String content = new String(
                new FileInputStream(expectedFilePath).readAllBytes()
        );
        assertTrue(content.contains("Coke"));
        assertTrue(content.contains("large"));
    }

    @Test
    public void testMultipleOrdersAppendToSameDayFile() throws Exception {
        Order order1 = new Order();
        order1.addItem(new Chip("BBQ"));
        receipt.saveReceipt(order1);

        Order order2 = new Order();
        order2.addItem(new Drink("Coke", "large"));
        receipt.saveReceipt(order2);

        String content = new String(
                new FileInputStream(expectedFilePath).readAllBytes()
        );

        assertTrue(content.contains("CHIPS - BBQ"));
        assertTrue(content.contains("DRINK - Coke"));
        assertTrue(content.contains("-".repeat(40)));
    }

    @Test
    public void testEmptyOrderReceipt() throws Exception {
        receipt.saveReceipt(order);

        String content = new String(
                new FileInputStream(expectedFilePath).readAllBytes()
        );

        assertTrue(content.contains("BREADWINNER"));
        assertTrue(content.contains("TOTAL: $0.00"));
    }

    @Test
    public void testReceiptWithAllItemTypes() throws Exception {
        order.addItem(new Sandwich("white", true, 8));
        order.addItem(new Drink("Coke", "large"));
        order.addItem(new Chip("BBQ"));
        receipt.saveReceipt(order);

        String content = new String(
                new FileInputStream(expectedFilePath).readAllBytes()
        );

        assertTrue(content.contains("Sandwich:"));
        assertTrue(content.contains("DRINK"));
        assertTrue(content.contains("CHIPS"));
        assertTrue(content.contains("TOTAL: $" +
                String.format("%.2f", order.getTotalPrice().doubleValue())));
    }

    @Test
    public void testReceiptFolderAlreadyExists() {
        new File("receipts").mkdirs();
        order.addItem(new Chip("BBQ"));

        assertDoesNotThrow(() -> receipt.saveReceipt(order));
        assertTrue(new File(expectedFilePath).exists());
    }

    @Test
    public void testReceiptWithMultipleSandwiches() throws Exception {
        order.addItem(new Sandwich("white", false, 4));
        order.addItem(new Sandwich("wheat", true, 12));
        receipt.saveReceipt(order);

        String content = new String(
                new FileInputStream(expectedFilePath).readAllBytes()
        );

        assertTrue(content.contains("Sandwich: 4\" white"));
        assertTrue(content.contains("Sandwich: 12\" wheat (Toasted)"));
    }

    @Test
    public void testReceiptWithMultipleDrinks() throws Exception {
        order.addItem(new Drink("Coke", "small"));
        order.addItem(new Drink("Sprite", "large"));
        receipt.saveReceipt(order);

        String content = new String(
                new FileInputStream(expectedFilePath).readAllBytes()
        );

        assertTrue(content.contains("Coke"));
        assertTrue(content.contains("Sprite"));
    }

    @AfterEach
    public void cleanup() {
        File file = new File(expectedFilePath);
        if (file.exists()) {
            file.delete();
        }
    }
}