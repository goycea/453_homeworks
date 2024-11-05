import org.example.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

        @Test
        void testGetName() {
            Customer customer = new Customer("Alice", 100.0);
            assertEquals("Alice", customer.getName(), "Customer name should be Alice");
        }

        @Test
        void testGetAvailableBalance() {
            Customer customer = new Customer("Alice", 100.0);
            assertEquals(100.0, customer.getAvailableBalance(), 0.001, "Balance should be 100.0");
        }

        @Test
        void testSetAvailableBalance() {
            Customer customer = new Customer("Alice", 100.0);
            customer.setAvailableBalance(200.0);
            assertEquals(200.0, customer.getAvailableBalance(), 0.001, "Balance should be updated to 200.0");
        }

        @Test
        void testSubtractionBalance_SufficientFunds() {
            Customer customer = new Customer("Alice", 100.0);
            customer.subtractionBalance(50.0);  // 50.0 should be subtracted
            assertEquals(50.0, customer.getAvailableBalance(), 0.001, "Balance should be 50.0");
        }

        @Test
        void testSubtractionBalance_InsufficientFunds() {
            Customer customer = new Customer("Alice", 50.0);
            customer.subtractionBalance(100.0);  // Should print "Insufficient balance" and not change balance
            assertEquals(50.0, customer.getAvailableBalance(), 0.001, "Balance should remain 50.0");
        }
    }
