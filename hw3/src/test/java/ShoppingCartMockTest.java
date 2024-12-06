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
        product1 = mock(ProductModel.class);
        product2 = mock(ProductModel.class);
        customer = mock(Customer.class);
    }

    @Test
    void testAddProduct() {
        when(product1.getName()).thenReturn("Apple");
        when(product1.getPrice()).thenReturn(1);
        when(product1.getQuantity()).thenReturn(1);
        when(product1.getUnit()).thenReturn("kg");

        cart.addProduct(product1, 1);

        verify(product1, times(1)).getName();
        verify(product1, times(1)).getPrice();
        verify(product1, times(1)).getQuantity();
    }

    @Test
    void testRemoveProduct() {
        when(product1.getName()).thenReturn("Apple");
        when(product1.getPrice()).thenReturn(1);
        when(product1.getQuantity()).thenReturn(3);
        when(product1.getUnit()).thenReturn("kg");

        cart.addProduct(product1, 3);
        cart.removeProduct(product1, 1);

        verify(product1, times(2)).getName();
        verify(product1, times(2)).getPrice();
        verify(product1, times(2)).getQuantity();
    }

    @Test
    void testBuyProducts() {
        when(product1.getName()).thenReturn("Apple");
        when(product1.getPrice()).thenReturn(1);
        when(product1.getQuantity()).thenReturn(1);
        when(product1.getUnit()).thenReturn("kg");
        when(customer.getAvailableBalance()).thenReturn(10.0);

        cart.addProduct(product1, 1);
        cart.buyProducts(customer);

        verify(customer, times(1)).getAvailableBalance();
        verify(customer, times(1)).subtractionBalance(1);
    }

    @Test
    void testClearCart() {
        when(product1.getName()).thenReturn("Apple");
        when(product1.getPrice()).thenReturn(1);
        when(product1.getQuantity()).thenReturn(1);
        when(product1.getUnit()).thenReturn("kg");

        cart.addProduct(product1, 1);
        cart.clearCart();

        assertEquals(0, cart.getTotalProducts());
        assertEquals(0, cart.getTotalCost());
    }

    @Test
    void testFindProductIndex() {
        when(product1.getName()).thenReturn("Apple");
        when(product1.getPrice()).thenReturn(1);
        when(product1.getQuantity()).thenReturn(1);
        when(product1.getUnit()).thenReturn("kg");

        cart.addProduct(product1, 1);
        int index = cart.findProductIndex(product1);

        assertEquals(0, index);
        verify(product1, times(1)).getName();
    }
}
