package com.example.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Test;

public class GettingStarted {
    @Test
    public void testGoogleSearch() throws InterruptedException {
        // Read headless flag from system property -Dheadless=true|false (default: true)
        String headlessProp = System.getProperty("headless", "true");
        boolean headless = Boolean.parseBoolean(headlessProp);

        ChromeOptions options = new ChromeOptions();
        if (headless) {
            // New headless mode flag for modern Chrome
            options.addArguments("--headless=new");
        }
        // Helpful options for CI/headless and stability
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = null;
        try {
            driver = new ChromeDriver(options);
            driver.get("https://www.google.com/");
            // Small pause to let page load (replace with WebDriverWait in real tests)
            Thread.sleep(1500);
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("ChromeDriver");
            searchBox.submit();
            Thread.sleep(1500);
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
