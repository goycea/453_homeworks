import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    WebDriver driver;
    private String username;
    private String password;
    private boolean shouldLoginSucceed;

    public LoginTest(String username, String password, boolean shouldLoginSucceed) {
        this.username = username;
        this.password = password;
        this.shouldLoginSucceed = shouldLoginSucceed;
    }

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testLogin() {
        driver.get("file:///C:\\Users\\alper\\OneDrive\\Masaüstü\\AlperenGoyce\\Lessons\\Seng 453\\homeworks\\hw4\\src\\main\\java\\org\\example\\login.html"); // Change to your file location
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.tagName("button"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();

        if (shouldLoginSucceed) {
            Assert.assertTrue(driver.getCurrentUrl().contains("welcome.html"), "Login failed for correct credentials");
        } else {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            Assert.assertTrue(driver.switchTo().alert().getText().contains("Incorrect username or password"), "Alert not displayed for incorrect credentials");
        }
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
