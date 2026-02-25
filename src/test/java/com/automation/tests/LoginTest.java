package com.automation.tests;

import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test(description = "Valid user login")
    public void testValidLogin() {
        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Login failed or inventory page not loaded.");
    }

    @Test(description = "Invalid login")
    public void testInvalidLogin() {
        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("wrong_password");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.getErrorMessage().contains("Epic sadface"), "Error message not shown for invalid login.");
    }
}
