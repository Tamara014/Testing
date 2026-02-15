package com.automation.tests;

import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.utils.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Case 2: User Login Test
 * Validates user login functionality with registered credentials
 */
public class UserLoginTest extends BaseTest {
    
    @Test(priority = 2, description = "User Login with registered credentials", 
          dependsOnMethods = {"com.automation.tests.UserRegistrationTest.testUserRegistration"})
    public void testUserLogin() {
        String testName = "User Login Test";
        String description = "Login with previously registered credentials and verify successful login";
        
        logTestStart(testName, description);
        
        try {
            // Verify we have registered credentials
            Assert.assertNotNull(registeredEmail, "Email should be set from registration test");
            Assert.assertNotNull(registeredPassword, "Password should be set from registration test");
            
            Logger.log("Attempting login with email: " + registeredEmail);
            
            // Navigate to login page
            HomePage homePage = new HomePage(driver);
            homePage.clickLoginLink();
            Logger.log("Navigated to login page");
            
            // Perform login
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(registeredEmail, registeredPassword);
            Logger.log("Login credentials entered and submitted");
            
            // Verify login success
            boolean isLoginSuccessful = loginPage.isLoginSuccessful();
            
            // Assert and log result
            Assert.assertTrue(isLoginSuccessful, "Login should be successful with registered credentials");
            
            logTestResult(testName, true, "User login completed successfully with email: " + registeredEmail);
            
        } catch (Exception e) {
            Logger.log("ERROR: " + e.getMessage());
            logTestResult(testName, false, "Login failed: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
