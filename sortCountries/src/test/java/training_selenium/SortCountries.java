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

import static org.junit.Assert.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

import java.util.ArrayList;
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
        List<WebElement> rows = driver.findElements(By.cssSelector("tr.row"));
        List<String> countries = new ArrayList<>();
        List<String> amountZones = new ArrayList<>();
        List<String> zones = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            WebElement row = rows.get(i);
            countries.add(row.findElement(By.cssSelector("td:nth-child(5)")).getText());
            amountZones.add(row.findElement(By.cssSelector("td:nth-child(6)")).getText());
            if (Integer.parseInt(amountZones.get(i)) > 0) {
                driver.findElements(By.cssSelector("td:nth-child(5) a")).get(i).click();
                List<WebElement> listEditCountry = driver.findElements(By.cssSelector("[id=table-zones] tr:not(tr:first-child,tr:last-child) td:nth-child(3)"));
                for (int j = 0; j < listEditCountry.size(); j++) {
                    zones.add(listEditCountry.get(j).getText());
                }
                List<String> sortZones = new ArrayList<>(zones);
                sortZones.sort(String.CASE_INSENSITIVE_ORDER);
                assertEquals(sortZones, zones);
                driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
                rows = driver.findElements(By.cssSelector("tr.row"));
                zones.clear();
            }
        }

        List<String> sortCountries = new ArrayList<>(countries);
        sortCountries.sort(String.CASE_INSENSITIVE_ORDER);
        assertEquals(sortCountries, countries);

    }

    @Test
    public void sortZones() {
        login();
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<WebElement> geoCountries = driver.findElements(By.cssSelector(".row td:nth-child(3) a"));
        List<String> geoZones = new ArrayList<>();
        for (int i = 0; i < geoCountries.size(); i++) {
            geoCountries.get(i).click();
            List<WebElement> zones = driver.findElements(By.cssSelector("#table-zones tbody tr:not(tr:first-child) td:nth-child(3) select"));
            for (int j = 0; j < zones.size(); j++) {
                Select select = new Select(zones.get(j));
                geoZones.add(select.getFirstSelectedOption().getText());
            }
            List<String> sortGeoZones = new ArrayList<>(geoZones);
            sortGeoZones.sort(String.CASE_INSENSITIVE_ORDER);
            assertEquals(sortGeoZones, geoZones);
            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            geoCountries = driver.findElements(By.cssSelector(".row td:nth-child(3) a"));
            geoZones.clear();
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