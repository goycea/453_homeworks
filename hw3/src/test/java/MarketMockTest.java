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

        verify(product1, times(1)).getName();
        verify(product1, times(1)).getPrice();
        verify(product1, times(1)).getQuantity();
    }

    @Test
    void testRestockProduct() {
        when(product1.getName()).thenReturn("Apple");
        when(product1.getPrice()).thenReturn(5);
        when(product1.getQuantity()).thenReturn(10);
        when(product1.getUnit()).thenReturn("kg");

        market.addProduct(product1);
        market.restockProduct(0, 10);

        verify(product1, times(2)).getQuantity();
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
        verify(product1, times(1)).getName();
    }

}
