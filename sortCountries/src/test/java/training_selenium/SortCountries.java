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



public class SortCountries {

    public WebDriver driver;
    public WebDriverWait wait;



    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait (driver, 20);
    }



    @Test
    public void sortCountry() {
        login();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> countriesToSort = driver.findElements(By.cssSelector("td:nth-child(5)"));
        countriesToSort.sort((o1, o2) -> o1.getText().compareTo(o2.getText()));
        List<WebElement> countries = driver.findElements(By.cssSelector("td:nth-child(5)"));
        for (int i = 0; i < countries.size(); i++) {
            assertEquals(countries.get(i).getText(), countriesToSort.get(i).getText());
        }
        List<WebElement> countriesZones = driver.findElements(By.cssSelector("td:nth-child(6)"));
        for (int i = 0; i < countriesZones.size(); i++) {
            if (Integer.parseInt(countriesZones.get(i).getAttribute("textContent")) > 0) {
                driver.findElements(By.cssSelector("td:nth-child(5) a")).get(i).click();
                List<WebElement> editCountryListToSort = driver.findElements(By.cssSelector("[id=table-zones] tr:not(tr:first-child,tr:last-child) td:nth-child(3)"));
                editCountryListToSort.sort((o1, o2) -> o1.getText().compareTo(o2.getText()));
                List<WebElement> editCountryList = driver.findElements(By.cssSelector("[id=table-zones] tr:not(tr:first-child,tr:last-child) td:nth-child(3)"));
                for (int j = 0; j < editCountryList.size(); j++) {
                    assertEquals(editCountryList.get(j), editCountryListToSort.get(j));
                }
                driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
                countriesZones = driver.findElements(By.cssSelector("td:nth-child(6)"));
            }
        }
    }

    @Test
    public void sortZones() {
        login();
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<WebElement> countries = driver.findElements(By.cssSelector(".row td:nth-child(3)"));
        for (int i = 0; i < countries.size(); i++) {
            driver.findElements(By.cssSelector(".row td:nth-child(3) a")).get(i).click();
            List<WebElement> zonesToSort = driver.findElements(By.cssSelector("tr:not(tr:first-child) td:nth-child(3)"));
            zonesToSort.sort((o1, o2) -> o1.getText().compareTo(o2.getText()));
            List<WebElement> zones = driver.findElements(By.cssSelector("tr:not(tr:first-child) td:nth-child(3)"));
            for (int j = 0; j < zones.size(); j++) {
                assertEquals(zones.get(j).getText(), zonesToSort.get(i).getText());
            }
            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            countries = driver.findElements(By.cssSelector(".row td:nth-child(3)"));
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