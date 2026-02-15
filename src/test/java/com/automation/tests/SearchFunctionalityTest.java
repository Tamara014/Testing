package com.automation.tests;

import com.automation.pages.HomePage;
import com.automation.pages.ProductPage;
import com.automation.utils.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Additional Test Case 1: Search Functionality Test
 * Validates product search functionality
 */
public class SearchFunctionalityTest extends BaseTest {
    
    @Test(priority = 8, description = "Test search functionality with various queries",
          dependsOnMethods = {"com.automation.tests.UserLoginTest.testUserLogin"})
    public void testSearchFunctionality() {
        String testName = "Search Functionality Test";
        String description = "Test product search with valid queries and verify results";
        
        logTestStart(testName, description);
        
        try {
            HomePage homePage = new HomePage(driver);
            ProductPage productPage = new ProductPage(driver);
            
            // Test Case 1: Search for "laptop"
            Logger.log("Searching for: laptop");
            homePage.searchProduct("laptop");
            Thread.sleep(1000); // Wait for search results
            
            int laptopResults = productPage.getSearchResultCount();
            Logger.log("Search results for 'laptop': " + laptopResults);
            Assert.assertTrue(laptopResults > 0, "Search for 'laptop' should return results");
            
            // Navigate back to home
            driver.get(com.automation.config.Config.BASE_URL);
            
            // Test Case 2: Search for "shirt"
            Logger.log("Searching for: shirt");
            homePage.searchProduct("shirt");
            Thread.sleep(1000); // Wait for search results
            
            int shirtResults = productPage.getSearchResultCount();
            Logger.log("Search results for 'shirt': " + shirtResults);
            Assert.assertTrue(shirtResults > 0, "Search for 'shirt' should return results");
            
            // Navigate back to home
            driver.get(com.automation.config.Config.BASE_URL);
            
            // Test Case 3: Search for non-existent product
            Logger.log("Searching for: xyzabc123notfound");
            homePage.searchProduct("xyzabc123notfound");
            Thread.sleep(1000); // Wait for search results
            
            boolean noResultsMessageDisplayed = productPage.isNoResultsMessageDisplayed();
            Logger.log("No results message displayed: " + noResultsMessageDisplayed);
            
            logTestResult(testName, true, 
                "Search functionality working correctly. Tested multiple queries successfully.");
            
        } catch (Exception e) {
            Logger.log("ERROR: " + e.getMessage());
            logTestResult(testName, false, "Search functionality test failed: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
