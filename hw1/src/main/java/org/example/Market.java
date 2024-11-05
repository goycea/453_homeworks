package org.example;

public class Market {
    ProductModel [] products;
    int productCount;
    public Market(int size) {
        products = new ProductModel[size];
        productCount = 0;
    }

    public void addProduct(ProductModel product) {
        products[productCount] = product;
        productCount++;
    }

    public ProductModel getProduct(int index) {
        return products[index];
    }

    public int getProductCount() {
        return productCount;
    }

    public void restockProduct(int index, int quantity) {
        products[index].setQuantity(quantity+ products[index].getQuantity());
    }

    public int findProductIndex(ProductModel product) {
        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().equals(product.getName())) {
                return i;
            }
        }
        return -1;

    }
}
