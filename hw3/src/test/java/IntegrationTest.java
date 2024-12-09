import org.example.Customer;
import org.example.Market;
import org.example.ProductModel;
import org.example.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class IntegrationTest {

    private ProductModel product1;
    private ProductModel product2;
    private Customer customer;
    private ShoppingCart cart;
    private Market market;

    @BeforeEach
    void setUp() {
        customer = Mockito.mock(Customer.class);
        cart = Mockito.mock(ShoppingCart.class);
        market = Mockito.mock(Market.class);
        product1 = mock(ProductModel.class);
        product2 = mock(ProductModel.class);
    }

    //******************ShoppingCart Mock tests***********
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
        when(cart.getProducts()).thenReturn(new ProductModel[]{mockProduct});
        when(cart.removeProduct(mockProduct, 2)).thenReturn(2);
        doNothing().when(market).restockProduct(0, 2);

        // Simulate the operation
        cart.removeProduct(mockProduct, 2);
        market.restockProduct(0, 2);

        // Verify interactions
        verify(cart, times(1)).removeProduct(mockProduct, 2);
        verify(market, times(1)).restockProduct(0, 2);

        System.out.println("Product removed from cart and market stock updated.");
    }

    @Test
    void testBuyProducts() {
        cart = new ShoppingCart();
        product1 = new ProductModel("Apple", 1, 1, "kg");
        product2 = new ProductModel("Banana", 2, 2, "kg");

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

    //******************** Customer Mock Tests *********************

    @Test
    void testCustomerBalanceUpdate() {
        // Mock behaviors
        when(customer.getAvailableBalance()).thenReturn(50.0);
        doNothing().when(customer).subtractionBalance(20.0);

        // Simulate the operation
        customer.subtractionBalance(20.0);

        // Verify interactions
        verify(customer, times(1)).subtractionBalance(20.0);

        System.out.println("Customer balance updated.");
    }

    @Test
    void testCheckout() {
        // Mock behaviors
        ProductModel mockProduct = new ProductModel("Milk", 10, 2, "liter");
        when(cart.getProducts()).thenReturn(new ProductModel[]{mockProduct});
        when(cart.getTotalCost()).thenReturn(20);
        when(customer.getAvailableBalance()).thenReturn(50.0);

        // Simulate checkout
        if (customer.getAvailableBalance() >= cart.getTotalCost()) {
            cart.buyProducts(customer);
        }

        // Verify interactions
        verify(cart, times(1)).buyProducts(customer);

    }

    //******************** Market Mock Tests *********************

    @Test
    void testInvalidQuantityHandling() {

        // Mock behaviors
        ProductModel mockProduct = new ProductModel("Juice", 5, 0, "liter");
        when(market.getProduct(0)).thenReturn(mockProduct);

        // Simulate the operation
        int invalidQuantity = 10; // greater than available
        if (mockProduct.getQuantity() < invalidQuantity) {
            System.out.println("Invalid quantity or product is out of stock.");
        }

        // Verify no interaction on invalid input
        verify(cart, never()).addProduct(mockProduct, invalidQuantity);

        System.out.println("No product added to cart due to invalid quantity.");
    }

    @Test
    void testRestockProduct() {

        market = new Market(10);

        when(product1.getName()).thenReturn("Apple");
        when(product1.getPrice()).thenReturn(5);
        when(product1.getQuantity()).thenReturn(10);
        when(product1.getUnit()).thenReturn("kg");

        market.addProduct(product1);
        market.restockProduct(0, 10);

        // Verify quantity was updated
        verify(product1, times(1)).setQuantity(20); // assuming you have setQuantity implemented in ProductModel
    }

    @Test
    void testFindProductIndex() {
        when(product1.getName()).thenReturn("Apple");
        when(product1.getPrice()).thenReturn(5);
        when(product1.getQuantity()).thenReturn(10);
        when(product1.getUnit()).thenReturn("kg");

        market.addProduct(product1);
        int index = market.findProductIndex(product1);

        assertEquals(0, index);
    }


}
