import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Class_Test {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("hhttps://www.trendyol.com/elit-wheels/6-kol-dogan-slx-jant-takimi-14-inc-4x98-p-72749672?boutiqueId=61&merchantId=227037&sav=true");
 
        WebElement priceElement = driver.findElement(By.id("price-id"));
        String priceText = priceElement.getText();
        System.out.println("Fiyat: " + priceText);

        driver.quit();
    }
}
