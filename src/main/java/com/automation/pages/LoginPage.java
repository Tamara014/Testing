package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.automation.utils.WaitUtils;

// Ova klasa predstavlja login stranicu i sadrži metode za unos korisničkog imena, lozinke i prijavu.
public class LoginPage {
    // Polja za elemente na login stranici
    @FindBy(id = "user-name")
    private WebElement usernameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(id = "login-button")
    private WebElement loginButton;
    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    private WebDriver driver;

    // Konstruktor inicijalizuje elemente stranice
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Unos korisničkog imena
    public void enterUsername(String username) {
        WaitUtils.waitForElementVisible(driver, usernameField, 10);
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    // Unos lozinke
    public void enterPassword(String password) {
        WaitUtils.waitForElementVisible(driver, passwordField, 10);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    // Klik na dugme za prijavu
    public void clickLogin() {
        WaitUtils.waitForElementClickable(driver, loginButton, 10);
        loginButton.click();
    }

    // Vraća poruku o grešci ako login nije uspešan
    public String getErrorMessage() {
        try {
            WaitUtils.waitForElementVisible(driver, errorMessage, 5);
            return errorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }
}
