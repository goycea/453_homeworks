package org.example;

import static org.junit.jupiter.api.Assertions.*;

class ProductModelTest {

    @org.junit.jupiter.api.Test
    void getName() {
        ProductModel product = new ProductModel("TestProduct", 100, 10, "unit");
        assertEquals("TestProduct", product.getName());    }

    @org.junit.jupiter.api.Test
    void getPrice() {
        ProductModel product = new ProductModel("TestProduct", 100, 10, "unit");
        assertEquals(100, product.getPrice());
    }

    @org.junit.jupiter.api.Test
    void getQuantity() {
        ProductModel product = new ProductModel("TestProduct", 100, 10, "unit");
        assertEquals(10, product.getQuantity());
    }

    @org.junit.jupiter.api.Test

    void getUnit() {
        ProductModel product = new ProductModel("TestProduct", 100, 10, "kg");
        assertEquals("kg", product.getUnit());
    }

    @org.junit.jupiter.api.Test
    void setQuantity() {
        ProductModel product = new ProductModel("TestProduct", 100, 10, "unit");
        product.setQuantity(20);
        assertEquals(20, product.getQuantity());
    }

    @org.junit.jupiter.api.Test
    void WrongGetName() {
        ProductModel product = new ProductModel("TestProduct", 100, 10, "unit");
        assertNotEquals("WrongProduct", product.getName());
    }

    @org.junit.jupiter.api.Test
    void WrongGetPrice() {
        ProductModel product = new ProductModel("TestProduct", 100, 10, "unit");
        assertNotEquals(200, product.getPrice());
    }

    @org.junit.jupiter.api.Test
    void WrongGetQuantity() {
        ProductModel product = new ProductModel("TestProduct", 100, 10, "unit");
        assertNotEquals(5, product.getQuantity());
    }

    @org.junit.jupiter.api.Test
    void WrongGetUnit() {
        ProductModel product = new ProductModel("TestProduct", 100, 10, "kg");
        assertNotEquals("litre", product.getUnit());
    }

    @org.junit.jupiter.api.Test
    void WrongSetQuantity() {
        ProductModel product = new ProductModel("TestProduct", 100, 10, "unit");
        product.setQuantity(20);
        assertNotEquals(10, product.getQuantity());
    }

    @org.junit.jupiter.api.Test
    void isPricePositive() {
        ProductModel product = new ProductModel("TestProduct", 100, 10, "unit");
        assertTrue(product.getPrice() > 0);
    }

    @org.junit.jupiter.api.Test
    void isUnitNotEmpty() {
        ProductModel product = new ProductModel("TestProduct", 100, 10, "kg");
        assertFalse(product.getUnit().isEmpty());
    }

    @org.junit.jupiter.api.Test
    void testSameInstance() {
        ProductModel product = new ProductModel("TestProduct", 100, 10, "unit");
        assertSame(product, product);
    }

    @org.junit.jupiter.api.Test
    void testDifferentInstance() {
        ProductModel product1 = new ProductModel("TestProduct", 100, 10, "unit");
        ProductModel product2 = new ProductModel("AnotherProduct", 200, 5, "unit");
        assertNotSame(product1, product2);
    }

    @org.junit.jupiter.api.Test
    void testProductModelNotNull() {
        ProductModel product = new ProductModel("TestProduct", 100, 10, "unit");
        assertNotNull(product);
    }


}
