import org.example.Customer;
import org.example.Market;
import org.example.ProductModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MarketMockTest {

    private Market market;
    private ProductModel product1;

    @BeforeEach
    void setUp() {
        market = new Market(10);
        product1 = mock(ProductModel.class);
    }

    @Test
    void testAddProduct() {
        when(product1.getName()).thenReturn("Apple");
        when(product1.getPrice()).thenReturn(5);
        when(product1.getQuantity()).thenReturn(10);
        when(product1.getUnit()).thenReturn("kg");

        market.addProduct(product1);

        // Verify product is added correctly by checking product count
        assertEquals(1, market.getProductCount());

        // Optionally, check if the product is indeed added to the array
        assertEquals(product1, market.getProduct(0));
    }

    @Test
    void testRestockProduct() {
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
