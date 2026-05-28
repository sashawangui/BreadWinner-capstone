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

    @Test
    public void testAllDrinkFlavors() {
        Drink coke = new Drink("Coke", "large");
        Drink sprite = new Drink("Sprite", "large");
        Drink lemonade = new Drink("Lemonade", "large");

        assertEquals("Coke", coke.getFlavor());
        assertEquals("Sprite", sprite.getFlavor());
        assertEquals("Lemonade", lemonade.getFlavor());
        assertEquals(3.00, coke.getPrice());
        assertEquals(3.00, sprite.getPrice());
        assertEquals(3.00, lemonade.getPrice());
    }

    @Test
    public void testSetDrinkFlavor() {
        Drink d = new Drink("Coke", "medium");
        d.setFlavor("Pepsi");
        assertEquals("Pepsi", d.getFlavor());
    }

    @Test
    public void testCaseInsensitiveSize() {
        Drink d1 = new Drink("Coke", "SMALL");
        Drink d2 = new Drink("Coke", "MEDIUM");
        Drink d3 = new Drink("Coke", "LARGE");

        assertEquals(2.00, d1.getPrice());
        assertEquals(2.50, d2.getPrice());
        assertEquals(3.00, d3.getPrice());
    }

    @Test
    public void testNullFlavorThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Drink(null, "large");
        });
        assertTrue(exception.getMessage().contains("cannot be null"));
    }

    @Test
    public void testNullSizeThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Drink("Coke", null);
        });
        assertTrue(exception.getMessage().contains("cannot be null"));
    }
}