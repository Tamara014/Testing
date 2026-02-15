package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * HomePage - Page Object for home page and navigation
 */
public class HomePage extends BasePage {
    
    // Page elements
    @FindBy(xpath = "//div[@class='header-logo']")
    private WebElement logo;
    
    @FindBy(linkText = "Computers")
    private WebElement computersMenu;
    
    @FindBy(linkText = "Electronics")
    private WebElement electronicsMenu;
    
    @FindBy(linkText = "Apparel")
    private WebElement apparelMenu;
    
    @FindBy(linkText = "Register")
    private WebElement registerLink;
    
    @FindBy(linkText = "Log in")
    private WebElement loginLink;
    
    @FindBy(xpath = "//div[@class='item-grid']//div[@class='item-box']")
    private List<WebElement> featuredProducts;
    
    @FindBy(xpath = "//input[@id='small-searchterms']")
    private WebElement searchBox;
    
    @FindBy(xpath = "//button[@type='submit' and text()='Search']")
    private WebElement searchButton;
    
    @FindBy(linkText = "Shopping cart")
    private WebElement cartLink;
    
    @FindBy(xpath = "//a[@href='/customer/info']")
    private WebElement myAccountLink;
    
    @FindBy(xpath = "//footer")
    private WebElement footer;
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Clicks register link
     */
    public void clickRegisterLink() {
        clickElement(registerLink);
    }
    
    /**
     * Clicks login link
     */
    public void clickLoginLink() {
        clickElement(loginLink);
    }
    
    /**
     * Clicks profile/account link
     */
    public void clickProfileLink() {
        try {
            clickElement(myAccountLink);
        } catch (Exception e) {
            driver.get(com.automation.config.Config.ACCOUNT_URL);
        }
    }
    
    /**
     * Clicks cart link
     */
    public void clickCartLink() {
        clickElement(cartLink);
    }
    
    /**
     * Clicks About link
     */
    public void clickAboutLink() {
        driver.get(com.automation.config.Config.ABOUT_US_URL);
    }
    
    /**
     * Navigates to a category
     * @param categoryName Category name
     */
    public void navigateToCategory(String categoryName) {
        try {
            if (categoryName.equalsIgnoreCase("Electronics")) {
                clickElement(electronicsMenu);
            } else if (categoryName.equalsIgnoreCase("Clothing") || categoryName.equalsIgnoreCase("Apparel")) {
                clickElement(apparelMenu);
            } else if (categoryName.equalsIgnoreCase("Books")) {
                driver.get(com.automation.config.Config.BASE_URL + "books");
            } else {
                clickElement(computersMenu);
            }
        } catch (Exception e) {
            driver.get(com.automation.config.Config.BASE_URL + categoryName.toLowerCase());
        }
    }
    
    /**
     * Search for a product
     * @param searchQuery Search query
     */
    public void searchProduct(String searchQuery) {
        typeText(searchBox, searchQuery);
        clickElement(searchButton);
    }
    
    /**
     * Gets the number of featured products on home page
     * @return Number of featured products
     */
    public int getFeaturedProductCount() {
        return featuredProducts.size();
    }
    
    /**
     * Verifies if home page is loaded
     * @return true if on home page
     */
    public boolean isHomePageLoaded() {
        return isElementDisplayed(logo);
    }
    
    /**
     * Checks if on home page
     * @return true if on home page
     */
    public boolean isOnHomePage() {
        return driver.getCurrentUrl().contains(com.automation.config.Config.BASE_URL);
    }
    
    /**
     * Checks if login link is visible
     * @return true if login link is visible
     */
    public boolean isLoginLinkVisible() {
        try {
            return isElementDisplayed(loginLink);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Checks if on login page
     * @return true if on login page
     */
    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().contains("login");
    }
    
    /**
     * Measures page load time for home page
     * @return Load time in milliseconds
     */
    public long getHomePageLoadTime() {
        return measurePageLoadTime();
    }
}
