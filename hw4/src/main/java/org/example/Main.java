package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:/Users/alper/OneDrive/Masaüstü/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // PTT AVM
        driver.get("https://www.pttavm.com/apple-iphone-16-128-gb-siyah-ithalatci-garantili-p-1082892215?utm_source=akakce.com&utm_medium=fiyat-kiyaslama&utm_campaign=akakce&v=1.33");
        // get source code of the page
        String pageSource = driver.getPageSource();
        // find the price of the product
        int index = pageSource.indexOf("price");
        String price = pageSource.substring(index +8, index + 14);
        System.out.println("PTT AVM: " + price);

        Product ptt = new Product("iphone 16", Double.parseDouble(price), "PTT AVM");

        // n11
        driver.get("https://www.n11.com/urun/apple-iphone-16-128-gb-apple-turkiye-garantili-59257801?magaza=bittibitiyor&renk=deniz-mavisi&utm_source=comp_akakce&utm_medium=cpc&utm_campaign=akakce_genel");
        pageSource = driver.getPageSource();
        index = pageSource.indexOf("value");
        price = pageSource.substring(index+8 , index + 14);
        System.out.println("n11: " + price);

        Product n11 = new Product("iphone 16", Double.parseDouble(price), "n11");

        // Hepsiburada

        driver.get("https://www.hepsiburada.com/iphone-16-128gb-deniz-mavisi-p-HBCV00006Y4HFP?magaza=Hepsiburada");
        pageSource = driver.getPageSource();
        index = pageSource.indexOf("\"price\"");
        price = pageSource.substring(index + 9, index + 15);
        System.out.println("Hepsiburada: " + price);

        Product hepsiburada = new Product("iphone 16", Double.parseDouble(price), "Hepsiburada");


        // Compare the prices
        double average = (ptt.price + n11.price + hepsiburada.price) / 3;
        System.out.println("Average price: " + average);

        if (ptt.price < n11.price && ptt.price < hepsiburada.price) {
            System.out.println("Cheapest: " + ptt.Site);
        } else if (n11.price < ptt.price && n11.price < hepsiburada.price) {
            System.out.println("Cheapest: " + n11.Site);
        } else {
            System.out.println("Cheapest: " + hepsiburada.Site);
        }

        if (ptt.price > n11.price && ptt.price > hepsiburada.price) {
            System.out.println("Most expensive: " + ptt.Site);
        } else if (n11.price > ptt.price && n11.price > hepsiburada.price) {
            System.out.println("Most expensive: " + n11.Site);
        } else {
            System.out.println("Most expensive: " + hepsiburada.Site);
        }

        driver.quit();
    }
}
