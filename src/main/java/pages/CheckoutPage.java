package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    By checkoutBtn = By.id("checkout");
    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By postalCode = By.id("postal-code");
    By continueBtn = By.id("continue");
    By finishBtn = By.id("finish");

    // VERY IMPORTANT (overview page indicator)
    By overviewPage = By.className("summary_info");

    // Logout
    By menuBtn = By.id("react-burger-menu-btn");
    By logoutBtn = By.id("logout_sidebar_link");

    // ============================
    // CHECKOUT FLOW
    // ============================
    public void checkout() {

        // Click checkout (on cart page)
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();

        // Fill details
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys("John");
        driver.findElement(lastName).sendKeys("Doe");
        driver.findElement(postalCode).sendKeys("12345");

        // Continue
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();

        // 🔥 WAIT for overview page (THIS FIXES YOUR ERROR)
        wait.until(ExpectedConditions.visibilityOfElementLocated(overviewPage));

        // Finish
        wait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
    }

    // ============================
    // LOGOUT FLOW
    // ============================
    public void logout() {

        wait.until(ExpectedConditions.elementToBeClickable(menuBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn)).click();
    }
}