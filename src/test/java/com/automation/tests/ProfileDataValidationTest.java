package com.automation.tests;

import com.automation.pages.HomePage;
import com.automation.pages.ProfilePage;
import com.automation.utils.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Case 3: Profile Data Validation Test
 * Verifies that user profile data matches registration data
 */
public class ProfileDataValidationTest extends BaseTest {
    
    @Test(priority = 3, description = "Validate profile data matches registration data",
          dependsOnMethods = {"com.automation.tests.UserLoginTest.testUserLogin"})
    public void testProfileDataValidation() {
        String testName = "Profile Data Validation Test";
        String description = "Verify that user profile data matches registration data";
        
        logTestStart(testName, description);
        
        try {
            // Navigate to profile page
            HomePage homePage = new HomePage(driver);
            homePage.clickProfileLink();
            Logger.log("Navigated to profile page");
            
            // Get profile data
            ProfilePage profilePage = new ProfilePage(driver);
            String profileFirstName = profilePage.getFirstName();
            String profileLastName = profilePage.getLastName();
            String profileEmail = profilePage.getEmail();
            String profilePhone = profilePage.getPhone();
            
            Logger.log("Retrieved profile data:");
            Logger.log("Name: " + profileFirstName + " " + profileLastName);
            Logger.log("Email: " + profileEmail);
            Logger.log("Phone: " + profilePhone);
            
            // Validate profile data against registration data
            boolean firstNameMatches = registeredFirstName.equals(profileFirstName);
            boolean lastNameMatches = registeredLastName.equals(profileLastName);
            boolean emailMatches = registeredEmail.equalsIgnoreCase(profileEmail);
            boolean phoneMatches = registeredPhone.equals(profilePhone);
            
            Logger.log("Validation Results:");
            Logger.log("First Name Match: " + firstNameMatches);
            Logger.log("Last Name Match: " + lastNameMatches);
            Logger.log("Email Match: " + emailMatches);
            Logger.log("Phone Match: " + phoneMatches);
            
            // Assert all fields match
            Assert.assertTrue(firstNameMatches, "First name should match registration data");
            Assert.assertTrue(lastNameMatches, "Last name should match registration data");
            Assert.assertTrue(emailMatches, "Email should match registration data");
            Assert.assertTrue(phoneMatches, "Phone should match registration data");
            
            boolean allDataMatches = firstNameMatches && lastNameMatches && emailMatches && phoneMatches;
            
            logTestResult(testName, allDataMatches, 
                "Profile data validation completed. All fields match: " + allDataMatches);
            
        } catch (Exception e) {
            Logger.log("ERROR: " + e.getMessage());
            logTestResult(testName, false, "Profile validation failed: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
