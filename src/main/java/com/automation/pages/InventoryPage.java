package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import com.automation.utils.WaitUtils;

// Ova klasa predstavlja stranicu sa proizvodima i sadrži metode za dodavanje i uklanjanje proizvoda iz korpe.
public class InventoryPage {
    private WebDriver driver;

    @FindBy(className = "inventory_item")
    private List<WebElement> products;
    @FindBy(css = "button[data-test^='add-to-cart']")
    private List<WebElement> addToCartButtons;
    @FindBy(css = "button[data-test^='remove']")
    private List<WebElement> removeButtons;
    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    // Konstruktor inicijalizuje elemente stranice
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Dodaje proizvod u korpu po indeksu
    public void addProductToCart(int index) {
        WaitUtils.waitForElementClickable(driver, addToCartButtons.get(index), 10);
        addToCartButtons.get(index).click();
    }

    // Uklanja proizvod iz korpe po indeksu
    public void removeProductFromCart(int index) {
        WaitUtils.waitForElementClickable(driver, removeButtons.get(index), 10);
        removeButtons.get(index).click();
    }

    // Vraća broj proizvoda u korpi
    public int getCartItemCount() {
        try {
            WaitUtils.waitForElementVisible(driver, cartBadge, 5);
            return Integer.parseInt(cartBadge.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    // Vraća broj proizvoda na stranici
    public int getProductCount() {
        return products.size();
    }
}
