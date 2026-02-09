package com.skyrexio.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import com.skyrexio.user.User;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By loginInput = By.cssSelector(DATA_TEST_PATTERN.formatted("username"));
    private final By passwordInput = By.cssSelector(DATA_TEST_PATTERN.formatted("password"));
    private final By loginButton = By.cssSelector(DATA_TEST_PATTERN.formatted("login-button"));
    private final By errorMsg = By.cssSelector(DATA_TEST_PATTERN.formatted("error"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие браузера")
    public void open() {
        driver.get(BASE_URL);
    }

    @Step("Выполняем вход с данными: логин и пароль")
    public void login(String user, String password) {
        fillLoginField(user);
        fillPasswordField(password);
        driver.findElement(loginButton).click();
    }

    @Step("Выполняем вход пользователем: {user}")
    public void login(User user) {
        fillLoginField(user.getEmail());
        fillPasswordField(user.getPassword());
        driver.findElement(loginButton).click();
    }

    private void fillLoginField(String username) {
        driver.findElement(loginInput).clear();
        driver.findElement(loginInput).sendKeys(username);
    }

    private void fillPasswordField(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Проверяем, видимость уведомления об ошибке")
    public boolean isErrorDisplayed() {
        return driver.findElement(errorMsg).isDisplayed();
    }

    @Step("Получаем текст сообщения об ошибке")
    public String getErrorText() {
        return driver.findElement(errorMsg).getText();
    }
}
