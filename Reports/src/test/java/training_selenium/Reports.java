 package training_selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

 public class Reports {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void testBrowserLogs() {
        login();
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=2");
        List<WebElement> items = driver.findElements(By.cssSelector("tr td:nth-child(3) > a"));
        for (int i = 0; i < items.size(); i++) {
            items.get(i).click();
            if (driver.manage().logs().get("browser").getAll().size() == 0) {
                driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=2");}
                else break;
            items = driver.findElements(By.cssSelector("tr td:nth-child(3) > a"));
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





