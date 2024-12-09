import org.example.Customer;
import org.example.ProductModel;
import org.example.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShoppingCartMockTest {

    private ShoppingCart cart;
    private ProductModel product1;
    private ProductModel product2;
    private Customer customer;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
        // Create real instances of ProductModel
        product1 = new ProductModel("Apple", 1, 1, "kg");
        product2 = new ProductModel("Banana", 2, 2, "kg");
        customer = mock(Customer.class);
    }

    @Test
    void testAddProduct() {
        cart.addProduct(product1, 1);

        // Verify the product is added by checking the total products in cart
        assertEquals(1, cart.getTotalProducts());

        // Verify that the total cost is updated
        assertEquals(1, cart.getTotalCost());
    }

    @Test
    void testRemoveProduct() {
        cart.addProduct(product1, 3);
        cart.addProduct(product2, 2);
        cart.removeProduct(product1, 1);

        // Verify the total products and total cost after removing
        assertEquals(2, cart.getTotalProducts());
        assertEquals(6, cart.getTotalCost());
    }

    @Test
    void testBuyProducts() {
        when(customer.getAvailableBalance()).thenReturn(10.0);

        cart.addProduct(product1, 1);
        cart.buyProducts(customer);

        verify(customer, times(1)).getAvailableBalance();
        verify(customer, times(1)).subtractionBalance(1);
        assertEquals(0, cart.getTotalProducts()); // Cart should be empty after purchase
    }

    @Test
    void testClearCart() {
        cart.addProduct(product1, 1);
        cart.clearCart();

        assertEquals(0, cart.getTotalProducts());
        assertEquals(0, cart.getTotalCost());
    }

    @Test
    void testFindProductIndex() {
        cart.addProduct(product1, 1);
        int index = cart.findProductIndex(product1);

        assertEquals(0, index);
    }
}
