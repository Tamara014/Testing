package com.automation.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

// Ova klasa sadrži metodu za merenje vremena učitavanja stranice.
public class PerformanceUtils {
    // Vraća vreme učitavanja stranice u milisekundama
    public static long getPageLoadTime(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long navigationStart = (long) js.executeScript("return window.performance.timing.navigationStart;");
        long loadEventEnd = (long) js.executeScript("return window.performance.timing.loadEventEnd;");
        return loadEventEnd - navigationStart;
    }
}
