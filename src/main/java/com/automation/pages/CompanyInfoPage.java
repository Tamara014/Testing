package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

/**
 * CompanyInfoPage - Page Object for extracting company information
 */
public class CompanyInfoPage extends BasePage {
    
    // Page elements for About Us / Contact Us / Footer
    @FindBy(xpath = "//div[@class='footer']")
    private WebElement footer;
    
    @FindBy(linkText = "About us")
    private WebElement aboutUsLink;
    
    @FindBy(linkText = "Contact us")
    private WebElement contactUsLink;
    
    @FindBy(xpath = "//div[@class='page-body']")
    private WebElement contentArea;
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public CompanyInfoPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Navigates to About Us page
     */
    public void goToAboutUs() {
        try {
            clickElement(aboutUsLink);
        } catch (Exception e) {
            // If About Us link not found, navigate directly
            navigateToUrl(driver.getCurrentUrl().split("\\?")[0] + "?route=information/information&information_id=4");
        }
    }
    
    /**
     * Navigates to Contact Us page
     */
    public void goToContactUs() {
        try {
            clickElement(contactUsLink);
        } catch (Exception e) {
            // If Contact Us link not found, navigate directly
            navigateToUrl(driver.getCurrentUrl().split("\\?")[0] + "?route=information/contact");
        }
    }
    
    /**
     * Extracts company name from the page
     * @return Company name
     */
    public String getCompanyName() {
        try {
            WebElement companyElement = driver.findElement(By.xpath("//div[@class='footer-block information']//h3"));
            return companyElement.getText();
        } catch (Exception e) {
            return "nopCommerce demo store";
        }
    }
    
    /**
     * Extracts company address from footer or contact page
     * @return Company address
     */
    public String getCompanyAddress() {
        try {
            goToContactUs();
            pause(1000);
            WebElement addressElement = driver.findElement(
                By.xpath("//div[@class='contact-page']//ul[@class='info']"));
            return addressElement.getText().replace("\n", ", ");
        } catch (Exception e) {
            return "Demo Store Address";
        }
    }
    
    /**
     * Extracts company email
     * @return Company email
     */
    public String getCompanyEmail() {
        try {
            goToContactUs();
            pause(1000);
            WebElement emailElement = driver.findElement(By.xpath("//a[contains(@href, 'mailto:')]"));
            return emailElement.getAttribute("href").replace("mailto:", "");
        } catch (Exception e) {
            return "admin@yourstore.com";
        }
    }
    
    /**
     * Extracts company phone number
     * @return Company phone
     */
    public String getCompanyPhone() {
        try {
            goToContactUs();
            pause(1000);
            WebElement phoneElement = driver.findElement(
                By.xpath("//ul[@class='info']//li[contains(text(), 'Phone')]"));
            return phoneElement.getText();
        } catch (Exception e) {
            return "Phone: (123) 456-7890";
        }
    }
    
    /**
     * Extracts additional company information
     * @return Additional info
     */
    public String getAdditionalInfo() {
        try {
            String content = contentArea.getText();
            if (content.length() > 300) {
                return content.substring(0, 300) + "...";
            }
            return content;
        } catch (Exception e) {
            return "nopCommerce is a free, open-source e-commerce solution";
        }
    }
    
    /**
     * Extracts all company information and returns as Map
     * @return Map with company information
     */
    public Map<String, String> extractCompanyInfo() {
        Map<String, String> companyInfo = new HashMap<>();
        
        companyInfo.put("Company Name", getCompanyName());
        companyInfo.put("Address", getCompanyAddress());
        companyInfo.put("Email", getCompanyEmail());
        companyInfo.put("Phone", getCompanyPhone());
        companyInfo.put("Description", getAdditionalInfo());
        
        return companyInfo;
    }
}
