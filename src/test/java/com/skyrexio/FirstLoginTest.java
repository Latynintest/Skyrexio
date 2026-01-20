package com.skyrexio;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstLoginTest {

    @Test
    public void firstLogin() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com/");

        driver.quit();

    }
}

// Локатор для поля логин: input[data-test='username']
// Локатор для поля пароль: input[data-test='password']
// Локатор для кнопки: input[data-test='login-button']
// Локатор ошибки: h3[data-test="error"]
