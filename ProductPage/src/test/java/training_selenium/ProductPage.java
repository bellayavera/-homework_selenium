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



public class ProductPage {

    public WebDriver driver;
    public WebDriverWait wait;


    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait (driver, 20);
    }


    @Test
    public void testEqualsPages(){
        driver.get("http://localhost/litecart/");
        WebElement homeDuck = driver.findElement(By.cssSelector("#box-campaigns li.product"));
        Duck homePageDuck = new Duck();
        homePageDuck.setName(homeDuck.findElement(By.className("name")).getAttribute("textContent"));
        homePageDuck.setPrice(homeDuck.findElement(By.cssSelector("s.regular-price")).getAttribute("textContent"));
        homePageDuck.setDiscountPrice(homeDuck.findElement(By.cssSelector("strong.campaign-price")).getAttribute("textContent"));
        homePageDuck.setWeightPrice(homeDuck.findElement(By.cssSelector("s.regular-price")).getCssValue("font-weight"));
        homePageDuck.setWeightDiscountPrice(homeDuck.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight"));
        homePageDuck.setLineThroughPrice(homeDuck.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration-line"));
        homePageDuck.setLineThroughDiscountPrice(homeDuck.findElement(By.cssSelector("strong.campaign-price")).getCssValue("text-decoration-line"));


        driver.get("http://localhost/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1");
        Duck viewPageDuck = new Duck();
        viewPageDuck.setName(driver.findElement(By.cssSelector("h1.title")).getAttribute("textContent"));
        viewPageDuck.setPrice(driver.findElement(By.cssSelector("s.regular-price")).getAttribute("textContent"));
        viewPageDuck.setDiscountPrice(driver.findElement(By.cssSelector("strong.campaign-price")).getAttribute("textContent"));
        viewPageDuck.setWeightPrice(driver.findElement(By.cssSelector("s.regular-price")).getCssValue("font-weight"));
        viewPageDuck.setWeightDiscountPrice(driver.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight"));
        viewPageDuck.setLineThroughPrice(driver.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration-line"));
        viewPageDuck.setLineThroughDiscountPrice(driver.findElement(By.cssSelector("strong.campaign-price")).getCssValue("text-decoration-line"));

        assertEquals(homePageDuck, viewPageDuck);
    }

    @Test
    public void priceDifference() {
        driver.get("http://localhost/litecart/");
        WebElement homeDuck = driver.findElement(By.cssSelector("#box-campaigns li.product"));
        System.out.print(homeDuck.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size"));
        System.out.print(homeDuck.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size"));
        double sizeHomePrice = Double.parseDouble(homeDuck.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size").replaceAll("px",""));
        double sizeHomeDiscountPrice = Double.parseDouble(homeDuck.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size").replaceAll("px",""));

        assertTrue(sizeHomeDiscountPrice > sizeHomePrice);

        driver.get("http://localhost/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1");

        double sizeViewPrice = Double.parseDouble(driver.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size").replaceAll("px",""));
        double sizeViewDiscountPrice = Double.parseDouble(driver.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size").replaceAll("px",""));

        assertTrue(sizeViewDiscountPrice > sizeViewPrice);
    }

    @Test
    public void priceColor(){
        //цвета проверим на каждой из страниц по значениям RGB
        driver.get("http://localhost/litecart/");
        WebElement homeDuck = driver.findElement(By.cssSelector("#box-campaigns li.product"));
        String colorHomePrice =homeDuck.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        String colorHomeDiscountPrice = homeDuck.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");


        driver.get("http://localhost/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1");
        String colorViewPrice = driver.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        String colorViewDiscountPrice = driver.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
    }
        @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}