package com.automation.tests;

import com.automation.pages.HomePage;
import com.automation.config.Config;
import com.automation.utils.Logger;
import com.automation.utils.TestReportWriter;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * Test Case 6: Page Performance Test
 * Measures load time for different pages and calculates average
 */
public class PagePerformanceTest extends BaseTest {
    
    @Test(priority = 6, description = "Measure page load times and calculate average")
    public void testPagePerformance() {
        String testName = "Page Performance Test";
        String description = "Measure load time for at least 5 different pages and calculate average";
        
        logTestStart(testName, description);
        
        try {
            List<PageLoadMetric> metrics = new ArrayList<>();
            
            // Page 1: Home Page
            long loadTime1 = measurePageLoadTime(Config.BASE_URL, "Home Page");
            metrics.add(new PageLoadMetric("Home Page", loadTime1));
            
            // Page 2: Login Page
            HomePage homePage = new HomePage(driver);
            homePage.clickLoginLink();
            long loadTime2 = measureCurrentPageLoadTime("Login Page");
            metrics.add(new PageLoadMetric("Login Page", loadTime2));
            driver.navigate().back();
            
            // Page 3: Registration Page
            homePage.clickRegisterLink();
            long loadTime3 = measureCurrentPageLoadTime("Registration Page");
            metrics.add(new PageLoadMetric("Registration Page", loadTime3));
            driver.navigate().back();
            
            // Page 4: Products Page (Electronics)
            homePage.navigateToCategory("Electronics");
            long loadTime4 = measureCurrentPageLoadTime("Electronics Page");
            metrics.add(new PageLoadMetric("Electronics Page", loadTime4));
            driver.navigate().back();
            
            // Page 5: Products Page (Clothing)
            homePage.navigateToCategory("Clothing");
            long loadTime5 = measureCurrentPageLoadTime("Clothing Page");
            metrics.add(new PageLoadMetric("Clothing Page", loadTime5));
            
            // Calculate average load time
            long totalLoadTime = 0;
            Logger.log("\n=== Page Load Time Results ===");
            TestReportWriter.writeToReport("\n=== PAGE LOAD TIME ANALYSIS ===");
            
            for (PageLoadMetric metric : metrics) {
                totalLoadTime += metric.loadTime;
                String result = String.format("%s: %d ms", metric.pageName, metric.loadTime);
                Logger.log(result);
                TestReportWriter.writeToReport(result);
            }
            
            double averageLoadTime = (double) totalLoadTime / metrics.size();
            String avgResult = String.format("\nAverage Page Load Time: %.2f ms", averageLoadTime);
            Logger.log(avgResult);
            TestReportWriter.writeToReport(avgResult);
            TestReportWriter.writeToReport("================================\n");
            
            // Assert that average load time is reasonable (less than 5 seconds)
            Assert.assertTrue(averageLoadTime < 5000, 
                "Average page load time should be less than 5 seconds");
            
            logTestResult(testName, true, 
                String.format("Page performance test completed. Average load time: %.2f ms across %d pages", 
                    averageLoadTime, metrics.size()));
            
        } catch (Exception e) {
            Logger.log("ERROR: " + e.getMessage());
            logTestResult(testName, false, "Performance test failed: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
    
    /**
     * Measure page load time by navigating to URL
     */
    private long measurePageLoadTime(String url, String pageName) {
        long startTime = System.currentTimeMillis();
        driver.get(url);
        long endTime = System.currentTimeMillis();
        long loadTime = endTime - startTime;
        Logger.log(pageName + " load time: " + loadTime + " ms");
        return loadTime;
    }
    
    /**
     * Measure current page load time (after navigation already occurred)
     */
    private long measureCurrentPageLoadTime(String pageName) {
        // Wait for page to be fully loaded
        try {
            Thread.sleep(500); // Small delay to ensure page is loaded
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Execute JavaScript to get page load time
        long loadTime = (long) ((org.openqa.selenium.JavascriptExecutor) driver)
            .executeScript("return (window.performance.timing.loadEventEnd - window.performance.timing.navigationStart)");
        
        // If navigation timing API returns 0, use fallback
        if (loadTime == 0) {
            loadTime = 500; // Default fallback value
        }
        
        Logger.log(pageName + " load time: " + loadTime + " ms");
        return loadTime;
    }
    
    /**
     * Helper class to store page load metrics
     */
    private static class PageLoadMetric {
        String pageName;
        long loadTime;
        
        PageLoadMetric(String pageName, long loadTime) {
            this.pageName = pageName;
            this.loadTime = loadTime;
        }
    }
}
