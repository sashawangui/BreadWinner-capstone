package com.pluralsight;

import com.pluralsight.models.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SandwichTests {

    @Test
    public void testBasePrice4Inch() {
        Sandwich s = new Sandwich("white", false, 4);
        assertEquals(5.50, s.getPrice());
    }

    @Test
    public void testBasePrice8Inch() {
        Sandwich s = new Sandwich("white", false, 8);
        assertEquals(7.00, s.getPrice());
    }

    @Test
    public void testBasePrice12Inch() {
        Sandwich s = new Sandwich("white", false, 12);
        assertEquals(8.50, s.getPrice());
    }

    @Test
    public void testMeatTopping8Inch() {
        Sandwich s = new Sandwich("white", false, 8);
        s.addTopping(new Topping("bacon", "meat", false));
        assertEquals(9.00, s.getPrice());
    }

    @Test
    public void testExtraMeatAddsOnTop() {
        Sandwich s = new Sandwich("white", false, 8);
        s.addTopping(new Topping("bacon", "meat", true));
        assertEquals(10.00, s.getPrice()); // 7.00 + 2.00 + 1.00
    }

    @Test
    public void testCheeseTopping8Inch() {
        Sandwich s = new Sandwich("white", false, 8);
        s.addTopping(new Topping("cheddar", "cheese", false));
        assertEquals(8.50, s.getPrice()); // 7.00 + 1.50
    }

    @Test
    public void testExtraCheeseAddsOnTop() {
        Sandwich s = new Sandwich("white", false, 8);
        s.addTopping(new Topping("cheddar", "cheese", true));
        assertEquals(9.10, s.getPrice()); // 7.00 + 1.50 + 0.60
    }

    @Test
    public void testRegularToppingFree() {
        Sandwich s = new Sandwich("white", false, 8);
        s.addTopping(new Topping("lettuce", "regular", false));
        assertEquals(7.00, s.getPrice());
    }

    @Test
    public void testOrderTotal() {
        Order order = new Order();
        order.addItem(new Sandwich("white", false, 8));
        order.addItem(new Drink("Coke", "large"));
        order.addItem(new Chip("BBQ"));
        assertEquals(11.50, order.getTotalPrice().doubleValue());
    }

    @Test
    public void testMultipleToppingsPriceAccumulation() {
        Sandwich s = new Sandwich("white", false, 12);
        s.addTopping(new Topping("bacon", "meat", false));
        s.addTopping(new Topping("cheddar", "cheese", true));
        // Base: 8.50 + meat(3.00) + cheese(2.25) + extra cheese(0.90) = 14.65
        assertEquals(14.65, s.getPrice(), 0.01);
    }

    @Test
    public void testAllSandwichSizes() {
        Sandwich s4 = new Sandwich("white", false, 4);
        Sandwich s8 = new Sandwich("white", false, 8);
        Sandwich s12 = new Sandwich("white", false, 12);

        assertEquals(5.50, s4.getPrice());
        assertEquals(7.00, s8.getPrice());
        assertEquals(8.50, s12.getPrice());
    }

    @Test
    public void testDifferentBreadTypes() {
        Sandwich white = new Sandwich("white", false, 8);
        Sandwich wheat = new Sandwich("wheat", false, 8);
        Sandwich rye = new Sandwich("rye", false, 8);

        assertEquals("white", white.getBreadType());
        assertEquals("wheat", wheat.getBreadType());
        assertEquals("rye", rye.getBreadType());
        assertEquals(7.00, white.getPrice());
        assertEquals(7.00, wheat.getPrice());
        assertEquals(7.00, rye.getPrice());
    }

    @Test
    public void testToastedVsUntoasted() {
        Sandwich toasted = new Sandwich("white", true, 8);
        Sandwich untoasted = new Sandwich("white", false, 8);

        assertTrue(toasted.isToasted());
        assertFalse(untoasted.isToasted());
        assertEquals(7.00, toasted.getPrice());
        assertEquals(7.00, untoasted.getPrice());
    }

    @Test
    public void testSetSandwichProperties() {
        Sandwich s = new Sandwich("white", false, 8);
        s.setBreadType("wheat");
        s.setSize(12);
        s.setToasted(true);

        assertEquals("wheat", s.getBreadType());
        assertEquals(12, s.getSize());
        assertTrue(s.isToasted());
    }
}