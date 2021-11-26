package training_selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import java.util.List;
import java.util.NoSuchElementException;


public class FirstTest {

    public WebDriver driver;
    public WebDriverWait wait;

    boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait (driver, 20);
    }

    @Test
    public void testLogin() {
        login();
    }

    @Test
    public void testReadTable() {
        login();
        List<WebElement> rows = driver.findElements(By.id("app-"));
        for (int i = 0, rowsSize = rows.size(); i < rowsSize; i++) {
            rows.get(i).click();
            rows = driver.findElements(By.id("app-"));
            assertTrue(isElementPresent(driver, By.tagName("h1")));
            List<WebElement> list = rows.get(i).findElements(By.tagName("li"));
            for (int j = 1; j < list.size(); j++) {
                list.get(j).click();
                rows = driver.findElements(By.id("app-"));
                list = rows.get(i).findElements(By.tagName("li"));
                assertTrue(isElementPresent(driver, By.tagName("h1")));
            }
        }
    }

    private void login() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}