package com.pluralsight;

import com.pluralsight.models.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTests {

    @Test
    public void testRemoveItemFromOrder() {
        Order order = new Order();
        Drink drink = new Drink("Coke", "large");
        order.addItem(drink);
        assertEquals(1, order.getItems().size());
        order.removeItem(0);
        assertEquals(0, order.getItems().size());
    }

    @Test
    public void testRemoveItemInvalidIndexDoesNothing() {
        Order order = new Order();
        order.addItem(new Drink("Coke", "large"));
        order.removeItem(5);
        assertEquals(1, order.getItems().size());
    }

    @Test
    public void testEmptyOrderTotalIsZero() {
        Order order = new Order();
        assertEquals(BigDecimal.ZERO, order.getTotalPrice());
    }

    @Test
    public void testOrderWithMultipleSameItems() {
        Order order = new Order();
        order.addItem(new Chip("BBQ"));
        order.addItem(new Chip("Plain"));
        assertEquals(3.00, order.getTotalPrice().doubleValue());
    }
}