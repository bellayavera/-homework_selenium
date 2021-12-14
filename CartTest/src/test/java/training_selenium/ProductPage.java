package training_selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class ProductPage extends Page{

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSelectPresent() {
        return isElementPresent(By.cssSelector("select"));
    }

    public void selectProperty(){
        driver.findElement(By.cssSelector("select")).click();
        driver.findElement(By.cssSelector("select option:nth-child(2)")).click();
    }

     public void addInCart(int i){
         driver.findElement(By.cssSelector("#box-product button")).click();
         wait.until(attributeContains(By.cssSelector("span.quantity"), "textContent", String.valueOf(i+1)));
     }


    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return  false;
        }
    }
}






