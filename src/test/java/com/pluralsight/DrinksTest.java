package com.pluralsight;

import com.pluralsight.models.Drink;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DrinksTest {

    @Test
    public void testSmallDrinkPrice() {
        Drink d = new Drink("Coke", "small");
        assertEquals(2.00, d.getPrice());
    }

    @Test
    public void testMediumDrinkPrice() {
        Drink d = new Drink("Coke", "medium");
        assertEquals(2.50, d.getPrice());
    }

    @Test
    public void testLargeDrinkPrice() {
        Drink d = new Drink("Coke", "large");
        assertEquals(3.00, d.getPrice());
    }

    @Test
    public void testInvalidSizeReturnsZero() {
        Drink d = new Drink("Coke", "jumbo");
        assertEquals(0.0, d.getPrice());
    }

    @Test
    public void testDrinkReceiptFormat() {
        Drink d = new Drink("Lemonade", "medium");
        String receipt = d.createReceipt();
        assertTrue(receipt.contains("Lemonade"));
        assertTrue(receipt.contains("medium"));
        assertTrue(receipt.contains("$2.50"));
    }
}