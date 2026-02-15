package com.automation.tests;

import com.automation.config.Config;
import com.automation.utils.DriverManager;
import com.automation.utils.Logger;
import com.automation.utils.TestReportWriter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

/**
 * BaseTest class - Parent class for all test classes
 * Manages WebDriver lifecycle and test reporting setup
 */
public class BaseTest {
    
    protected WebDriver driver;
    protected Logger logger;
    protected TestReportWriter reportWriter;
    
    // Shared user data across tests
    protected static String registeredEmail;
    protected static String registeredPassword;
    protected static String registeredFirstName;
    protected static String registeredLastName;
    protected static String registeredPhone;
    
    @BeforeSuite
    public void suiteSetup() {
        Logger.log("==================================================");
        Logger.log("Test Suite Started: E-Commerce Automation Testing");
        Logger.log("Date: " + java.time.LocalDateTime.now());
        Logger.log("==================================================");
        
        // Initialize test report writer
        TestReportWriter.initialize();
        TestReportWriter.writeToReport("==================================================");
        TestReportWriter.writeToReport("E-COMMERCE TEST AUTOMATION REPORT");
        TestReportWriter.writeToReport("Date: " + java.time.LocalDateTime.now());
        TestReportWriter.writeToReport("==================================================\n");
    }
    
    @BeforeClass
    public void setUp() {
        Logger.log("Setting up test environment...");
        
        // Initialize WebDriver
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        Logger.log("Browser launched and maximized");
        
        // Navigate to base URL
        driver.get(Config.BASE_URL);
        Logger.log("Navigated to: " + Config.BASE_URL);
        
        // Initialize logger
        logger = new Logger();
    }
    
    @AfterClass
    public void tearDown() {
        Logger.log("Tearing down test environment...");
        
        // Close browser
        if (driver != null) {
            DriverManager.quitDriver();
            Logger.log("Browser closed successfully");
        }
    }
    
    @AfterSuite
    public void suiteTearDown() {
        Logger.log("==================================================");
        Logger.log("Test Suite Completed");
        Logger.log("==================================================");
        
        TestReportWriter.writeToReport("\n==================================================");
        TestReportWriter.writeToReport("TEST SUITE COMPLETED");
        TestReportWriter.writeToReport("==================================================");
        
        // Close report writer
        TestReportWriter.close();
    }
    
    /**
     * Helper method to log test start
     */
    protected void logTestStart(String testName, String description) {
        Logger.log("--------------------------------------------------");
        Logger.log("TEST: " + testName);
        Logger.log("DESCRIPTION: " + description);
        Logger.log("--------------------------------------------------");
        
        TestReportWriter.writeToReport("\n--------------------------------------------------");
        TestReportWriter.writeToReport("TEST: " + testName);
        TestReportWriter.writeToReport("DESCRIPTION: " + description);
    }
    
    /**
     * Helper method to log test result
     */
    protected void logTestResult(String testName, boolean passed, String details) {
        String status = passed ? "PASSED" : "FAILED";
        Logger.log("TEST RESULT: " + status);
        Logger.log("DETAILS: " + details);
        Logger.log("--------------------------------------------------");
        
        TestReportWriter.writeToReport("STATUS: " + status);
        TestReportWriter.writeToReport("DETAILS: " + details);
        TestReportWriter.writeToReport("--------------------------------------------------");
    }
}
