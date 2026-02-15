package com.automation.config;

/**
 * Configuration class containing application URLs and test data
 * This class stores all configuration values used across the test framework
 */
public class Config {
    
    // Base URL for the e-commerce application (using nopCommerce demo site)
    public static final String BASE_URL = "https://demo.nopcommerce.com/";
    
    // Page URLs
    public static final String REGISTRATION_URL = BASE_URL + "register";
    public static final String LOGIN_URL = BASE_URL + "login";
    public static final String ACCOUNT_URL = BASE_URL + "customer/info";
    public static final String CART_URL = BASE_URL + "cart";
    public static final String HOME_URL = BASE_URL;
    public static final String ABOUT_US_URL = BASE_URL + "aboutus";
    
    // Test report file path
    public static final String TEST_REPORT_FILE = "test-report.txt";
    
    // Browser settings
    public static final String BROWSER = "chrome";
    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 15;
    public static final int PAGE_LOAD_TIMEOUT = 30;
    
    // Test data - User credentials
    public static String firstName = "John";
    public static String lastName = "Doe";
    public static String email = "johndoe" + System.currentTimeMillis() + "@testmail.com";
    public static String telephone = "1234567890";
    public static String password = "Test@123456";
    
    // Company name for report
    public static final String COMPANY_NAME = "TSW-TMR Automation Solutions";
    public static final String PROJECT_NAME = "E-Commerce Test Automation Framework";
    
    private Config() {
        // Private constructor to prevent instantiation
    }
}
