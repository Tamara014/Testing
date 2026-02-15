package com.automation.utils;

import com.automation.config.Config;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * TestReportWriter utility class for writing test results to file
 * Manages test result collection and report generation
 */
public class TestReportWriter {
    
    private static List<TestResult> testResults = new ArrayList<>();
    private static CompanyInfo companyInfo = null;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * Initializes the report writer
     */
    public static void initialize() {
        testResults.clear();
        companyInfo = null;
        Logger.info("TestReportWriter initialized");
    }
    
    /**
     * Writes a line to the report
     * @param message The message to write
     */
    public static void writeToReport(String message) {
        System.out.println(message);
    }
    
    /**
     * Closes the report writer
     */
    public static void close() {
        generateReport();
        Logger.info("TestReportWriter closed");
    }
    
    /**
     * Inner class to store test result information
     */
    public static class TestResult {
        private String testName;
        private String description;
        private String status;
        private String timestamp;
        private String details;
        
        public TestResult(String testName, String description, String status, String details) {
            this.testName = testName;
            this.description = description;
            this.status = status;
            this.timestamp = LocalDateTime.now().format(formatter);
            this.details = details;
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\n----------------------------------------\n");
            sb.append("Test Name: ").append(testName).append("\n");
            sb.append("Description: ").append(description).append("\n");
            sb.append("Status: ").append(status).append("\n");
            sb.append("Timestamp: ").append(timestamp).append("\n");
            if (details != null && !details.isEmpty()) {
                sb.append("Details: ").append(details).append("\n");
            }
            sb.append("----------------------------------------\n");
            return sb.toString();
        }
    }
    
    /**
     * Inner class to store company information
     */
    public static class CompanyInfo {
        private String companyName;
        private String address;
        private String email;
        private String phone;
        private String additionalInfo;
        
        public CompanyInfo(String companyName, String address, String email, String phone, String additionalInfo) {
            this.companyName = companyName;
            this.address = address;
            this.email = email;
            this.phone = phone;
            this.additionalInfo = additionalInfo;
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\n========================================\n");
            sb.append("COMPANY INFORMATION\n");
            sb.append("========================================\n");
            sb.append("Company Name: ").append(companyName).append("\n");
            sb.append("Address: ").append(address).append("\n");
            sb.append("Email: ").append(email).append("\n");
            sb.append("Phone: ").append(phone).append("\n");
            if (additionalInfo != null && !additionalInfo.isEmpty()) {
                sb.append("Additional Info: ").append(additionalInfo).append("\n");
            }
            sb.append("========================================\n");
            return sb.toString();
        }
    }
    
    /**
     * Adds a test result to the collection
     * @param testName Name of the test
     * @param description Description of the test
     * @param status Status (PASSED/FAILED)
     * @param details Additional details
     */
    public static void addTestResult(String testName, String description, String status, String details) {
        testResults.add(new TestResult(testName, description, status, details));
        Logger.info("Test result added: " + testName + " - " + status);
    }
    
    /**
     * Sets company information
     * @param companyName Company name
     * @param address Company address
     * @param email Company email
     * @param phone Company phone
     * @param additionalInfo Additional information
     */
    public static void setCompanyInfo(String companyName, String address, String email, String phone, String additionalInfo) {
        companyInfo = new CompanyInfo(companyName, address, email, phone, additionalInfo);
        Logger.info("Company information set");
    }
    
    /**
     * Generates and writes the complete test report to file
     */
    public static void generateReport() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Config.TEST_REPORT_FILE))) {
            // Write header
            writer.write("================================================================================\n");
            writer.write("                    TEST EXECUTION REPORT                                       \n");
            writer.write("================================================================================\n");
            writer.write("Project: " + Config.PROJECT_NAME + "\n");
            writer.write("Company: " + Config.COMPANY_NAME + "\n");
            writer.write("Report Generated: " + LocalDateTime.now().format(formatter) + "\n");
            writer.write("================================================================================\n");
            
            // Write test summary
            int passedTests = (int) testResults.stream().filter(r -> r.status.equals("PASSED")).count();
            int failedTests = (int) testResults.stream().filter(r -> r.status.equals("FAILED")).count();
            int totalTests = testResults.size();
            
            writer.write("\n\nTEST SUMMARY\n");
            writer.write("----------------------------------------\n");
            writer.write("Total Tests Executed: " + totalTests + "\n");
            writer.write("Tests Passed: " + passedTests + "\n");
            writer.write("Tests Failed: " + failedTests + "\n");
            writer.write("Pass Rate: " + (totalTests > 0 ? (passedTests * 100.0 / totalTests) : 0) + "%\n");
            writer.write("----------------------------------------\n");
            
            // Write individual test results
            writer.write("\n\nDETAILED TEST RESULTS\n");
            writer.write("================================================================================\n");
            for (TestResult result : testResults) {
                writer.write(result.toString());
            }
            
            // Write company information if available
            if (companyInfo != null) {
                writer.write("\n\n");
                writer.write(companyInfo.toString());
            }
            
            // Write footer
            writer.write("\n\n================================================================================\n");
            writer.write("                         END OF REPORT                                          \n");
            writer.write("================================================================================\n");
            
            Logger.info("Test report generated successfully: " + Config.TEST_REPORT_FILE);
            
        } catch (IOException e) {
            Logger.error("Failed to generate test report", e);
        }
    }
    
    /**
     * Clears all test results and company info (useful for test cleanup)
     */
    public static void clearResults() {
        testResults.clear();
        companyInfo = null;
    }
}
