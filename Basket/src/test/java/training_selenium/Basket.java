 package training_selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import static org.openqa.selenium.support.ui.ExpectedConditions.*;

 public class Basket {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void testCart()  {
        int n = 3;
        List<String> elements = new ArrayList<>();
        driver.get("http://localhost/litecart/en/");
        for (int i = 0; i < n; i++) {
            WebElement element = driver.findElement(By.cssSelector("#box-most-popular li:nth-child(1) a.link"));
            elements.add(element.getAttribute("title"));
            element.click();
            if (isElementPresent(By.cssSelector("select"))) {
                driver.findElement(By.cssSelector("select")).click();
                driver.findElement(By.cssSelector("select option:nth-child(2)")).click();
            }
            driver.findElement(By.cssSelector("#box-product button")).click();
            wait.until(attributeContains(By.cssSelector("span.quantity"), "textContent", String.valueOf(i+1)));
            driver.get("http://localhost/litecart/en/");
            WebElement elementNew = driver.findElement(By.cssSelector("#box-most-popular li:nth-child(1) a.link"));
            String elementNewTitle = elementNew.getAttribute("title");
            for (int k = 0; k < i+1; k++) {
                if (Objects.equals(elements.get(k), elementNewTitle)) {
                    n++;
                    break;
                }
            }
        }
        driver.findElement(By.cssSelector("#cart a.link")).click();
        driver.findElement(By.cssSelector("li.shortcut:nth-child(1) a")).click();
        for (int j = 0; j < 3; j++) {
            WebElement table = wait.until(presenceOfElementLocated(By.cssSelector("#order_confirmation-wrapper")));
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(stalenessOf(table));
        }
        }


    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return  false;
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}





