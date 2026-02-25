package com.automation.tests;
import com.automation.utils.DriverFactory;
import com.automation.utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
// Ova klasa služi kao osnova za sve testove. Sadrži podešavanje i gašenje WebDriver-a.
public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = DriverFactory.getDriver("chrome"); // ili "firefox" po potrebi
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void takeScreenshotAfterTest(ITestResult result) {
        // Pravi screenshot posle svakog testa
        if (driver != null) {
            String testName = result.getMethod().getMethodName();
            ScreenshotUtils.captureScreenshot(driver, testName);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

