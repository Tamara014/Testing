package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * RegistrationPage - Page Object for user registration functionality
 */
public class RegistrationPage extends BasePage {
    
    // Page elements
    @FindBy(id = "FirstName")
    private WebElement firstNameInput;
    
    @FindBy(id = "LastName")
    private WebElement lastNameInput;
    
    @FindBy(id = "Email")
    private WebElement emailInput;
    
    @FindBy(id = "Password")
    private WebElement passwordInput;
    
    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordInput;
    
    @FindBy(id = "register-button")
    private WebElement registerButton;
    
    @FindBy(xpath = "//div[@class='result']")
    private WebElement successMessage;
    
    @FindBy(linkText = "CONTINUE")
    private WebElement continueButton;
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Fills the registration form with user details
     * @param firstName First name
     * @param lastName Last name
     * @param email Email address
     * @param password Password
     * @param phone Phone number (not used in nopcommerce)
     */
    public void fillRegistrationForm(String firstName, String lastName, String email, 
                                     String password, String phone) {
        typeText(firstNameInput, firstName);
        typeText(lastNameInput, lastName);
        typeText(emailInput, email);
        typeText(passwordInput, password);
        typeText(confirmPasswordInput, password);
    }
    
    /**
     * Submits the registration form
     */
    public void submitRegistration() {
        clickElement(registerButton);
    }
    
    /**
     * Performs complete registration process
     * @param firstName First name
     * @param lastName Last name
     * @param email Email address
     * @param password Password
     * @param phone Phone number
     */
    public void register(String firstName, String lastName, String email, 
                        String password, String phone) {
        fillRegistrationForm(firstName, lastName, email, password, phone);
        submitRegistration();
    }
    
    /**
     * Checks if registration was successful
     * @return true if success message is displayed
     */
    public boolean isRegistrationSuccessful() {
        try {
            pause(2000);
            return isElementDisplayed(successMessage) && 
                   getElementText(successMessage).contains("registration completed");
        } catch (Exception e) {
            return false;
        }
    }
}
