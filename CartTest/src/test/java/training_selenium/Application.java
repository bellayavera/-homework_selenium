package training_selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Application {
    private WebDriverWait wait;
    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    
    public Application(){
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        wait = new WebDriverWait(driver,10);
    }

    public void quit() {
        driver.quit();
    }

    public void addAndRemoveProductsFromCart() {
        int n = 3;
        homePage.open();
        for (int i = 0; i < 3; i++) {
            homePage.addElementsTitlesInList();
            homePage.getElement().click();
            if (productPage.isSelectPresent()) {
                productPage.selectProperty();
            }
            productPage.addInCart(i);
            if (i == 2) {
                break;
            }
            homePage.open();
            for (int k = 0; k < i + 1; k++) {
                if (homePage.isEqualElementPresent(k)) {
                    n--;
                    break;
                }
            }
        }
        cartPage.openCart();
        for (int j = 0; j < n; j++) {
            cartPage.removeProduct();
        }
    }
}

