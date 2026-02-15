package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * LoginPage - Page Object for user login functionality
 */
public class LoginPage extends BasePage {
    
    // Page elements
    @FindBy(id = "Email")
    private WebElement emailInput;
    
    @FindBy(id = "Password")
    private WebElement passwordInput;
    
    @FindBy(xpath = "//button[@class='button-1 login-button']")
    private WebElement loginButton;
    
    @FindBy(linkText = "My account")
    private WebElement myAccountLink;
    
    @FindBy(linkText = "Log out")
    private WebElement logoutLink;
    
    @FindBy(xpath = "//div[@class='page-title']/h1")
    private WebElement pageHeading;
    
    @FindBy(xpath = "//div[contains(@class,'message-error')]")
    private WebElement errorMessage;
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Enters email address
     * @param email Email address
     */
    public void enterEmail(String email) {
        typeText(emailInput, email);
    }
    
    /**
     * Enters password
     * @param password Password
     */
    public void enterPassword(String password) {
        typeText(passwordInput, password);
    }
    
    /**
     * Clicks login button
     */
    public void clickLogin() {
        clickElement(loginButton);
    }
    
    /**
     * Performs complete login process
     * @param email Email address
     * @param password Password
     */
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }
    
    /**
     * Checks if login was successful by verifying My Account page
     * @return true if login successful
     */
    public boolean isLoginSuccessful() {
        try {
            pause(2000);
            return driver.getCurrentUrl().contains("nopcommerce.com") && 
                   !driver.getCurrentUrl().contains("login");
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Checks if logout link is displayed (indicates user is logged in)
     * @return true if logout link is visible
     */
    public boolean isLogoutLinkDisplayed() {
        try {
            return isElementDisplayed(logoutLink);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Gets error message text if login fails
     * @return Error message text
     */
    public String getErrorMessage() {
        try {
            return getElementText(errorMessage);
        } catch (Exception e) {
            return "";
        }
    }
}
