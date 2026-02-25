package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.automation.utils.WaitUtils;

import java.util.List;

// Ova klasa predstavlja stranicu korpe i sadrži metode za proveru broja proizvoda i checkout.
public class CartPage {
    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;
    @FindBy(id = "checkout")
    private WebElement checkoutButton;
    private WebDriver driver;

    // Konstruktor inicijalizuje elemente stranice
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    // Vraća broj proizvoda u korpi
    public int getCartItemsCount() {
        return cartItems.size();
    }

    // Klik na dugme za checkout
    public void clickCheckout() {
        WaitUtils.waitForElementClickable(driver, checkoutButton, 10);
        checkoutButton.click();
    }
}
