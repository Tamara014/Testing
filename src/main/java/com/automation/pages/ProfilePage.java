package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.automation.utils.WaitUtils;

// Klasa za prikaz profila korisnika - prilagoditi prema stvarnoj aplikaciji
public class ProfilePage {
    private WebDriver driver;

    @FindBy(id = "profile-first-name")
    private WebElement profileFirstName;
    @FindBy(id = "profile-last-name")
    private WebElement profileLastName;
    @FindBy(id = "profile-email")
    private WebElement profileEmail;

    // Konstruktor inicijalizuje elemente stranice
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Vraća ime korisnika
    public String getFirstName() {
        WaitUtils.waitForElementVisible(driver, profileFirstName, 10);
        return profileFirstName.getText();
    }
    // Vraća prezime korisnika
    public String getLastName() {
        WaitUtils.waitForElementVisible(driver, profileLastName, 10);
        return profileLastName.getText();
    }
    // Vraća email korisnika
    public String getEmail() {
        WaitUtils.waitForElementVisible(driver, profileEmail, 10);
        return profileEmail.getText();
    }
}
