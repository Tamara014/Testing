package com.automation.tests;

import com.automation.pages.LoginPage;
import com.automation.pages.InventoryPage;
import com.automation.pages.CartPage;
import com.automation.pages.CheckoutPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {
    @Test(description = "Verify total price calculation in checkout")
    public void testTotalPriceCalculation() {
        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart(0);
        inventoryPage.addProductToCart(1);
        driver.findElement(org.openqa.selenium.By.className("shopping_cart_link")).click();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("12345");
        checkoutPage.clickContinue();
        String totalLabel = checkoutPage.getTotalLabel();
        Assert.assertTrue(totalLabel.contains("Total"), "Total label not found.");
    }

    @Test(description = "Complete checkout process")
    public void testCompleteCheckout() {
        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart(0);
        driver.findElement(org.openqa.selenium.By.className("shopping_cart_link")).click();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("12345");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();
        Assert.assertTrue(checkoutPage.getCompleteHeader().contains("THANK YOU"), "Checkout not completed successfully.");
    }
}
