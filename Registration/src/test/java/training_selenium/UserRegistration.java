 package training_selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;
import java.util.Random;

public class UserRegistration {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void registration(){
        //регистрация нового аккаунта
        driver.get("http://localhost/litecart/");
        driver.findElement(By.cssSelector("#box-account-login a")).click();
        driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(1) input[type=text]")).sendKeys("Ivan");
        driver.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(2) input[type=text]")).sendKeys("Ivanov");
        driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(1) input[type=text]")).sendKeys("3rd Builders Street, 25, apt. 12");
        driver.findElement(By.cssSelector("tr:nth-child(4) td:nth-child(1) input[type=text]")).sendKeys("12345");
        driver.findElement(By.cssSelector("tr:nth-child(4) td:nth-child(2) input[type=text]")).sendKeys("Saratov");

        WebElement selectElemCountry = driver.findElement(By.cssSelector("select.select2-hidden-accessible"));
        Select selectCountry = new Select(selectElemCountry);
        selectCountry.selectByVisibleText("United States");

        WebElement selectElemState =driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(2)"));
        selectElemState.click();
        selectElemState.findElement(By.cssSelector("option:nth-child(20)")).click();

        String email = getSaltString() + "@gmail.com";
        driver.findElement(By.cssSelector("input[type=email]")).sendKeys(email);
        driver.findElement(By.cssSelector("input[type=tel]")).sendKeys("88005553535");
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys("qwerty1234");
        driver.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys("qwerty1234");
        driver.findElement(By.cssSelector("button")).click();
        //выход из аккаунта
        driver.findElement(By.cssSelector("#box-account li:nth-child(4) a")).click();
        //вход в аккаунт
        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(email);
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys("qwerty1234");
        driver.findElement(By.cssSelector("#box-account-login button:nth-child(1)")).click();
        //выход из аккаунта
        driver.findElement(By.cssSelector("#box-account li:nth-child(4) a")).click();
    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}





