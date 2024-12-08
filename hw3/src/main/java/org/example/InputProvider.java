package org.example;

import java.util.Scanner;

public interface InputProvider {
    String getInput();


    public class ConsoleInputProvider implements InputProvider {
        private final Scanner scanner;

        public ConsoleInputProvider(Scanner scanner) {
            this.scanner = scanner;
        }

        @Override
        public String getInput() {
            return scanner.next();
        }
    }

    public class ShoppingCartManager {
        private static InputProvider inputProvider;
        private static Market market;
        private static ShoppingCart cart;

        public static void setInputProvider(InputProvider provider) {
            inputProvider = provider;
        }

        public static void addProductToCart(int productIndex) {
            System.out.println("Enter quantity to add");
            int quantity = getValidatedIntegerInput();
            ProductModel product = market.getProduct(productIndex);
            if (quantity > 0 && quantity <= product.getQuantity()) {
                if (cart.getTotalProducts() < 5) {
                    cart.addProduct(product, quantity);
                    market.restockProduct(productIndex, -quantity);
                } else {
                    System.out.println("Cart is full");
                    MyTimer.waitSecond(2);
                }
            } else {
                System.out.println("Invalid quantity or product is out of stock");
                MyTimer.waitSecond(2);
            }
        }

        private static int getValidatedIntegerInput() {
            while (true) {
                String input = inputProvider.getInput();
                try {
                    return Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                }
            }
        }
    }
}