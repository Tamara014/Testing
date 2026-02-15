package com.automation.pages;

import com.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import com.automation.config.Config;

/**
 * BasePage class - Parent class for all Page Objects
 * Contains common methods and utilities used across all pages
 */
public class BasePage {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    /**
     * Constructor to initialize WebDriver and WebDriverWait
     * @param driver WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.EXPLICIT_WAIT));
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Waits for element to be visible and returns it
     * @param element WebElement to wait for
     * @return WebElement after it's visible
     */
    protected WebElement waitForElementVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    /**
     * Waits for element to be clickable and returns it
     * @param element WebElement to wait for
     * @return WebElement after it's clickable
     */
    protected WebElement waitForElementClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    /**
     * Types text into an element after waiting for it to be visible
     * @param element WebElement to type into
     * @param text Text to type
     */
    protected void typeText(WebElement element, String text) {
        waitForElementVisible(element);
        element.clear();
        element.sendKeys(text);
        Logger.debug("Typed text: " + text);
    }
    
    /**
     * Clicks on an element after waiting for it to be clickable
     * @param element WebElement to click
     */
    protected void clickElement(WebElement element) {
        waitForElementClickable(element);
        element.click();
        Logger.debug("Clicked element");
    }
    
    /**
     * Gets text from an element after waiting for it to be visible
     * @param element WebElement to get text from
     * @return Text content of the element
     */
    protected String getElementText(WebElement element) {
        waitForElementVisible(element);
        return element.getText();
    }
    
    /**
     * Checks if an element is displayed
     * @param element WebElement to check
     * @return true if element is displayed, false otherwise
     */
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Gets current page title
     * @return Page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Gets current page URL
     * @return Current URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    /**
     * Navigates to a specific URL
     * @param url URL to navigate to
     */
    public void navigateToUrl(String url) {
        driver.get(url);
        Logger.info("Navigated to: " + url);
    }
    
    /**
     * Measures page load time
     * @return Page load time in milliseconds
     */
    public long measurePageLoadTime() {
        long startTime = System.currentTimeMillis();
        driver.navigate().refresh();
        wait.until(webDriver -> ((org.openqa.selenium.JavascriptExecutor) webDriver)
            .executeScript("return document.readyState").equals("complete"));
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
    
    /**
     * Pauses execution for specified milliseconds
     * @param milliseconds Time to pause
     */
    protected void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Logger.error("Thread sleep interrupted", e);
        }
    }
}
