package com.automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    // Ova klasa služi za kreiranje WebDriver objekta za Chrome ili Firefox.
    public static WebDriver getDriver(String browser) {
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "chrome":
                // Ručno postavljanje putanje do chromedriver.exe
                System.setProperty("webdriver.chrome.driver", "C:/Users/dvsd/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe"); // Ispravljena putanja
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Nepodržan browser: " + browser);
        }
        return driver;
    }
}
