 package training_selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

 public class NewWindows {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void testOpenNewWindows() {
        login();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.cssSelector("#content div a")).click();
        List<WebElement> links = driver.findElements(By.cssSelector(".fa-external-link"));
        for (int i = 0; i < links.size(); i++) {
            String originalWindow = driver.getWindowHandle();
            Set<String> existingWindows = driver.getWindowHandles();
            links.get(i).click();
            String newWindow = wait.until(anyWindowOtherThan(existingWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(originalWindow);
        }
    }

     public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
         return new ExpectedCondition<String>() {
             public String apply(WebDriver driver) {
                 Set<String> handles=driver.getWindowHandles();
                 handles.removeAll(oldWindows);
                 return handles.size()>0 ? handles.iterator().next():null;
             }
         };
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





