package training_selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import java.util.List;
import java.util.NoSuchElementException;


public class FirstTest {

    public WebDriver driver;
    public WebDriverWait wait;


    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait (driver, 20);
    }


    @Test
    public void availableOfStickers() {
        driver.get("http://localhost/litecart/");
        wait.until(titleIs("Online Store | My Store"));
        List<WebElement> ducks = driver.findElements(By.cssSelector("li.product.column.shadow.hover-light"));
        for (int i = 0, duckSize = ducks.size(); i < duckSize; i++) {
            List<WebElement> stickers = ducks.get(i).findElements(By.cssSelector("div.sticker"));
            assertEquals(1, stickers.size());
        }
    }



    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}