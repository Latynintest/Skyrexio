package com.skyrexio.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.skyrexio.utils.PropertyReader;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    public static final String DATA_TEST_PATTERN = "[data-test='%s']";
    public static final String BASE_URL = PropertyReader.getProperty("saucedemmo.url");
    private final By title = By.cssSelector(DATA_TEST_PATTERN.formatted("title"));

    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public String checkTitleName() {
        return driver.findElement(title).getText();
    }
}
