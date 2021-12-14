package training_selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HomePage extends Page {

    private final List<String> elements = new ArrayList<>();

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("http://localhost/litecart/en/");
    }

    public WebElement getElement() {
        return driver.findElement(By.cssSelector("#box-most-popular li:nth-child(1) a.link"));
    }

    private String getTitle() {
        return getElement().getAttribute("title");
    }

    public void addElementsTitlesInList() {
        elements.add(getTitle());
    }

    public boolean isEqualElementPresent(int k){
        return Objects.equals(elements.get(k),getTitle());
    }

}
