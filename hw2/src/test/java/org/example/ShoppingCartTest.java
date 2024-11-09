package org.example;

// import testng classpath

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


class ShoppingCartTest {

    private ShoppingCart cart;
    private ProductModel product1;
    private ProductModel product2;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
        product1 = new ProductModel("Apple", 1, 1, "kg");
        product2 = new ProductModel("Banana", 2, 1, "kg");
    }

    @Test
    void getProducts() {
        cart.addProduct(product1, 1);
        ProductModel[] products = cart.getProducts();
        assertEquals(1, products[0].getQuantity());
        assertEquals("Apple", products[0].getName());
        assertNotNull(products);
    }

    @Test
    void addProduct() {
        cart.addProduct(product1, 1);
        assertEquals(1, cart.getTotalProducts());
        assertEquals(1, cart.getProducts()[0].getQuantity());
        assertEquals(1, cart.getTotalCost());

        cart.addProduct(product1, 2);
        assertEquals(1, cart.getTotalProducts());
        assertEquals(3, cart.getProducts()[0].getQuantity());
        assertEquals(3, cart.getTotalCost());

        cart.addProduct(product2, 1);
        assertEquals(2, cart.getTotalProducts());
        assertEquals(1, cart.getProducts()[1].getQuantity());
        assertEquals(5, cart.getTotalCost());
    }

    @Test
    void removeProduct() {
        cart.addProduct(product1, 3);
        cart.addProduct(product2, 2);

        int removedQuantity = cart.removeProduct(product1, 1);
        assertEquals(1, removedQuantity);
        assertEquals(2, cart.getProducts()[0].getQuantity());
        assertEquals(6, cart.getTotalCost());

        removedQuantity = cart.removeProduct(product1, 2);
        assertEquals(2, removedQuantity);
        assertEquals(1, cart.getTotalProducts());
        assertEquals(4, cart.getTotalCost());

        removedQuantity = cart.removeProduct(product2, 2);
        assertEquals(2, removedQuantity);
        assertEquals(0, cart.getTotalProducts());
        assertEquals(0, cart.getTotalCost());
    }

    @Test
    void getTotalCost() {
        cart.addProduct(product1, 1);
        cart.addProduct(product2, 2);
        assertEquals(5, cart.getTotalCost());
        assertNotEquals(0, cart.getTotalCost());
    }

    @Test
    void getTotalProducts() {
        cart.addProduct(product1, 1);
        cart.addProduct(product2, 2);
        assertEquals(2, cart.getTotalProducts());
        assertNotEquals(0, cart.getTotalProducts());
    }

    @Test
    void findProductIndex() {
        cart.addProduct(product1, 1);
        cart.addProduct(product2, 2);
        assertEquals(0, cart.findProductIndex(product1));
        assertEquals(1, cart.findProductIndex(product2));
        assertTrue(cart.findProductIndex(product1) >= 0);
        assertFalse(cart.findProductIndex(product1) < 0);
    }

    @Test
    void printCart() {
        cart.addProduct(product1, 1);
        cart.addProduct(product2, 2);
        cart.printCart();
        assertNotNull(cart);
    }

    @Test
    void clearCart() {
        cart.addProduct(product1, 1);
        cart.addProduct(product2, 2);
        cart.clearCart();
        assertEquals(0, cart.getTotalProducts());
        assertEquals(0, cart.getTotalCost());
        assertEquals(0, cart.getTotalProducts());
        assertFalse(cart.getTotalProducts() > 0);
    }

    // Mutation Testings

    @Test
    void addProductMutation() {
        cart.addProduct(product1, 1);
        assertEquals(1, cart.getTotalProducts());
        assertEquals(1, cart.getProducts()[0].getQuantity());
        assertEquals(1, cart.getTotalCost());

        cart.addProduct(product1, 2);
        assertEquals(1, cart.getTotalProducts());
        assertEquals(3, cart.getProducts()[0].getQuantity());
        assertEquals(3, cart.getTotalCost());

        cart.addProduct(product2, 1);
        assertEquals(2, cart.getTotalProducts());
        assertEquals(1, cart.getProducts()[1].getQuantity());
        assertEquals(5, cart.getTotalCost());
    }

    @Test
    void removeProductMutation() {
        cart.addProduct(product1, 3);
        cart.addProduct(product2, 2);

        int removedQuantity = cart.removeProduct(product1, 1);
        assertEquals(1, removedQuantity);
        assertEquals(2, cart.getProducts()[0].getQuantity());
        assertEquals(6, cart.getTotalCost());

        removedQuantity = cart.removeProduct(product1, 2);
        assertEquals(2, removedQuantity);
        assertEquals(1, cart.getTotalProducts());
        assertEquals(4, cart.getTotalCost());

        removedQuantity = cart.removeProduct(product2, 2);
        assertEquals(2, removedQuantity);
        assertEquals(0, cart.getTotalProducts());
        assertEquals(0, cart.getTotalCost());
    }

    @Test
    void getTotalCostMutation() {
        cart.addProduct(product1, 1);
        cart.addProduct(product2, 2);
        assertEquals(5, cart.getTotalCost());
        assertNotEquals(0, cart.getTotalCost());
    }

    @Test
    void clearCartMutation() {
        cart.addProduct(product1, 1);
        cart.addProduct(product2, 2);
        cart.clearCart();
        assertEquals(0, cart.getTotalProducts());
        assertEquals(0, cart.getTotalCost());
        assertEquals(0, cart.getTotalProducts());
        assertFalse(cart.getTotalProducts() > 0);
    }
}
