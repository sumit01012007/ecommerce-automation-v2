package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void checkout() {

        // Click checkout (now it will work)
        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click();

        // Wait for checkout info page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));

        // Fill details
        driver.findElement(By.id("first-name")).sendKeys("Sumit");
        driver.findElement(By.id("last-name")).sendKeys("Kumar");
        driver.findElement(By.id("postal-code")).sendKeys("123456");

        // Continue
        wait.until(ExpectedConditions.elementToBeClickable(By.id("continue"))).click();

        // Wait for overview
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));

        // Finish
        wait.until(ExpectedConditions.elementToBeClickable(By.id("finish"))).click();

        System.out.println("Order completed");
    }

    public void logout() {

        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("react-burger-menu-btn"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("logout_sidebar_link"))).click();
    }
}