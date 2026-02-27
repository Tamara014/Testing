package com.automation.tests;

import com.automation.utils.PerformanceUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PerformanceTest extends BaseTest {
    @Test(description = "Measure and log page load performance time")
    public void testPageLoadPerformance() {
        driver.get("https://www.saucedemo.com/");
        long loadTime = PerformanceUtils.getPageLoadTime(driver);
        System.out.println("Page Load Time: " + loadTime + " ms");
        Assert.assertTrue(loadTime > 0, "Vreme učitavanja stranice mora biti pozitivno.");
    }
}
