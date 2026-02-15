package com.automation.tests;

import com.automation.pages.HomePage;
import com.automation.pages.RegistrationPage;
import com.automation.utils.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Case 1: User Registration Test
 * Validates user registration functionality with valid data
 */
public class UserRegistrationTest extends BaseTest {
    
    @Test(priority = 1, description = "User Registration with valid data")
    public void testUserRegistration() {
        String testName = "User Registration Test";
        String description = "Fill registration form with valid data and verify successful registration";
        
        logTestStart(testName, description);
        
        try {
            // Generate unique email for registration
            String timestamp = String.valueOf(System.currentTimeMillis());
            registeredEmail = "testuser" + timestamp + "@example.com";
            registeredPassword = "SecurePass123!";
            registeredFirstName = "John";
            registeredLastName = "Doe";
            registeredPhone = "1234567890";
            
            Logger.log("Generated test user credentials:");
            Logger.log("Email: " + registeredEmail);
            Logger.log("Name: " + registeredFirstName + " " + registeredLastName);
            
            // Navigate to registration page
            HomePage homePage = new HomePage(driver);
            homePage.clickRegisterLink();
            Logger.log("Navigated to registration page");
            
            // Fill registration form
            RegistrationPage registrationPage = new RegistrationPage(driver);
            registrationPage.fillRegistrationForm(
                registeredFirstName,
                registeredLastName,
                registeredEmail,
                registeredPassword,
                registeredPhone
            );
            Logger.log("Registration form filled with valid data");
            
            // Submit registration
            registrationPage.submitRegistration();
            Logger.log("Registration form submitted");
            
            // Verify registration success
            boolean isRegistrationSuccessful = registrationPage.isRegistrationSuccessful();
            
            // Assert and log result
            Assert.assertTrue(isRegistrationSuccessful, "Registration should be successful");
            
            logTestResult(testName, true, "User registration completed successfully. Email: " + registeredEmail);
            
        } catch (Exception e) {
            Logger.log("ERROR: " + e.getMessage());
            logTestResult(testName, false, "Registration failed: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
