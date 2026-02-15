package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * ProfilePage - Page Object for user profile/account information
 */
public class ProfilePage extends BasePage {
    
    // Page elements
    @FindBy(linkText = "Edit Account")
    private WebElement editAccountLink;
    
    @FindBy(id = "input-firstname")
    private WebElement firstNameInput;
    
    @FindBy(id = "input-lastname")
    private WebElement lastNameInput;
    
    @FindBy(id = "input-email")
    private WebElement emailInput;
    
    @FindBy(id = "input-telephone")
    private WebElement telephoneInput;
    
    @FindBy(xpath = "//div[@id='content']/h2[text()='My Account']")
    private WebElement accountHeading;
    
    @FindBy(linkText = "Account")
    private WebElement accountBreadcrumb;
    
    @FindBy(linkText = "Logout")
    private WebElement logoutLink;
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public ProfilePage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Gets first name from profile
     * @return First name
     */
    public String getFirstName() {
        try {
            return firstNameInput.getAttribute("value");
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Gets last name from profile
     * @return Last name
     */
    public String getLastName() {
        try {
            return lastNameInput.getAttribute("value");
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Gets email from profile
     * @return Email address
     */
    public String getEmail() {
        try {
            return emailInput.getAttribute("value");
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Gets phone from profile (nopcommerce doesn't have phone field)
     * @return Phone number
     */
    public String getPhone() {
        return ""; // nopcommerce doesn't have phone in profile
    }
    
    /**
     * Clicks logout button
     */
    public void clickLogout() {
        try {
            clickElement(logoutLink);
        } catch (Exception e) {
            driver.get(com.automation.config.Config.BASE_URL + "logout");
        }
    }
    
    /**
     * Verifies if user is on account page
     * @return true if on account page
     */
    public boolean isOnAccountPage() {
        try {
            return isElementDisplayed(accountHeading);
        } catch (Exception e) {
            return false;
        }
    }
}
