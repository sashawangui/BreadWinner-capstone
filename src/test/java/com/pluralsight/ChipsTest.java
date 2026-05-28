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
}