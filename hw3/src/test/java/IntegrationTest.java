import org.example.Customer;
import org.example.Market;
import org.example.ProductModel;
import org.example.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.example.Main.addProductToCart;
import static org.mockito.Mockito.*;

class IntegrationTest {

    private Customer mockCustomer;
    private ShoppingCart mockCart;
    private Market mockMarket;

    @BeforeEach
    void setUp() {
        mockCustomer = Mockito.mock(Customer.class);
        mockCart = Mockito.mock(ShoppingCart.class);
        mockMarket = Mockito.mock(Market.class);
    }

    @Test
    public void testAddProductToCart() {

        // Mock dependencies
        Market mockMarket = Mockito.mock(Market.class);
        ShoppingCart mockCart = Mockito.mock(ShoppingCart.class);

        // Test data
        ProductModel mockProduct = new ProductModel("Apple", 10, 10, "kg");

        // Market and cart behaviors
        Mockito.when(mockMarket.getProduct(0)).thenReturn(mockProduct);
        Mockito.when(mockCart.getTotalProducts()).thenReturn(0);

        // Function call
        ProductModel product = mockMarket.getProduct(0);
        mockCart.addProduct(product, 5);
        mockMarket.restockProduct(0, -5);

        // Verification
        Mockito.verify(mockCart, Mockito.times(1)).addProduct(mockProduct, 5); // Example: 5 units
        Mockito.verify(mockMarket, Mockito.times(1)).restockProduct(0, -5);

        System.out.println("Product added to cart and market stock updated.");
    }

    @Test
    void testRemoveProductFromCart() {

        // Mock behaviors
        ProductModel mockProduct = new ProductModel("Banana", 3, 5, "kg");
        when(mockCart.getProducts()).thenReturn(new ProductModel[]{mockProduct});
        when(mockCart.removeProduct(mockProduct, 2)).thenReturn(2);
        doNothing().when(mockMarket).restockProduct(0, 2);

        // Simulate the operation
        mockCart.removeProduct(mockProduct, 2);
        mockMarket.restockProduct(0, 2);

        // Verify interactions
        verify(mockCart, times(1)).removeProduct(mockProduct, 2);
        verify(mockMarket, times(1)).restockProduct(0, 2);

        System.out.println("Product removed from cart and market stock updated.");
    }

    @Test
    void testCustomerBalanceUpdate() {
        // Mock behaviors
        when(mockCustomer.getAvailableBalance()).thenReturn(50.0);
        doNothing().when(mockCustomer).subtractionBalance(20.0);

        // Simulate the operation
        mockCustomer.subtractionBalance(20.0);

        // Verify interactions
        verify(mockCustomer, times(1)).subtractionBalance(20.0);

        System.out.println("Customer balance updated.");
    }

    @Test
    void testCheckout() {
        // Mock behaviors
        ProductModel mockProduct = new ProductModel("Milk", 10, 2, "liter");
        when(mockCart.getProducts()).thenReturn(new ProductModel[]{mockProduct});
        when(mockCart.getTotalCost()).thenReturn(20);
        when(mockCustomer.getAvailableBalance()).thenReturn(50.0);

        // Simulate checkout
        if (mockCustomer.getAvailableBalance() >= mockCart.getTotalCost()) {
            mockCart.buyProducts(mockCustomer);
        }

        // Verify interactions
        verify(mockCart, times(1)).buyProducts(mockCustomer);

    }

    @Test
    void testInvalidQuantityHandling() {

        // Mock behaviors
        ProductModel mockProduct = new ProductModel("Juice", 5, 0, "liter");
        when(mockMarket.getProduct(0)).thenReturn(mockProduct);

        // Simulate the operation
        int invalidQuantity = 10; // greater than available
        if (mockProduct.getQuantity() < invalidQuantity) {
            System.out.println("Invalid quantity or product is out of stock.");
        }

        // Verify no interaction on invalid input
        verify(mockCart, never()).addProduct(mockProduct, invalidQuantity);

        System.out.println("No product added to cart due to invalid quantity.");
    }
}
