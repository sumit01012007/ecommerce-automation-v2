package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {

    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    By addToCart = By.id("add-to-cart-sauce-labs-backpack");
    By cartBtn = By.className("shopping_cart_link");

    public void addProductToCart() throws InterruptedException {

        // Add product
        driver.findElement(addToCart).click();

        Thread.sleep(1000); // wait for cart update

        // 🔥 IMPORTANT: go to cart page
        driver.findElement(cartBtn).click();

        Thread.sleep(1000); // wait for cart page
    }
}