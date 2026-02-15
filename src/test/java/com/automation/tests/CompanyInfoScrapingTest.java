package com.automation.tests;

import com.automation.pages.HomePage;
import com.automation.pages.CompanyInfoPage;
import com.automation.utils.Logger;
import com.automation.utils.TestReportWriter;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;

/**
 * Test Case 7: Company Info Scraping Test
 * Extracts company information and writes to test report
 */
public class CompanyInfoScrapingTest extends BaseTest {
    
    @Test(priority = 7, description = "Extract company information and write to report")
    public void testCompanyInfoScraping() {
        String testName = "Company Info Scraping Test";
        String description = "Extract company information from website (name, address, contact) and write to report";
        
        logTestStart(testName, description);
        
        try {
            // Navigate to company info page (About Us / Contact Us)
            HomePage homePage = new HomePage(driver);
            homePage.clickAboutLink();
            Logger.log("Navigated to About/Company Info page");
            
            // Extract company information
            CompanyInfoPage companyInfoPage = new CompanyInfoPage(driver);
            Map<String, String> companyInfo = companyInfoPage.extractCompanyInfo();
            
            Logger.log("\n=== Company Information Extracted ===");
            TestReportWriter.writeToReport("\n\n==================================================");
            TestReportWriter.writeToReport("COMPANY INFORMATION");
            TestReportWriter.writeToReport("==================================================");
            
            // Log and write each piece of company information
            for (Map.Entry<String, String> entry : companyInfo.entrySet()) {
                String info = entry.getKey() + ": " + entry.getValue();
                Logger.log(info);
                TestReportWriter.writeToReport(info);
            }
            
            Logger.log("=====================================\n");
            TestReportWriter.writeToReport("==================================================\n");
            
            // Verify that at least some company info was extracted
            Assert.assertFalse(companyInfo.isEmpty(), "Company information should not be empty");
            Assert.assertTrue(companyInfo.size() >= 3, 
                "Should extract at least 3 pieces of company information");
            
            logTestResult(testName, true, 
                "Successfully extracted " + companyInfo.size() + " pieces of company information and wrote to report");
            
        } catch (Exception e) {
            Logger.log("ERROR: " + e.getMessage());
            logTestResult(testName, false, "Company info scraping failed: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
