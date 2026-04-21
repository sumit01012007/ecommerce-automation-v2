package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void addProductToCart() {

        // Wait for product page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_container")));

        // Add product
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("add-to-cart-sauce-labs-backpack"))).click();

        System.out.println("Product added");

        // Click cart icon
        wait.until(ExpectedConditions.elementToBeClickable(
                By.className("shopping_cart_link"))).click();

        System.out.println("Clicked cart");

        // 🔥 IMPORTANT FIX: wait for URL instead of element
        wait.until(ExpectedConditions.urlContains("cart.html"));

        System.out.println("Cart page loaded");
    }
}