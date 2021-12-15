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


import static junit.framework.TestCase.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

 public class Cart {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void testCart()  {
        int n = 3; // n - счетчик неодинаковых товаров (считает сколько раз нужно нажать кнопку remove в корзине)
        List<String> elements = new ArrayList<>();
        driver.get("http://localhost/litecart/en/");
        for (int i = 0; i < 3; i++) {
            WebElement element = driver.findElement(By.cssSelector("#box-most-popular li:nth-child(1) a.link"));
            elements.add(element.getAttribute("title"));
            element.click();
            if (isElementPresent(By.cssSelector("select"))) {
                driver.findElement(By.cssSelector("select")).click();
                driver.findElement(By.cssSelector("select option:nth-child(2)")).click();
            }
            //добавление товаров в корзину
            driver.findElement(By.cssSelector("#box-product button")).click();
            wait.until(attributeContains(By.cssSelector("span.quantity"), "textContent", String.valueOf(i+1)));
            if (i == 2) {
                break;
            }
            driver.get("http://localhost/litecart/en/");
            WebElement elementNew = driver.findElement(By.cssSelector("#box-most-popular li:nth-child(1) a.link"));
            String elementNewTitle = elementNew.getAttribute("title");
            for (int k = 0; k < i+1; k++) {
                if (Objects.equals(elements.get(k), elementNewTitle)) {
                    n--;
                    break;
                }
            }
        }
        //проверка что в корзине 3 товара
        assertEquals(Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getAttribute("textContent")), 3);

        //удаление товаров из корзины
        driver.findElement(By.cssSelector("#cart a.link")).click();
        driver.findElement(By.cssSelector("li.shortcut:nth-child(1) a")).click();
        for (int j = 0; j < n; j++) {
            WebElement table = wait.until(presenceOfElementLocated(By.cssSelector("#order_confirmation-wrapper")));
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(stalenessOf(table));
        }

        driver.get("http://localhost/litecart/en/");

        //проверка, что корзина пуста
        assertEquals(Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getAttribute("textContent")), 0);

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





