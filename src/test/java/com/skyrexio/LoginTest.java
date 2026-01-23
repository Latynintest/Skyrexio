package com.skyrexio;

import org.openqa.selenium.By;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void firstLogin() {
        driver.findElement(By.cssSelector("input[data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input[data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input[data-test='login-button']")).click();

        boolean pageProduct = driver.getCurrentUrl().contains("/inventory.html");
        assertTrue(pageProduct, "После логина должны быть на странице товаров");
        String titleName = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(titleName, "Products", "Не верный заголовок");
    }

    @Test
    public void incorrectLogin() {

        driver.findElement(By.cssSelector("input[data-test='username']"))
                .sendKeys("locked_out_user");
        driver.findElement(By.cssSelector("input[data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input[data-test='login-button']")).click();

        boolean errorIsDisplayed =
                driver.findElement(By.cssSelector("h3[data-test='error']")).isDisplayed();
        assertTrue(errorIsDisplayed, "Нет сообщения об ошибке");

        String errorText = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        assertEquals(errorText, "Epic sadface: Sorry, this user has been locked out.",
                "Не верный текст сообщения об ошибке");
    }
}
