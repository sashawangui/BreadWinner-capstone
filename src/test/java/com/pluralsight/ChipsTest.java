package com.pluralsight;

import com.pluralsight.models.Chip;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChipsTest {

    @Test
    public void testChipsPrice() {
        Chip c = new Chip("BBQ");
        assertEquals(1.50, c.getPrice());
    }

    @Test
    public void testChipsReceiptFormat() {
        Chip c = new Chip("Ranch");
        String receipt = c.createReceipt();
        assertTrue(receipt.contains("Ranch"));
        assertTrue(receipt.contains("$1.50"));
    }

    @Test
    public void testMultipleChipFlavors() {
        Chip bbq = new Chip("BBQ");
        Chip plain = new Chip("Plain");
        Chip saltVinegar = new Chip("Salt & Vinegar");

        assertEquals("BBQ", bbq.getType());
        assertEquals("Plain", plain.getType());
        assertEquals("Salt & Vinegar", saltVinegar.getType());
        assertEquals(1.50, bbq.getPrice());
        assertEquals(1.50, plain.getPrice());
        assertEquals(1.50, saltVinegar.getPrice());
    }

    @Test
    public void testSetChipType() {
        Chip c = new Chip("BBQ");
        c.setType("Ranch");
        assertEquals("Ranch", c.getType());
    }

    @Test
    public void testNullChipTypeThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Chip(null);
        });
        assertTrue(exception.getMessage().contains("cannot be null or empty"));
    }

    @Test
    public void testEmptyChipTypeThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Chip("");
        });
        assertTrue(exception.getMessage().contains("cannot be null or empty"));
    }
}