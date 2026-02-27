package com.automation.tests;

import com.automation.pages.LoginPage;
import com.automation.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {
    @Test(description = "Add products to cart")
    public void testAddProductsToCart() {
        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart(0);
        inventoryPage.addProductToCart(1);
        Assert.assertTrue(inventoryPage.getCartItemCount() == 2, "Broj proizvoda u korpi nije ispravan nakon dodavanja.");
    }

    @Test(description = "Remove products from cart")
    public void testRemoveProductsFromCart() {
        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart(0);
        inventoryPage.addProductToCart(1);
        inventoryPage.removeProductFromCart(0);
        Assert.assertTrue(inventoryPage.getCartItemCount() == 1, "Broj proizvoda u korpi nije ispravan nakon uklanjanja proizvoda.");
    }
}
