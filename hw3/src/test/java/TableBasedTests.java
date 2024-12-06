import org.example.ProductModel;
import org.example.ShoppingCart;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class TableBasedTests { //from removeProduct method.

     ShoppingCart cart = new ShoppingCart();

     @Test
     public void testIf_Added_Reduced_Removed() {
         // Tests the condition that product is added to the list, quantity is reduced and quantity is 0, product removed from the cart.
         ProductModel product = new ProductModel("Pencil", 500, 4, "unit");
         cart.addProduct(product, 5);

         int removedQuantity = cart.removeProduct(product, 5);

         assertEquals(5, removedQuantity);
         assertEquals(0, cart.getTotalProducts());
     }

     @Test
     public void testIf_Added_Reduced_NotRemoved() {
         // Tests the condition that product is added to the list, quantity is reduced and quantity removal is not 0, not removed from the cart.
         ProductModel product = new ProductModel("Notebook", 800, 7, "unit");
         cart.addProduct(product, 7);

         int removedQuantity = cart.removeProduct(product, 2);

         assertEquals(2, removedQuantity);
         assertEquals(1, cart.getTotalProducts());
         assertEquals(4000, cart.getTotalCost());
     }

     @Test
     public void testIf_Added_NotReduced_NotRemoved() {
         // Tests the condition that product is added to the list, quantity is invalid, quantity removal is not 0, not removed from the cart.
         ProductModel product = new ProductModel("Eraser", 200, 6, "unit");
         cart.addProduct(product, 6);

         int removedQuantity = cart.removeProduct(product, 8);

         assertEquals(0, removedQuantity);
         assertEquals(1, cart.getTotalProducts());
         assertEquals(1200, cart.getTotalCost());
     }

     @Test
     public void testIfProductNotFound() {
         // Tests the condition that product is not in the list.
         ProductModel product = new ProductModel("Glass", 500, 6, "unit");

         int removedQuantity = cart.removeProduct(product, 2);

         assertEquals(0, removedQuantity);
         assertEquals(0, cart.getTotalProducts());
     }



 }
