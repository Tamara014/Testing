package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * CartPage - Page Object for shopping cart functionality
 */
public class CartPage extends BasePage {
    
    // Page elements
    @FindBy(xpath = "//tbody//tr[@class='cart-item-row']")
    private List<WebElement> cartItems;
    
    @FindBy(xpath = "//span[@class='value-summary']//strong")
    private WebElement totalPrice;
    
    @FindBy(xpath = "//td[@class='unit-price']//span")
    private List<WebElement> itemPrices;
    
    @FindBy(xpath = "//div[@class='page-title']/h1")
    private WebElement pageHeading;
    
    @FindBy(id = "termsofservice")
    private WebElement termsCheckbox;
    
    @FindBy(id = "checkout")
    private WebElement checkoutButton;
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public CartPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Gets the number of items in cart
     * @return Number of items
     */
    public int getCartItemCount() {
        try {
            if (driver.getPageSource().contains("Your Shopping Cart is empty!")) {
                return 0;
            }
            return cartItems.size();
        } catch (Exception e) {
            return 0;
        }
    }
    
    /**
     * Gets the total price displayed in cart
     * @return Total price as double
     */
    public double getTotalPrice() {
        try {
            String totalText = getElementText(totalPrice);
            return extractPrice(totalText);
        } catch (Exception e) {
            return 0.0;
        }
    }
    
    /**
     * Extracts numeric value from price string
     * @param priceString Price string (e.g., "$123.20")
     * @return Numeric value
     */
    private double extractPrice(String priceString) {
        try {
            // Remove currency symbols and extra characters
            String cleanPrice = priceString.replaceAll("[^0-9.]", "");
            return Double.parseDouble(cleanPrice);
        } catch (Exception e) {
            return 0.0;
        }
    }
    
    /**
     * Gets list of all individual product prices
     * @return List of prices
     */
    public List<Double> getIndividualProductPrices() {
        List<Double> prices = new ArrayList<>();
        
        try {
            List<WebElement> priceElements = driver.findElements(
                By.xpath("//td[@class='unit-price']//span"));
            
            for (WebElement priceElement : priceElements) {
                try {
                    String priceText = priceElement.getText();
                    double price = extractPrice(priceText);
                    prices.add(price);
                } catch (Exception e) {
                    // Skip this item if price extraction fails
                }
            }
        } catch (Exception e) {
            // Return empty list if extraction fails
        }
        
        return prices;
    }
    
    /**
     * Calculates sum of all individual product prices
     * @return Sum of prices
     */
    public double calculateExpectedTotal() {
        List<Double> prices = getIndividualProductPrices();
        double sum = 0.0;
        for (Double price : prices) {
            sum += price;
        }
        return sum;
    }
    
    /**
     * Gets the displayed total price as numeric value
     * @return Total price
     */
    public double getTotalPriceAsDouble() {
        return getTotalPrice();
    }
    
    /**
     * Verifies if cart page is loaded
     * @return true if on cart page
     */
    public boolean isOnCartPage() {
        try {
            String heading = getElementText(pageHeading);
            return heading.contains("Shopping Cart");
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Checks if cart is empty
     * @return true if cart is empty
     */
    public boolean isCartEmpty() {
        return driver.getPageSource().contains("Your shopping cart is empty!");
    }
}
