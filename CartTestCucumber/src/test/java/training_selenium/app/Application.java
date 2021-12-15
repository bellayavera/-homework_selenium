package training_selenium.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import training_selenium.pages.CartPage;
import training_selenium.pages.HomePage;
import training_selenium.pages.ProductPage;

public class Application {
    private final WebDriverWait wait;
    private final WebDriver driver;
    private final HomePage homePage;
    private final ProductPage productPage;
    private final CartPage cartPage;
    private int n = 0;

    public Application() {
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        wait = new WebDriverWait(driver, 10);
    }

    public void quit() {
        driver.quit();
    }


        public void addItemsToCart(int m){ // m - количество товаров,которые надо добавить
         n = m;// n - счетчик неодинаковых товаров (сколько раз нажмем на кнопку remove)
        homePage.open();
        for (int i = 0; i < m; i++) {
            homePage.addElementsTitlesInList();
            homePage.getElement().click();
            if (productPage.isSelectPresent()) {
                productPage.selectProperty();
            }
            productPage.addInCart(i);
            if (i == m-1) {
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

    }

    public void removeAllItemsFromCart() {
        cartPage.openCart();
        for (int j = 0; j < n; j++) {
            cartPage.removeProduct();
        }
    }

    public Integer countItemsInCart(){
        homePage.open();
        return cartPage.quantityProducts();
    }









}

