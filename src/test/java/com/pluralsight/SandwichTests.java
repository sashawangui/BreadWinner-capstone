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
}