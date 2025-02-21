package id.ac.ui.cs.advprog.eshop.functional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

public class CreateProductFunctionalTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://your-webapp-url.com");
    }

    @Test
    void testCreateProduct() {
        WebElement nameInput = driver.findElement(By.id("product-name"));
        WebElement priceInput = driver.findElement(By.id("product-price"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        nameInput.sendKeys("New Product");
        priceInput.sendKeys("200");
        submitButton.click();

        WebElement productList = driver.findElement(By.id("product-list"));
        assertTrue(productList.getText().contains("New Product"), "New product should be visible in the list");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}

