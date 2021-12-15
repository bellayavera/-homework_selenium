package training_selenium.tests;

import org.junit.Before;
import org.junit.Test;
import training_selenium.app.Application;

import static junit.framework.TestCase.assertEquals;


public class CartTest {

   public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
   public Application app;

    @Before
    public void start() {
        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }
        app = new Application();
        tlApp.set(app);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            app.quit();
            app = null;
        }));
    }

    @Test
    public void testCart() {
        app.addItemsToCart(3);
        assertEquals(app.countItemsInCart(), Integer.valueOf(3));
        app.removeAllItemsFromCart();
        assertEquals(app.countItemsInCart(), Integer.valueOf(0));
    }
 }















