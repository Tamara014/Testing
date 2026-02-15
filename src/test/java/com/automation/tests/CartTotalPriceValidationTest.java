package com.automation.tests;

import com.automation.pages.HomePage;
import com.automation.pages.CartPage;
import com.automation.utils.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

/**
 * Test Case 5: Cart Total Price Validation Test
 * Verifies that total cart price equals sum of individual product prices
 */
public class CartTotalPriceValidationTest extends BaseTest {
    
    @Test(priority = 5, description = "Validate cart total price equals sum of individual prices",
          dependsOnMethods = {"com.automation.tests.AddProductsToCartTest.testAddProductsToCart"})
    public void testCartTotalPriceValidation() {
        String testName = "Cart Total Price Validation Test";
        String description = "Verify that total cart price equals sum of individual product prices";
        
        logTestStart(testName, description);
        
        try {
            // Navigate to cart
            HomePage homePage = new HomePage(driver);
            homePage.clickCartLink();
            Logger.log("Navigated to cart page");
            
            CartPage cartPage = new CartPage(driver);
            
            // Get individual product prices
            List<Double> productPrices = cartPage.getIndividualProductPrices();
            Logger.log("Retrieved " + productPrices.size() + " product prices");
            
            // Calculate sum of individual prices
            double calculatedTotal = 0.0;
            for (int i = 0; i < productPrices.size(); i++) {
                double price = productPrices.get(i);
                calculatedTotal += price;
                Logger.log("Product " + (i + 1) + " price: $" + String.format("%.2f", price));
            }
            
            Logger.log("Calculated total (sum of individual prices): $" + String.format("%.2f", calculatedTotal));
            
            // Get displayed total price
            double displayedTotal = cartPage.getTotalPrice();
            Logger.log("Displayed total price: $" + String.format("%.2f", displayedTotal));
            
            // Verify totals match (with tolerance for floating point comparison)
            double difference = Math.abs(displayedTotal - calculatedTotal);
            boolean pricesMatch = difference < 0.01; // Allow 1 cent difference for rounding
            
            Logger.log("Price difference: $" + String.format("%.2f", difference));
            Logger.log("Prices match: " + pricesMatch);
            
            // Assert prices match
            Assert.assertTrue(pricesMatch, 
                "Total price ($" + displayedTotal + ") should equal sum of individual prices ($" + calculatedTotal + ")");
            
            logTestResult(testName, true, 
                String.format("Cart total price validation successful. Calculated: $%.2f, Displayed: $%.2f", 
                    calculatedTotal, displayedTotal));
            
        } catch (Exception e) {
            Logger.log("ERROR: " + e.getMessage());
            logTestResult(testName, false, "Price validation failed: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
