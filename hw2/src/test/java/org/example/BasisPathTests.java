package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasisPathTests {//for removeProduct method

    private ShoppingCart cart;
    private ProductModel product1;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
        product1 = new ProductModel("Apple", 1, 1, "kg");
    }

    //Path 1: There is no product, the process is terminated.
    @Test
    void testIsProductAvailable() {
        int removedQuantity = cart.removeProduct(new ProductModel("Orange", 1, 1, "kg"), 1);
        assertEquals(0, removedQuantity);
    }

    //Path 2: Product exists, quantity is insufficient, the process is terminated.
    @Test
    void testIsQuantityEnough() {
        cart.addProduct(product1, 1);
        int removedQuantity = cart.removeProduct(product1, 2);
        assertEquals(0, removedQuantity);
        assertEquals(1, cart.getProducts()[0].getQuantity());
    }

    //Path 3: The product exists, the quantity is sufficient, the product quantity decreases, it does not become zero, the total cost is updated.
    @Test
    void testIfQuantityEnough() {
        cart.addProduct(product1, 3);
        int removedQuantity = cart.removeProduct(product1, 1);
        assertEquals(1, removedQuantity);
        assertEquals(2, cart.getProducts()[0].getQuantity());
        assertEquals(2, cart.getTotalCost());
    }

    //Path 4: The product exists, the quantity is sufficient, the product quantity decreases to zero, the product is removed from the list and the total cost is updated.
    @Test
    void testRemovingFromList() {
        cart.addProduct(product1, 1);
        int removedQuantity = cart.removeProduct(product1, 1);
        assertEquals(1, removedQuantity);
        assertEquals(0, cart.getTotalProducts());
        assertEquals(0, cart.getTotalCost());
    }

}
