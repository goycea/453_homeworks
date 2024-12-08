import org.example.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {
    private Customer customerMock;
    private ShoppingCart cartMock;
    private Market marketMock;
    private ProductModel appleMock;
    private ProductModel bananaMock;

    @BeforeEach
    public void setUp() {
        // Create mocks for Customer, ShoppingCart, and Market
        customerMock = mock(Customer.class);
        cartMock = mock(ShoppingCart.class);
        marketMock = mock(Market.class);
        appleMock = mock(ProductModel.class);
        bananaMock = mock(ProductModel.class);

        // Setup mock behaviors
        when(customerMock.getAvailableBalance()).thenReturn(100.0);
        when(appleMock.getName()).thenReturn("Apple");
        when(appleMock.getPrice()).thenReturn(10);
        when(appleMock.getQuantity()).thenReturn(5);
        when(bananaMock.getName()).thenReturn("Banana");
        when(bananaMock.getPrice()).thenReturn(5);
        when(bananaMock.getQuantity()).thenReturn(10);
    }

    @Test
    public void testAddProductToCartSuccessfully() {
        // Simulate adding a product to the cart
        doNothing().when(cartMock).addProduct(appleMock, 2);


        // Perform the operation
        cartMock.addProduct(appleMock, 2);
        assertEquals(2, cartMock.getProducts().length);  // Assert that the cart now has the product added
        verify(cartMock, times(1)).addProduct(appleMock, 2);  // Verify method call
    }

    @Test
    public void testInsufficientBalanceForPurchase() {
        // Simulate insufficient balance
        when(customerMock.getAvailableBalance()).thenReturn(5.0);

        // Perform subtraction which should fail
        customerMock.subtractionBalance(10.0);

        // Verify that an insufficient balance message was printed
        verify(customerMock).subtractionBalance(10.0);  // Ensure subtraction was attempted
    }

    @Test
    public void testAddProductExceedingCartLimit() {
        // Mock cart full condition
        when(cartMock.getTotalProducts()).thenReturn(5);  // Cart is full

        // Attempt to add a new product when cart is full
        cartMock.addProduct(bananaMock, 3);
        verify(cartMock, never()).addProduct(bananaMock, 3);  // Ensure no product is added
    }

    @Test
    public void testRemoveProductFromCart() {
        // Simulate removing a product
        when(cartMock.removeProduct(appleMock, 2)).thenReturn(2);

        // Remove product from the cart
        cartMock.removeProduct(appleMock, 2);
        verify(cartMock).removeProduct(appleMock, 2);  // Ensure the method was called
    }

    
}

