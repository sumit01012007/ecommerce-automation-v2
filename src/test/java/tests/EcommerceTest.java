package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;

import utils.DriverFactory;
import utils.ScreenshotUtil;
import pages.LoginPage;
import pages.ProductPage;
import pages.CheckoutPage;

public class EcommerceTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() throws InterruptedException {
        driver = DriverFactory.initDriver();
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(3000); // ⏱️ wait after opening site
    }

    @Test
    public void testEcommerceFlow() throws InterruptedException {

        // 1. Login
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");
        Thread.sleep(7000);

        // 2. Add product + go to cart
        ProductPage product = new ProductPage(driver);
        product.addProductToCart();
        Thread.sleep(7000);

        // 3. Checkout
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.checkout();
        Thread.sleep(7000);

        // 4. Screenshot after order
        ScreenshotUtil.takeScreenshot(driver, "order_success");
        System.out.println("Order placed successfully!");
        Thread.sleep(7000);

        // 5. Logout
        checkout.logout();
        Thread.sleep(7000);

        // 6. Screenshot after logout
        ScreenshotUtil.takeScreenshot(driver, "logout_page");
        System.out.println("Logout successful!");
        Thread.sleep(7000);
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(7000); // ⏱️ final wait before closing
        if (driver != null) {
            driver.quit();
        }
    }
}