package training_selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;

import static org.junit.Assert.*;



public class ProductPage {

    public WebDriver driver;
    public WebDriverWait wait;


    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }


    @Test
    public void testEqualsPages() {
        driver.get("http://localhost/litecart/");
        WebElement homeDuck = driver.findElement(By.cssSelector("#box-campaigns li.product"));
        Duck homePageDuck = new Duck();
        homePageDuck.setName(homeDuck.findElement(By.className("name")).getAttribute("textContent"));
        homePageDuck.setPrice(homeDuck.findElement(By.cssSelector("s.regular-price")).getAttribute("textContent"));
        homePageDuck.setDiscountPrice(homeDuck.findElement(By.cssSelector("strong.campaign-price")).getAttribute("textContent"));

        driver.get("http://localhost/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1");
        Duck viewPageDuck = new Duck();
        viewPageDuck.setName(driver.findElement(By.cssSelector("h1.title")).getAttribute("textContent"));
        viewPageDuck.setPrice(driver.findElement(By.cssSelector("s.regular-price")).getAttribute("textContent"));
        viewPageDuck.setDiscountPrice(driver.findElement(By.cssSelector("strong.campaign-price")).getAttribute("textContent"));

        assertEquals(homePageDuck, viewPageDuck);
    }

    @Test
    public void priceDifference() {
        driver.get("http://localhost/litecart/");
        WebElement homeDuck = driver.findElement(By.cssSelector("#box-campaigns li.product"));

        double sizeHomePrice = Double.parseDouble(homeDuck.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size").replaceAll("px", ""));
        double sizeHomeDiscountPrice = Double.parseDouble(homeDuck.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size").replaceAll("px", ""));

        assertTrue(sizeHomeDiscountPrice > sizeHomePrice);

        driver.get("http://localhost/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1");

        double sizeViewPrice = Double.parseDouble(driver.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size").replaceAll("px", ""));
        double sizeViewDiscountPrice = Double.parseDouble(driver.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size").replaceAll("px", ""));

        assertTrue(sizeViewDiscountPrice > sizeViewPrice);
    }

    @Test
    public void priceColor() {
        driver.get("http://localhost/litecart/");
        WebElement homeDuck = driver.findElement(By.cssSelector("#box-campaigns li.product"));
        String rgbColorHomePrice = homeDuck.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        String rgbColorHomeDiscountPrice = homeDuck.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
        Color colorHomePrice = Color.fromString(rgbColorHomePrice);
        Color colorHomeDiscountPrice = Color.fromString(rgbColorHomeDiscountPrice);
        isGrey(colorHomePrice);
        isRed(colorHomeDiscountPrice);

        driver.get("http://localhost/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1");
        String rgbColorViewPrice = driver.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        String rgbColorViewDiscountPrice = driver.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
        Color colorViewPrice = Color.fromString(rgbColorViewPrice);
        Color colorViewDiscountPrice = Color.fromString(rgbColorViewDiscountPrice);
        isGrey(colorViewPrice);
        isRed(colorViewDiscountPrice);
    }

    private void isGrey(Color wrapperColor) {
        java.awt.Color color = wrapperColor.getColor();
        assertEquals(color.getRed(), color.getGreen());
        assertEquals(color.getRed(), color.getBlue());
        assertEquals(color.getGreen(), color.getBlue());
    }

    private void isRed(Color wrapperColor) {
        java.awt.Color color = wrapperColor.getColor();
        assertEquals(color.getGreen(), 0);
        assertEquals(color.getBlue(), 0);
    }

    @Test
    public void propertyLine() {
        driver.get("http://localhost/litecart/");
        WebElement homeDuck = driver.findElement(By.cssSelector("#box-campaigns li.product"));

        String weightDiscountPrice = homeDuck.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight");
        String linePrice = homeDuck.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration-line");

        assertTrue(isBold(weightDiscountPrice));
        assertTrue(isLineThrough(linePrice));

        driver.get("http://localhost/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1");

        String weightDiscountPrice2 = driver.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight");
        String linePrice2 = driver.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration-line");

        assertTrue(isBold(weightDiscountPrice2));
        assertTrue(isLineThrough(linePrice2));
    }

    private boolean isBold(String fontWeight) {
        return "bold".equals(fontWeight) || "bolder".equals(fontWeight) || Integer.parseInt(fontWeight) >= 700;
    }

    private boolean isLineThrough(String line) {
         return line.contains("line-through");
    }

        @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}