package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URL;

public class DriverFactory {

    public static WebDriver initDriver() {
        try {
            ChromeOptions options = new ChromeOptions();

            // Docker-friendly options
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");

            int retries = 3;

            while (retries > 0) {
                try {
                    return new RemoteWebDriver(
                            new URL("http://selenium:4444/wd/hub"),
                            options
                    );
                } catch (Exception e) {
                    System.out.println("Waiting for Selenium to be ready...");
                    Thread.sleep(3000);
                    retries--;
                }
            }

            throw new RuntimeException("Selenium not reachable");

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize driver", e);
        }
    }
}