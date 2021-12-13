 package training_selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class AddProduct {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void addNewProduct() throws URISyntaxException {
        login();
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        int items = driver.findElements(By.linkText("Chicken")).size();
        //заполнение вкладки General
        driver.findElement(By.cssSelector("#content div a:nth-child(2)")).click();
        driver.findElement(By.cssSelector("#tab-general label:nth-child(3)")).click();
        driver.findElement(By.cssSelector("#tab-general tr:nth-child(2) input[type=text]")).sendKeys("Chicken");
        driver.findElement(By.cssSelector("#tab-general tr:nth-child(3) input[type=text]")).sendKeys("rc001");
        driver.findElement(By.cssSelector("tr:nth-child(7) tr:nth-child(4)  input[type=checkbox]")).click();
        WebElement quantity = driver.findElement(By.cssSelector("tr:nth-child(8) td:nth-child(1) input[type=number]"));
        quantity.clear();
        quantity.sendKeys("5,00");
        //прикрепление файла
        URL resource = getClass().getClassLoader().getResource("chicken.jpg");
        assert resource != null;
        String path = Paths.get(resource.toURI()).toFile().getAbsolutePath();
        attachFile(driver, By.cssSelector("input[type=file]"), path);
        //выставление даты
        driver.findElement(By.cssSelector("tr:nth-child(10) input[type=date]")).sendKeys("10122021");
        driver.findElement(By.cssSelector("tr:nth-child(11) input[type=date]")).sendKeys("10122022");
        driver.findElement(By.cssSelector("#content li:nth-child(2) a")).click();


        //заполнение вкладки Information
        driver.findElement(By.cssSelector("#tab-information tr:nth-child(1) select")).click();
        driver.findElement(By.cssSelector("#tab-information option:nth-child(2)")).click();
        driver.findElement(By.cssSelector("#tab-information tr:nth-child(3) input[type=text]")).sendKeys("abc");
        driver.findElement(By.cssSelector("#tab-information tr:nth-child(4) input[type=text]")).sendKeys("abc");
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("abc");
        driver.findElement(By.cssSelector("#tab-information tr:nth-child(6) input[type=text]")).sendKeys("abc");
        driver.findElement(By.cssSelector("#tab-information tr:nth-child(7) input[type=text]")).sendKeys("abc");
        driver.findElement(By.cssSelector("#content li:nth-child(4) a")).click();


        //заполнение вкладки Prices
        WebElement price = driver.findElement(By.cssSelector("table:nth-child(2) input[type=number]"));
        price.clear();
        price.sendKeys("7,00");
        driver.findElement(By.cssSelector("#tab-prices table:nth-child(2) select")).click();
        driver.findElement(By.cssSelector("#tab-prices option:nth-child(2)")).click();
        driver.findElement(By.cssSelector("#tab-prices tr:nth-child(2) input[type=text]")).sendKeys("10");
        driver.findElement(By.cssSelector("#tab-prices tr:nth-child(3) input[type=text]")).sendKeys("7");
        driver.findElement(By.cssSelector("p button:nth-child(1)")).click();


        //проверка появления в каталоге
        int itemsNew = driver.findElements(By.linkText("Chicken")).size();
        assertEquals(1, (itemsNew - items));
    }

    private void login() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
    }

    public void unhide(WebDriver driver, WebElement element) {
        String script = "arguments[0].style.opacity=1;"
                + "arguments[0].style['transform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['MozTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['WebkitTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['msTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['OTransform']='translate(0px, 0px) scale(1)';"
                + "return true;";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    public void attachFile(WebDriver driver, By locator, String file) {
        WebElement input = driver.findElement(locator);
        unhide(driver, input);
        input.sendKeys(file);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}





