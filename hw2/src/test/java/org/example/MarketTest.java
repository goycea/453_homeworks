package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarketTest {

    @Test
    void addProduct() {
        Market market = new Market(10);
        ProductModel product = new ProductModel("Apple", 5, 10, "kg");
        market.addProduct(product);
        assertEquals(1, market.getProductCount());
        assertEquals(product, market.getProduct(0));
    }

    @Test
    void getProduct() {
        Market market = new Market(10);
        ProductModel product = new ProductModel("Apple", 5, 10, "kg");
        market.addProduct(product);
        assertEquals(product, market.getProduct(0));
    }

    @Test
    void getProductCount() {
        Market market = new Market(10);
        ProductModel product1 = new ProductModel("Apple", 5, 10, "kg");
        ProductModel product2 = new ProductModel("Banana", 10, 20, "kg");
        market.addProduct(product1);
        market.addProduct(product2);
        assertEquals(2, market.getProductCount());
    }

    @Test
    void restockProduct() {
        Market market = new Market(10);
        ProductModel product = new ProductModel("Apple", 5, 10, "kg");
        market.addProduct(product);
        market.restockProduct(0, 10);
        assertEquals(20, market.getProduct(0).getQuantity());
    }

    @Test
    void findProductIndex() {
        Market market = new Market(10);
        ProductModel product1 = new ProductModel("Apple", 5,10, "kg");
        ProductModel product2 = new ProductModel("Banana", 10, 20, "kg");
        market.addProduct(product1);
        market.addProduct(product2);
        assertEquals(0, market.findProductIndex(product1));
        assertEquals(1, market.findProductIndex(product2));
    }
}
