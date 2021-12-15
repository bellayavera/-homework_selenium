package training_selenium.cucumber;

import io.cucumber.java8.En;
import training_selenium.app.Application;

import static junit.framework.TestCase.assertEquals;

public class TestCartStepdefs implements En {

    private static Application app = new Application();
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {app.quit();app = null;}));
    }

    public TestCartStepdefs() {
        When("I open the litecart and add {int} items to the cart", (Integer m) -> {
            app.addItemsToCart(m);
        });
        Then("There are {int} items in the cart", (Integer m) -> {
            assertEquals(app.countItemsInCart(), m);
        });
        When("^I open the cart page and remove all items from the cart$", () -> {
            app.removeAllItemsFromCart();
        });
        Then("There are {int} item in the cart", (Integer m) -> {
            assertEquals(app.countItemsInCart(), m);
        });
    }
}
