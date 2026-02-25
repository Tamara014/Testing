# SauceDemo Selenium WebDriver Automation Project

## Overview
This project is a complete automation testing framework for [https://www.saucedemo.com/](https://www.saucedemo.com/) using Java, Selenium WebDriver, TestNG, Maven, Page Object Model (POM), Page Factory, and ExtentReports.

## Features
- Modular Page Object Model with Page Factory
- TestNG for test execution and reporting
- ExtentReports for rich HTML test reports
- WebDriverManager for automatic browser driver management
- Parameterized browser choice (Chrome/Firefox)
- Explicit waits and assertions
- Screenshot capture on test failure
- Logging and reusable utility methods
- Clean code and comments suitable for academic presentation

## Project Structure
```
src/
  main/
    java/
      com/automation/pages/        # Page Object classes (LoginPage, InventoryPage, CartPage, CheckoutPage)
      com/automation/utils/        # Utility classes (DriverFactory, WaitUtils, PerformanceUtils, ScreenshotUtils, Logger)
  test/
    java/
      com/automation/tests/        # Test classes (LoginTest, CartTest, CheckoutTest, PerformanceTest)
README.md
pom.xml
TestNG.xml
```

## Setup & Execution
1. **Install Java 11+ and Maven**
2. **Clone the repository**
3. **Run tests:**
   - `mvn clean test`
   - Or use TestNG XML: `mvn test -DsuiteXmlFile=testng.xml`
4. **Change browser:**
   - Edit the `browser` parameter in `testng.xml` (chrome/firefox)
5. **View reports:**
   - Open `target/ExtentReport.html` for detailed results
   - Screenshots are saved in `target/screenshots/`

## ExtentReports Configuration
ExtentReports is initialized in `BaseTest` and generates a report at `target/ExtentReport.html` after each test suite run.

## Best Practices
- No hardcoded sleeps; uses explicit waits
- Modular, maintainable code
- Clean logging and reporting
- Parameterized browser choice

## Authors
- Seminar Paper Example

---
For any issues or questions, please contact the project maintainer.
