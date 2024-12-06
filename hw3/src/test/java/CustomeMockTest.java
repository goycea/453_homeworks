import org.example.Customer;
import org.example.Market;
import org.example.ProductModel;
import org.example.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerMockTest {

    private Market market;
    private ProductModel product1;
    private ShoppingCart shoppingCart;
    private Customer customer;

    @BeforeEach
    void setUp() {
        market = new Market(10);
        product1 = mock(ProductModel.class);
        customer = mock(Customer.class);
        shoppingCart = new ShoppingCart();
    }

    @Test
    void testCustomerAddProductToCart() {
        when(product1.getName()).thenReturn("Apple");
        when(product1.getPrice()).thenReturn(5);
        when(product1.getQuantity()).thenReturn(10);
        when(product1.getUnit()).thenReturn("kg");

        market.addProduct(product1);
        shoppingCart.addProduct(product1, 2);

        verify(product1, times(1)).getName();
        verify(product1, times(1)).getPrice();
        verify(product1, times(1)).getQuantity();
    }

    @Test
    void testCustomerInsufficientBalance() {
        when(product1.getName()).thenReturn("Apple");
        when(product1.getPrice()).thenReturn(5);
        when(product1.getQuantity()).thenReturn(10);
        when(product1.getUnit()).thenReturn("kg");
        when(customer.getAvailableBalance()).thenReturn(5.0);

        market.addProduct(product1);
        shoppingCart.addProduct(product1, 2);
        shoppingCart.buyProducts(customer);

        verify(customer, times(5)).getAvailableBalance();
        verify(customer, times(0)).subtractionBalance(anyDouble());
    }


    @Test
    void testCustomerRemoveProductFromCart() {
        when(product1.getName()).thenReturn("Apple");
        when(product1.getPrice()).thenReturn(5);
        when(product1.getQuantity()).thenReturn(10);
        when(product1.getUnit()).thenReturn("kg");

        market.addProduct(product1);
        shoppingCart.addProduct(product1, 2);
        shoppingCart.removeProduct(product1, 1);

        verify(product1, times(2)).getQuantity();
    }

    @Test
    void testCustomerClearCart() {
        when(product1.getName()).thenReturn("Apple");
        when(product1.getPrice()).thenReturn(5);
        when(product1.getQuantity()).thenReturn(10);
        when(product1.getUnit()).thenReturn("kg");

        market.addProduct(product1);
        shoppingCart.addProduct(product1, 2);
        shoppingCart.clearCart();

        assertEquals(0, shoppingCart.getTotalProducts());
        assertEquals(0, shoppingCart.getTotalCost());
    }
}
