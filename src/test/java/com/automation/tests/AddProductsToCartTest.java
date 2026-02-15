package com.automation.tests;

import com.automation.pages.HomePage;
import com.automation.pages.ProductPage;
import com.automation.pages.CartPage;
import com.automation.utils.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Case 4: Add Products to Cart Test
 * Validates adding 3 products from different categories to cart
 */
public class AddProductsToCartTest extends BaseTest {
    
    @Test(priority = 4, description = "Add 3 products from different categories to cart",
          dependsOnMethods = {"com.automation.tests.UserLoginTest.testUserLogin"})
    public void testAddProductsToCart() {
        String testName = "Add Products to Cart Test";
        String description = "Add 3 products from different categories to the cart and verify all are added";
        
        logTestStart(testName, description);
        
        try {
            HomePage homePage = new HomePage(driver);
            ProductPage productPage = new ProductPage(driver);
            
            // Product 1: Electronics category
            Logger.log("Adding Product 1 from Electronics category");
            homePage.navigateToCategory("Electronics");
            productPage.selectProduct(1);
            productPage.addToCart();
            Logger.log("Product 1 added to cart");
            
            // Navigate back to home
            driver.navigate().back();
            driver.navigate().back();
            
            // Product 2: Clothing category
            Logger.log("Adding Product 2 from Clothing category");
            homePage.navigateToCategory("Clothing");
            productPage.selectProduct(2);
            productPage.addToCart();
            Logger.log("Product 2 added to cart");
            
            // Navigate back to home
            driver.navigate().back();
            driver.navigate().back();
            
            // Product 3: Books category
            Logger.log("Adding Product 3 from Books category");
            homePage.navigateToCategory("Books");
            productPage.selectProduct(1);
            productPage.addToCart();
            Logger.log("Product 3 added to cart");
            
            // Navigate to cart and verify
            homePage.clickCartLink();
            Logger.log("Navigated to cart page");
            
            CartPage cartPage = new CartPage(driver);
            int productCount = cartPage.getCartItemCount();
            
            Logger.log("Total products in cart: " + productCount);
            
            // Verify 3 products are in cart
            Assert.assertEquals(productCount, 3, "Cart should contain 3 products");
            
            logTestResult(testName, true, 
                "Successfully added 3 products from different categories to cart. Total items: " + productCount);
            
        } catch (Exception e) {
            Logger.log("ERROR: " + e.getMessage());
            logTestResult(testName, false, "Add to cart failed: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
