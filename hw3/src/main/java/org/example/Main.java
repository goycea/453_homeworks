package org.example;

import java.util.Scanner;

public class Main {
    private static ShoppingCart cart = new ShoppingCart();
    private static Market market = new Market(20); // Assuming a market size of 10
    private static Scanner user = new Scanner(System.in);
    private static Customer customer;

    public static void main(String[] args) {
        initializeMarket();
        askUserBudget();
        while (true) {
            displayMenu();
            String response = user.next();
            if (response.equals("0")) {
                break;
            } else if (response.equals("1")) {
                showCart();
            } else {
                int productIndex = Integer.parseInt(response) - 2;
                if (productIndex >= 0 && productIndex < market.getProductCount()) {
                    addProductToCart(productIndex);
                } else {
                    System.out.println("There is no such product");
                    MyTimer.waitSecond(2);
                }
            }
        }
    }

    private static void initializeMarket() {
        // Product unit kg
        market.addProduct(new ProductModel("Apple", 1, 10, "kg"));
        market.addProduct(new ProductModel("Banana", 2, 20, "kg"));
        market.addProduct(new ProductModel("Orange", 3, 15, "kg"));
        market.addProduct(new ProductModel("Mango", 4, 5, "kg"));
        market.addProduct(new ProductModel("Pineapple", 5, 3, "kg"));

        // Product unit piece
        market.addProduct(new ProductModel("Bleach", 10, 10, "piece"));
        market.addProduct(new ProductModel("Toilet Paper", 5, 20, "piece"));
        market.addProduct(new ProductModel("Shampoo", 50, 5, "piece"));
        market.addProduct(new ProductModel("Soap", 20, 10, "piece"));
        market.addProduct(new ProductModel("Toothpaste", 30, 10, "piece"));

        // Product unit liter
        market.addProduct(new ProductModel("Milk", 20, 10, "liter"));
        market.addProduct(new ProductModel("Water", 5, 20, "liter"));
        market.addProduct(new ProductModel("Juice", 30, 5, "liter"));
        market.addProduct(new ProductModel("Soda", 10, 10, "liter"));
        market.addProduct(new ProductModel("Beer", 50, 3, "liter"));

        // Product unit pack
        market.addProduct(new ProductModel("Chips", 10, 10, "pack"));
        market.addProduct(new ProductModel("Chocolate", 5, 20, "pack"));
        market.addProduct(new ProductModel("Candy", 1, 50, "pack"));
        market.addProduct(new ProductModel("Gum", 2, 25, "pack"));
        market.addProduct(new ProductModel("Cookies", 10, 10, "pack"));
    }

    private static void askUserBudget() {
        System.out.println("Enter your budget");
        double budget = Double.parseDouble(user.next());
        customer = new Customer("John", budget);
    }

    private static void displayMenu() {
        System.out.println("Choose a product to add to cart or to see the cart or exit to stop");
        System.out.println("0. Exit");
        if (cart.getTotalProducts() == 0) {
            System.out.println("1. Show the Cart: There is no product in the cart");
        } else {
            System.out.println("1. Show the Cart: There is/are " + cart.getTotalProducts() + " product(s) in the cart");
        }
        for (int i = 0; i < market.getProductCount(); i++) {
            ProductModel product = market.getProduct(i);
            System.out.println((i + 2) + ". " + product.getName() + " - "  + product.getQuantity()+" "+ product.getUnit()+" stock"
                    +" - "+ product.getPrice() + "₺ for each " + product.getUnit());
        }
    }

    private static void showCart() {
        if (cart.getTotalProducts() == 0) {
            System.out.println("There is no product in the cart");
            MyTimer.waitSecond(2);
        } else {
            while (true) {
                System.out.println("Total cost: " + cart.getTotalCost());
                System.out.println("Choose a product to remove or exit to stop");
                System.out.println("0. Exit");
                System.out.println("1. Buy Products");
                ProductModel[] productsInCart = cart.getProducts();
                for (int i = 0; i < cart.getTotalProducts(); i++) {
                    System.out.println((i + 2) + ". " + productsInCart[i].getName() + " - "
                             + productsInCart[i].getQuantity()+ " " + productsInCart[i].getUnit()+" stock"+" - "
                            + productsInCart[i].getPrice() + "₺ for each " + productsInCart[i].getUnit());
                }
                String response = user.next();
                if (response.equals("0")) {
                    break;
                } else if (response.equals("1")) {
                    cart.buyProducts(customer);
                    break;
                } else {
                    int productIndex = Integer.parseInt(response) - 2;
                    if (productIndex >= 0 && productIndex < cart.getTotalProducts()) {
                        removeProductFromCart(productIndex);
                    } else {
                        System.out.println("There is no such product");
                        MyTimer.waitSecond(2);
                    }
                }
            }
        }
    }

    private static void addProductToCart(int productIndex) {
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

    private static void removeProductFromCart(int productIndex) {
        System.out.println("Enter quantity to remove");
        int quantity = getValidatedIntegerInput();
        ProductModel[] productsInCart = cart.getProducts();
        if (productIndex >= 0 && productIndex < cart.getTotalProducts()
                && productsInCart[productIndex].getQuantity() > 0 && quantity > 0) {
            int removedQuantity = cart.removeProduct(productsInCart[productIndex], quantity);
            market.restockProduct(market.findProductIndex(productsInCart[productIndex]),
                                  market.getProduct(market.findProductIndex(productsInCart[productIndex])).getQuantity() + removedQuantity);
        } else {
            System.out.println("Invalid quantity or product");
            MyTimer.waitSecond(2);
        }
    }

    private static int getValidatedIntegerInput() {
        while (true) {
            String input = user.next();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }
}
