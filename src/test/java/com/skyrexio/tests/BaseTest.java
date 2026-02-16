package com.skyrexio.tests;

import java.time.Duration;
import com.skyrexio.pages.*;
import org.testng.ITestContext;
import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.skyrexio.utils.PropertyReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxOptions;


public class BaseTest {
    public WebDriver driver;
    CartPage cartPage;
    LoginPage loginPage;
    ProductsPage productsPage;

    protected String user;
    protected String password;

    @Parameters({"browser"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browser, ITestContext context) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("--guest");
            /*
             * options.addArguments("--window-size=1920,1080"); options.addArguments("headless");
             */
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        context.setAttribute("driver", driver);
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        user = PropertyReader.getProperty("saucedemmo.admin_user");
        password = PropertyReader.getProperty("saucedemmo.password");
    }

    @AfterMethod
    public void quitBrowser() {
        driver.quit();
    }

    @Attachment(value = "Screenshot: {0}", type = "image/png")
    public byte[] takeScreenshot(String description) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public String saveTextLog(String message) {
        return message;
    }

    @Attachment(value = "Page Source", type = "text/html")
    public String savePageSource() {
        return driver.getPageSource();
    }
}
