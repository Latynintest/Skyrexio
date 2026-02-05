package com.skyrexio.tests;

import java.time.Duration;
import com.skyrexio.pages.*;
import org.testng.ITestContext;
import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.testng.annotations.Optional;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import com.skyrexio.utils.PropertyReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.TakesScreenshot;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners({AllureTestNg.class})
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
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
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

    @Attachment(value = "screenshot", type = "image/png")
    public byte[] takesScreenshot(String description) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
