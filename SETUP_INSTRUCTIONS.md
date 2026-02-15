# Setup and Run Instructions

## Project Overview
Complete Selenium WebDriver test automation framework for **nopCommerce demo site** (https://demo.nopcommerce.com/) using Java, Maven, and TestNG.

## Prerequisites

### 1. Install Java JDK
- Download and install **Java JDK 11 or higher**
- Set `JAVA_HOME` environment variable
- Add Java to system PATH
- Verify: `java -version`

### 2. Install Apache Maven
- Download Maven from: https://maven.apache.org/download.cgi
- Extract to a directory (e.g., C:\Program Files\Apache\maven)
- Add Maven `bin` directory to system PATH
- Set `MAVEN_HOME` environment variable
- Verify: `mvn -version`

### 3. Install Google Chrome
- Download and install latest Chrome browser
- ChromeDriver will be automatically managed by WebDriverManager

## Project Configuration

The project is already configured to use **https://demo.nopcommerce.com/**

### Key Configuration Files:
- `pom.xml` - Maven dependencies and build configuration
- `testng.xml` - TestNG test suite configuration
- `src/main/java/com/automation/config/Config.java` - Application URLs and settings

## Building the Project

### 1. Clean and Compile
```powershell
mvn clean compile
```

### 2. Run Tests
```powershell
mvn clean test
```

### 3. Run Specific Test
```powershell
mvn test -Dtest=UserRegistrationTest
```

### 4. Run with TestNG XML
```powershell
mvn test -DsuiteXmlFile=testng.xml
```

## Test Execution Order

The tests are configured to run in sequence:

1. **UserRegistrationTest** - Creates a new user account
2. **UserLoginTest** - Logs in with registered credentials
3. **ProfileDataValidationTest** - Validates profile data
4. **AddProductsToCartTest** - Adds 3 products from different categories
5. **CartTotalPriceValidationTest** - Validates cart total price
6. **PagePerformanceTest** - Measures page load times
7. **CompanyInfoScrapingTest** - Extracts company information
8. **SearchFunctionalityTest** - Tests search feature
9. **LogoutTest** - Tests logout functionality
10. **NavigationTest** - Tests site navigation

## Understanding IDE Warnings

The "non-project file" warnings you're seeing are **IDE recognition issues only**. They don't affect Maven compilation or test execution. These warnings appear because:

1. VS Code's Java extension hasn't fully indexed the Maven project yet
2. The project structure needs to be recognized as a Maven project

### To Fix IDE Warnings:

#### Option 1: Reload Java Projects (VS Code)
1. Press `Ctrl+Shift+P`
2. Type: "Java: Clean Java Language Server Workspace"
3. Click on it and reload VS Code

#### Option 2: Use Maven from Command Line
The project will compile and run perfectly with Maven commands even if IDE shows warnings.

#### Option 3: Import as Maven Project
1. Close VS Code
2. Delete `.vscode` folder if exists
3. Reopen VS Code
4. Let Java extension detect the Maven project

## Viewing Test Results

### Console Output
All test logs will be displayed in the console during execution.

### Test Report File
A detailed report will be generated at: `test-report.txt`

The report includes:
- Test execution summary
- Pass/fail status for each test
- Company information extracted
- Page performance metrics
- Detailed test descriptions

## Project Structure

```
tsw-tmr/
├── pom.xml                          # Maven configuration
├── testng.xml                       # TestNG suite configuration
├── SETUP_INSTRUCTIONS.md            # This file
├── README.md                        # Project documentation
├── src/
│   ├── main/java/com/automation/
│   │   ├── config/
│   │   │   └── Config.java         # nopCommerce URLs and settings
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
│       └── [10 test classes]
└── test-report.txt                  # Generated after test run
```

## Troubleshooting

### Issue: Maven not found
**Solution:** Install Maven and add to PATH (see Prerequisites)

### Issue: Java not found
**Solution:** Install JDK and set JAVA_HOME

### Issue: Tests fail on nopcommerce site
**Solution:** 
- The nopcommerce demo site may have changed
- Selectors might need minor adjustments
- Website might be temporarily down

### Issue: ChromeDriver errors
**Solution:** 
- Update Chrome browser to latest version
- WebDriverManager should auto-download correct driver version
- Check internet connection

### Issue: IDE shows errors but Maven works
**Solution:** This is normal. The IDE needs time to index the project or recognize it as Maven project. Maven compilation works independently.

## Running Tests Successfully

### Quick Start:
```powershell
# Navigate to project directory
cd D:\tsw-tmr

# Compile project
mvn clean compile

# Run all tests
mvn clean test

# View results
# Check console output and test-report.txt file
```

### Expected Output:
- Console will show detailed test execution logs
- Each test will log: start, actions, and pass/fail status
- `test-report.txt` will be created with comprehensive results
- All 10 tests should complete (pass/fail depends on website state)

## Notes

1. **Test Data:** Each test run generates unique email addresses to avoid conflicts
2. **Test Dependencies:** Tests must run in order as they depend on each other
3. **nopCommerce Demo:** This is a public demo site, so behavior may vary
4. **Test Report:** Check `test-report.txt` for detailed results and company info
5. **IDE Warnings:** Can be safely ignored - they don't affect Maven execution

## Support

For issues or questions:
1. Ensure Maven and Java are properly installed
2. Check that nopcommerce demo site is accessible
3. Review console logs and test-report.txt for details
4. Verify all dependencies downloaded successfully (Maven will do this automatically)

## Success Criteria

✅ Maven clean compile completes without errors  
✅ Tests execute and log detailed information  
✅ test-report.txt is generated  
✅ Company information is extracted and reported  
✅ Performance metrics are calculated  

The "33 problems" you see in the IDE are **recognition issues only** and will not affect Maven test execution. Run `mvn clean test` from command line to execute tests successfully.
