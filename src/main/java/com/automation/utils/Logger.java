package com.automation.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Custom Logger utility class for logging test execution details
 * Provides methods to log test information at different levels
 */
public class Logger {
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String LOG_PREFIX = "[TEST-LOG]";
    
    /**
     * Logs a general message
     * @param message The message to log
     */
    public static void log(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println(String.format("%s [LOG] [%s] %s", LOG_PREFIX, timestamp, message));
    }
    
    /**
     * Logs informational messages
     * @param message The message to log
     */
    public static void info(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println(String.format("%s [INFO] [%s] %s", LOG_PREFIX, timestamp, message));
    }
    
    /**
     * Logs test start information
     * @param testName Name of the test
     * @param description Description of the test
     */
    public static void logTestStart(String testName, String description) {
        System.out.println("\n" + "=".repeat(80));
        info("TEST STARTED: " + testName);
        info("DESCRIPTION: " + description);
        System.out.println("=".repeat(80));
    }
    
    /**
     * Logs test end information with status
     * @param testName Name of the test
     * @param status Pass or Fail status
     */
    public static void logTestEnd(String testName, String status) {
        System.out.println("-".repeat(80));
        info("TEST COMPLETED: " + testName);
        info("STATUS: " + status);
        System.out.println("=".repeat(80) + "\n");
    }
    
    /**
     * Logs error messages
     * @param message The error message to log
     */
    public static void error(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.err.println(String.format("%s [ERROR] [%s] %s", LOG_PREFIX, timestamp, message));
    }
    
    /**
     * Logs error messages with exception details
     * @param message The error message
     * @param e The exception
     */
    public static void error(String message, Exception e) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.err.println(String.format("%s [ERROR] [%s] %s - Exception: %s", 
            LOG_PREFIX, timestamp, message, e.getMessage()));
    }
    
    /**
     * Logs warning messages
     * @param message The warning message to log
     */
    public static void warning(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println(String.format("%s [WARNING] [%s] %s", LOG_PREFIX, timestamp, message));
    }
    
    /**
     * Logs debug messages
     * @param message The debug message to log
     */
    public static void debug(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println(String.format("%s [DEBUG] [%s] %s", LOG_PREFIX, timestamp, message));
    }
    
    /**
     * Logs test step information
     * @param stepNumber The step number
     * @param stepDescription The step description
     */
    public static void logStep(int stepNumber, String stepDescription) {
        info(String.format("STEP %d: %s", stepNumber, stepDescription));
    }
    
    /**
     * Logs assertion results
     * @param assertionName Name of the assertion
     * @param expected Expected value
     * @param actual Actual value
     * @param passed Whether the assertion passed
     */
    public static void logAssertion(String assertionName, Object expected, Object actual, boolean passed) {
        String status = passed ? "PASSED" : "FAILED";
        info(String.format("ASSERTION [%s]: %s - Expected: %s, Actual: %s", 
            status, assertionName, expected, actual));
    }
}
