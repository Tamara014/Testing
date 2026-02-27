package com.automation.tests;

import com.automation.pages.RegistrationPage;
import com.automation.pages.ProfilePage;
import com.automation.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest {
    private WebDriver driver;
    private String firstName = "Test";
    private String lastName = "User";
    private String email = "testuser@example.com";
    private String password = "Test1234";

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver("chrome");
        driver.get("https://your-app-url.com/register"); // Prilagoditi URL
    }

    @Test(description = "Registracija korisnika i provera profila")
        public void testRegistrationAndProfile() {
            RegistrationPage regPage = new RegistrationPage(driver);
            regPage.enterFirstName(firstName);
            regPage.enterLastName(lastName);
            regPage.enterEmail(email);
            regPage.enterPassword(password);
            regPage.clickRegister();
            Assert.assertTrue(regPage.getSuccessMessage().contains("success"), "Registracija nije uspešna!");

            // Pretpostavlja se da nakon registracije korisnik prelazi na stranicu profila
            driver.get("https://your-app-url.com/profile"); // Prilagoditi URL
            ProfilePage profilePage = new ProfilePage(driver);
            Assert.assertEquals(profilePage.getFirstName(), firstName, "Ime nije identično!");
            Assert.assertEquals(profilePage.getLastName(), lastName, "Prezime nije identično!");
            Assert.assertEquals(profilePage.getEmail(), email, "Email nije identičan!");
        }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
