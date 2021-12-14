package training_selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CartPage extends Page {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void openCart(){
        driver.findElement(By.cssSelector("#cart a.link")).click();
        driver.findElement(By.cssSelector("li.shortcut:nth-child(1) a")).click();
    }
    public void removeProduct(){
        WebElement table = wait.until(presenceOfElementLocated(By.cssSelector("#order_confirmation-wrapper")));
        driver.findElement(By.name("remove_cart_item")).click();
        wait.until(stalenessOf(table));
    }

    public Integer quantityProducts() {
        return Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getAttribute("textContent"));
    }

}




