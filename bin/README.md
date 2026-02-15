# E-Commerce Test Automation Project

## Overview
Complete Selenium WebDriver test automation framework for e-commerce application testing using Java, Maven, and TestNG.

## Project Structure
```
tsw-tmr/
├── pom.xml                          # Maven configuration
├── testng.xml                       # TestNG suite configuration
├── src/
│   ├── main/java/com/automation/
│   │   ├── config/
│   │   │   └── Config.java         # Configuration management
│   │   ├── pages/                   # Page Object Model classes
│   │   │   ├── BasePage.java
│   │   │   ├── HomePage.java
│   │   │   ├── LoginPage.java
│   │   │   ├── RegistrationPage.java
│   │   │   ├── ProfilePage.java
│   │   │   ├── ProductPage.java
│   │   │   ├── CartPage.java
│   │   │   └── CompanyInfoPage.java
│   │   └── utils/                   # Utility classes
│   │       ├── DriverManager.java
│   │       ├── Logger.java
│   │       └── TestReportWriter.java
│   └── test/java/com/automation/tests/  # Test classes
│       ├── BaseTest.java
│       ├── UserRegistrationTest.java
│       ├── UserLoginTest.java
│       ├── ProfileDataValidationTest.java
│       ├── AddProductsToCartTest.java
│       ├── CartTotalPriceValidationTest.java
│       ├── PagePerformanceTest.java
│       ├── CompanyInfoScrapingTest.java
│       ├── SearchFunctionalityTest.java
│       ├── LogoutTest.java
│       └── NavigationTest.java
└── test-report.txt                  # Generated test report
```

## Technologies Used
- **Java 11+**
- **Selenium WebDriver 4.x**
- **TestNG** - Test framework
- **Maven** - Build and dependency management
- **ChromeDriver** - Browser automation

## Design Patterns
- **Page Object Model (POM)** - Separates page elements and actions from tests
- **Singleton Pattern** - WebDriver management
- **Factory Pattern** - Page object creation

## Test Cases

### Mandatory Tests (7)
1. **User Registration Test** - Validates user registration with valid data
2. **User Login Test** - Tests login with registered credentials
3. **Profile Data Validation Test** - Verifies profile data matches registration
4. **Add Products to Cart Test** - Adds 3 products from different categories
5. **Cart Total Price Validation Test** - Validates cart total calculation
6. **Page Performance Test** - Measures load time for 5+ pages
7. **Company Info Scraping Test** - Extracts and reports company information

### Additional Tests (3 Bonus)
8. **Search Functionality Test** - Tests product search with various queries
9. **Logout Test** - Validates logout functionality and session termination
10. **Navigation Test** - Tests navigation across different website sections

## Setup Instructions

### Prerequisites
1. Install Java JDK 11 or higher
2. Install Maven 3.6+
3. Download ChromeDriver matching your Chrome browser version
4. Set JAVA_HOME and Maven in system PATH

### Configuration
1. Update `src/main/java/com/automation/config/Config.java`:
   - Set BASE_URL to your test application URL
   - Set CHROME_DRIVER_PATH to your ChromeDriver location

### Running Tests

#### Run all tests:
```bash
mvn clean test
```

#### Run with TestNG XML:
```bash
mvn test -DsuiteXmlFile=testng.xml
```

#### Run specific test class:
```bash
mvn test -Dtest=UserRegistrationTest
```

## Features
- ✅ Complete Page Object Model implementation
- ✅ Comprehensive logging system
- ✅ Automatic test report generation (test-report.txt)
- ✅ Explicit waits for stability
- ✅ Reusable utility classes
- ✅ Organized project structure
- ✅ Clean, commented code
- ✅ Test dependencies and execution order
- ✅ Performance measurement
- ✅ Data validation
- ✅ Web scraping capabilities

## Test Report
The framework automatically generates `test-report.txt` containing:
- Test execution details
- Pass/fail status for each test
- Company information extracted from website
- Page load performance metrics
- Detailed test descriptions

## Logging
- Console logging via custom Logger class
- File logging to test-report.txt
- Detailed test steps and results
- Error tracking and reporting

## Best Practices Implemented
- Separation of concerns (POM pattern)
- DRY principle (Don't Repeat Yourself)
- Single Responsibility Principle
- Proper exception handling
- Meaningful variable and method names
- Comprehensive code comments
- Explicit waits over implicit waits
- WebDriver lifecycle management

## Author
Generated for TSW-TMR Test Automation Project

## Date
February 15, 2026
