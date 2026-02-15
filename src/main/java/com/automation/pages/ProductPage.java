package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * ProductPage - Page Object for product browsing and selection
 */
public class ProductPage extends BasePage {
    
    // Page elements
    @FindBy(xpath = "//div[@class='item-box']")
    private List<WebElement> productItems;
    
    @FindBy(xpath = "//button[text()='Add to cart']")
    private List<WebElement> addToCartButtons;
    
    @FindBy(xpath = "//div[@id='bar-notification']")
    private WebElement notificationBar;
    
    @FindBy(linkText = "shopping cart")
    private WebElement shoppingCartLink;
    
    @FindBy(xpath = "//div[@class='no-result']")
    private WebElement noResultsMessage;
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public ProductPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Gets the number of products displayed on the page
     * @return Number of products
     */
    public int getProductCount() {
        return productItems.size();
    }
    
    /**
     * Selects a product by clicking on it
     * @param productIndex Index of product (1-based)
     */
    public void selectProduct(int productIndex) {
        try {
            if (productIndex > 0 && productIndex <= productItems.size()) {
                WebElement product = productItems.get(productIndex - 1);
                clickElement(product);
                pause(1000);
            }
        } catch (Exception e) {
            // Product not found
        }
    }
    
    /**
     * Adds current product to cart
     */
    public void addToCart() {
        try {
            WebElement addButton = driver.findElement(By.xpath("//button[text()='Add to cart']"));
            clickElement(addButton);
            pause(2000);
        } catch (Exception e) {
            // Add to cart button not found
        }
    }
    
    /**
     * Gets search result count
     * @return Number of search results
     */
    public int getSearchResultCount() {
        return productItems.size();
    }
    
    /**
     * Checks if no results message is displayed
     * @return true if no results message visible
     */
    public boolean isNoResultsMessageDisplayed() {
        try {
            return isElementDisplayed(noResultsMessage) || 
                   driver.getPageSource().contains("No products were found");
        } catch (Exception e) {
            return false;
        }
    }
}
