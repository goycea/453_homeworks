import org.example.Customer;
import org.example.Market;
import org.example.ProductModel;
import org.example.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomerMockTest {

    private Market market;
    private ProductModel product1;
    private ShoppingCart shoppingCart;
    private Customer customer;

    @BeforeEach
    void setUp() {
        market = new Market(10);
        product1 = mock(ProductModel.class); // Mock product
        customer = mock(Customer.class); // Mock customer
        shoppingCart = new ShoppingCart(); // Real instance of ShoppingCart
    }

    @Test
    void testCustomerAddProductToCart() {
        when(product1.getName()).thenReturn("Apple");
        when(product1.getPrice()).thenReturn(5);
        when(product1.getQuantity()).thenReturn(10);

        market.addProduct(product1);
        shoppingCart.addProduct(product1, 2);

        verify(product1, atLeastOnce()).getName();
        assertEquals(1, shoppingCart.getTotalProducts());
        assertEquals(2, shoppingCart.getProducts()[0].getQuantity());
        assertEquals(10, shoppingCart.getTotalCost());
    }

    @Test
    void testCustomerInsufficientBalance() {
        when(customer.getAvailableBalance()).thenReturn(5.0);

        shoppingCart.addProduct(product1, 2); // Add product to cart
        shoppingCart.buyProducts(customer);  // Attempt to buy products

        verify(customer, times(1)).getAvailableBalance(); // Expect one invocation
        assertEquals(5.0, customer.getAvailableBalance()); // Balance remains unchanged
    }


    @Test
    void testCustomerRemoveProductFromCart() {
        when(product1.getName()).thenReturn("Apple");
        when(product1.getPrice()).thenReturn(5);
        when(product1.getQuantity()).thenReturn(10);

        market.addProduct(product1);
        shoppingCart.addProduct(product1, 2);
        shoppingCart.removeProduct(product1, 1);

        verify(product1, atLeastOnce()).getName();
        assertEquals(1, shoppingCart.getTotalProducts());
        assertEquals(5, shoppingCart.getTotalCost());
    }


    @Test
    void testCustomerClearCart() {
        // Mock product behavior
        when(product1.getPrice()).thenReturn(5);

        // Add product to cart and then clear it
        market.addProduct(product1);
        shoppingCart.addProduct(product1, 2);
        shoppingCart.clearCart();

        // Assertions
        assertEquals(0, shoppingCart.getTotalProducts());
        assertEquals(0, shoppingCart.getTotalCost());
    }
}
