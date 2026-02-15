package com.automation.utils;

import com.automation.config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

/**
 * DriverManager class to manage WebDriver instance lifecycle
 * Implements ThreadLocal for parallel test execution support
 */
public class DriverManager {
    
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    /**
     * Initializes and returns WebDriver instance
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            // Setup ChromeDriver using WebDriverManager
            WebDriverManager.chromedriver().setup();
            
            // Configure ChromeOptions
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--remote-allow-origins=*");
            
            // Create ChromeDriver instance
            WebDriver webDriver = new ChromeDriver(options);
            
            // Set timeouts
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.IMPLICIT_WAIT));
            webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Config.PAGE_LOAD_TIMEOUT));
            
            driver.set(webDriver);
            Logger.info("WebDriver initialized successfully");
        }
        return driver.get();
    }
    
    /**
     * Quits and removes WebDriver instance
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
            Logger.info("WebDriver closed successfully");
        }
    }
    
    /**
     * Gets current driver without initializing if null
     * @return Current WebDriver instance or null
     */
    public static WebDriver getCurrentDriver() {
        return driver.get();
    }
}
