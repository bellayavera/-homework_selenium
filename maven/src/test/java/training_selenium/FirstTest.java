package training_selenium;


import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;


public class FirstTest {


    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void FirstTest() {
        driver.get("http://www.google.com");
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}