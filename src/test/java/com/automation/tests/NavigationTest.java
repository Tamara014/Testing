package com.automation.tests;

import com.automation.pages.HomePage;
import com.automation.utils.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Additional Test Case 3: Navigation Test
 * Validates navigation between different sections of the website
 */
public class NavigationTest extends BaseTest {
    
    @Test(priority = 10, description = "Test navigation across different website sections")
    public void testNavigation() {
        String testName = "Navigation Test";
        String description = "Verify navigation between Home, Categories, About, and Contact pages";
        
        logTestStart(testName, description);
        
        try {
            HomePage homePage = new HomePage(driver);
            
            // Test 1: Navigate to Electronics category
            Logger.log("Navigating to Electronics category");
            homePage.navigateToCategory("Electronics");
            Thread.sleep(500);
            String currentUrl = driver.getCurrentUrl();
            Logger.log("Current URL: " + currentUrl);
            Assert.assertTrue(currentUrl.contains("electronics") || currentUrl.contains("category"), 
                "Should navigate to Electronics category");
            
            // Test 2: Navigate back to Home
            Logger.log("Navigating back to Home");
            driver.get(com.automation.config.Config.BASE_URL);
            Thread.sleep(500);
            boolean isOnHome = homePage.isOnHomePage();
            Logger.log("On Home Page: " + isOnHome);
            Assert.assertTrue(isOnHome, "Should be on home page");
            
            // Test 3: Navigate to Clothing category
            Logger.log("Navigating to Clothing category");
            homePage.navigateToCategory("Clothing");
            Thread.sleep(500);
            currentUrl = driver.getCurrentUrl();
            Logger.log("Current URL: " + currentUrl);
            Assert.assertTrue(currentUrl.contains("clothing") || currentUrl.contains("category"), 
                "Should navigate to Clothing category");
            
            // Test 4: Navigate to About page
            Logger.log("Navigating to About page");
            driver.get(com.automation.config.Config.BASE_URL);
            homePage.clickAboutLink();
            Thread.sleep(500);
            currentUrl = driver.getCurrentUrl();
            Logger.log("Current URL: " + currentUrl);
            Assert.assertTrue(currentUrl.contains("about") || currentUrl.contains("company"), 
                "Should navigate to About page");
            
            // Test 5: Navigate back using browser back button
            Logger.log("Testing browser back navigation");
            driver.navigate().back();
            Thread.sleep(500);
            isOnHome = homePage.isOnHomePage();
            Logger.log("Returned to Home Page: " + isOnHome);
            Assert.assertTrue(isOnHome, "Should return to home page after back navigation");
            
            // Test 6: Navigate to Books category
            Logger.log("Navigating to Books category");
            homePage.navigateToCategory("Books");
            Thread.sleep(500);
            currentUrl = driver.getCurrentUrl();
            Logger.log("Current URL: " + currentUrl);
            Assert.assertTrue(currentUrl.contains("books") || currentUrl.contains("category"), 
                "Should navigate to Books category");
            
            Logger.log("All navigation tests completed successfully");
            
            logTestResult(testName, true, 
                "Navigation test completed. Successfully navigated between 5+ different pages/sections.");
            
        } catch (Exception e) {
            Logger.log("ERROR: " + e.getMessage());
            logTestResult(testName, false, "Navigation test failed: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
