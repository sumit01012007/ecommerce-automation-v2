package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

import pages.LoginPage;
import pages.ProductPage;
import pages.CheckoutPage;
import utils.DriverFactory;
import utils.ScreenshotUtil;

public class EcommerceTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = DriverFactory.initDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testEcommerceFlow() {

        // 1. Open website
        driver.get("https://www.saucedemo.com");

        // Wait for login page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));

        // 2. Login
        System.out.println("Step: Login");
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        // Wait for inventory page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));

        // 3. Screenshot product page
        ScreenshotUtil.takeScreenshot(driver, "product_page");

        // 4. Add product
        System.out.println("Step: Add product");
        ProductPage product = new ProductPage(driver);
        product.addProductToCart();

        // Wait for cart badge or next page element
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));

        // 5. Checkout
        System.out.println("Step: Checkout");
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.checkout();

        // Wait for confirmation page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));

        // 6. Screenshot success
        ScreenshotUtil.takeScreenshot(driver, "order_success");

        // 7. Logout
        System.out.println("Step: Logout");
        checkout.logout();

        // Wait for login page again
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));

        // 8. Screenshot logout
        ScreenshotUtil.takeScreenshot(driver, "logout_page");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}