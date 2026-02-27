package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.automation.utils.WaitUtils;

// Klasa za registraciju korisnika - prilagoditi prema stvarnoj aplikaciji
public class RegistrationPage {
    private WebDriver driver;

    @FindBy(id = "first-name")
    private WebElement firstNameField;
    @FindBy(id = "last-name")
    private WebElement lastNameField;
    @FindBy(id = "email")
    private WebElement emailField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(id = "register-button")
    private WebElement registerButton;
    @FindBy(css = ".success-message")
    private WebElement successMessage;

    // Konstruktor inicijalizuje elemente stranice
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Unos imena
    public void enterFirstName(String firstName) {
        WaitUtils.waitForElementVisible(driver, firstNameField, 10);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }
    // Unos prezimena
    public void enterLastName(String lastName) {
        WaitUtils.waitForElementVisible(driver, lastNameField, 10);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }
    // Unos email adrese
    public void enterEmail(String email) {
        WaitUtils.waitForElementVisible(driver, emailField, 10);
        emailField.clear();
        emailField.sendKeys(email);
    }
    // Unos lozinke
    public void enterPassword(String password) {
        WaitUtils.waitForElementVisible(driver, passwordField, 10);
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    // Klik na dugme za registraciju
    public void clickRegister() {
        WaitUtils.waitForElementClickable(driver, registerButton, 10);
        registerButton.click();
    }
    // Vraća poruku o uspešnoj registraciji
    public String getSuccessMessage() {
        WaitUtils.waitForElementVisible(driver, successMessage, 10);
        return successMessage.getText();
    }
}
