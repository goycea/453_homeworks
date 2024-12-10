package org.example;

public class Customer {
    private final String name;
    private double availableBalance=0;

    public Customer(String name,double availableBalance) {
        this.name = name;
        this.availableBalance = availableBalance;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public void subtractionBalance(double amount) {
        if(amount > availableBalance) {
            System.out.println("Insufficient balance");
            System.out.println("Your available balance: " + availableBalance);
            System.out.println("Please remove some items from your cart");
        } else {
            availableBalance -= amount;
            System.out.println("Your available balance: " + availableBalance);
            System.out.println("Thank you for shopping with us");
        }
        MyTimer.waitSecond(2);
    }

    public String getName() {
        return name;
    }
}
