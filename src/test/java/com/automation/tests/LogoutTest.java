package com.automation.tests;

import com.automation.pages.HomePage;
import com.automation.pages.ProfilePage;
import com.automation.utils.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Additional Test Case 2: Logout Test
 * Validates user logout functionality
 */
public class LogoutTest extends BaseTest {
    
    @Test(priority = 9, description = "Test user logout functionality",
          dependsOnMethods = {"com.automation.tests.SearchFunctionalityTest.testSearchFunctionality"})
    public void testLogout() {
        String testName = "Logout Test";
        String description = "Verify user can successfully logout and session is terminated";
        
        logTestStart(testName, description);
        
        try {
            // Verify user is logged in by checking profile access
            HomePage homePage = new HomePage(driver);
            homePage.clickProfileLink();
            Logger.log("Accessed profile page - user is logged in");
            
            ProfilePage profilePage = new ProfilePage(driver);
            Thread.sleep(500);
            
            // Perform logout
            Logger.log("Attempting to logout...");
            profilePage.clickLogout();
            Thread.sleep(1000); // Wait for logout to complete
            
            // Verify logout successful - should redirect to home or login page
            boolean logoutSuccessful = homePage.isLoginLinkVisible();
            Logger.log("Logout successful: " + logoutSuccessful);
            
            // Verify user cannot access profile after logout
            Logger.log("Verifying user cannot access profile after logout");
            boolean profileAccessible = false;
            try {
                homePage.clickProfileLink();
                // If we can click profile, check if we're redirected to login
                Thread.sleep(1000);
                profileAccessible = !homePage.isOnLoginPage();
            } catch (Exception e) {
                // Expected - profile should not be accessible
                Logger.log("Profile not accessible after logout - as expected");
            }
            
            Assert.assertFalse(profileAccessible, "Profile should not be accessible after logout");
            Assert.assertTrue(logoutSuccessful, "Logout should be successful");
            
            logTestResult(testName, true, 
                "User logout completed successfully. Session terminated and profile inaccessible.");
            
        } catch (Exception e) {
            Logger.log("ERROR: " + e.getMessage());
            logTestResult(testName, false, "Logout test failed: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
