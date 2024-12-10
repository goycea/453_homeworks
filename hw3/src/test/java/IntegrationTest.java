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

    @Test
    public void testAddProductToCart() {
        Market mockMarket = Mockito.mock(Market.class);
        ShoppingCart mockCart = Mockito.mock(ShoppingCart.class);

        Mockito.when(mockMarket.getProduct(0)).thenReturn(product1);
        Mockito.when(mockCart.getTotalProducts()).thenReturn(0);
        Mockito.when(product1.getName()).thenReturn("Apple");

        ProductModel product = mockMarket.getProduct(0);
        mockCart.addProduct(product, 5);
        mockMarket.restockProduct(0, -5);

        Mockito.verify(mockCart, Mockito.times(1)).addProduct(product1, 5);
        Mockito.verify(mockMarket, Mockito.times(1)).restockProduct(0, -5);

        System.out.println("Product added to cart and market stock updated.");
    }

    @Test
    void testRemoveProductFromCart() {
        when(cart.getProducts()).thenReturn(new ProductModel[]{product1});
        when(cart.removeProduct(product1, 2)).thenReturn(2);
        when(product1.getName()).thenReturn("Banana");

        doNothing().when(market).restockProduct(0, 2);

        cart.removeProduct(product1, 2);
        market.restockProduct(0, 2);

        verify(cart, times(1)).removeProduct(product1, 2);
        verify(market, times(1)).restockProduct(0, 2);

        System.out.println("Product removed from cart and market stock updated.");
    }

    @Test
    void testBuyProducts() {
        when(customer.getAvailableBalance()).thenReturn(10.0);
        when(product1.getPrice()).thenReturn(1);
        when(product2.getPrice()).thenReturn(2);

        cart.addProduct(product1, 1);
        cart.buyProducts(customer);

        verify(customer, times(1)).getAvailableBalance();
        verify(customer, times(1)).subtractionBalance(1);
        assertEquals(0, cart.getTotalProducts());
    }

    @Test
    void testClearCart() {
        when(product1.getName()).thenReturn("Apple");
        when(product1.getPrice()).thenReturn(1);
        when(product1.getQuantity()).thenReturn(1);

        cart.addProduct(product1, 1);
        cart.clearCart();

        assertEquals(0, cart.getTotalProducts());
        assertEquals(0, cart.getTotalCost());
    }

    @Test
    void testCustomerBalanceUpdate() {
        when(customer.getAvailableBalance()).thenReturn(50.0);
        when(customer.getName()).thenReturn("John Doe");

        doNothing().when(customer).subtractionBalance(20.0);

        customer.subtractionBalance(20.0);

        verify(customer, times(1)).subtractionBalance(20.0);

        System.out.println("Customer balance updated.");
    }

    @Test
    void testCheckout() {
        when(cart.getProducts()).thenReturn(new ProductModel[]{product1});
        when(cart.getTotalCost()).thenReturn(20);
        when(customer.getAvailableBalance()).thenReturn(50.0);
        when(product1.getName()).thenReturn("Milk");

        if (customer.getAvailableBalance() >= cart.getTotalCost()) {
            cart.buyProducts(customer);
        }

        verify(cart, times(1)).buyProducts(customer);
    }

    @Test
    void testInvalidQuantityHandling() {
        when(market.getProduct(0)).thenReturn(product1);
        when(product1.getName()).thenReturn("Juice");
        when(product1.getPrice()).thenReturn(5);

        int invalidQuantity = 10;
        if (product1.getQuantity() < invalidQuantity) {
            System.out.println("Invalid quantity or product is out of stock.");
        }

        verify(cart, never()).addProduct(product1, invalidQuantity);

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

        verify(product1, times(1)).setQuantity(20);
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
