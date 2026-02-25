package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.automation.utils.WaitUtils;

// Ova klasa predstavlja checkout stranicu i sadrži metode za unos podataka i završetak kupovine.
public class CheckoutPage {
    private WebDriver driver;
    @FindBy(id = "first-name")
    private WebElement firstNameField;
    @FindBy(id = "last-name")
    private WebElement lastNameField;
    @FindBy(id = "postal-code")
    private WebElement postalCodeField;
    @FindBy(id = "continue")
    private WebElement continueButton;
    @FindBy(className = "summary_total_label")
    private WebElement totalLabel;
    @FindBy(id = "finish")
    private WebElement finishButton;
    @FindBy(className = "complete-header")
    private WebElement completeHeader;

    // Konstruktor inicijalizuje elemente stranice
    public CheckoutPage(WebDriver driver) {
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

    // Unos poštanskog koda
    public void enterPostalCode(String postalCode) {
        WaitUtils.waitForElementVisible(driver, postalCodeField, 10);
        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);
    }

    // Klik na dugme za nastavak
    public void clickContinue() {
        WaitUtils.waitForElementClickable(driver, continueButton, 10);
        continueButton.click();
    }

    // Vraća ukupnu cenu
    public String getTotalLabel() {
        WaitUtils.waitForElementVisible(driver, totalLabel, 10);
        return totalLabel.getText();
    }

    // Klik na dugme za završetak kupovine
    public void clickFinish() {
        WaitUtils.waitForElementClickable(driver, finishButton, 10);
        finishButton.click();
    }

    // Vraća poruku o uspešnoj kupovini
    public String getCompleteHeader() {
        WaitUtils.waitForElementVisible(driver, completeHeader, 10);
        return completeHeader.getText();
    }
}
