package com.skyrexio.tests;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
import com.skyrexio.user.UserFactory;
import org.testng.annotations.DataProvider;

public class LoginTest extends BaseTest {
    @Test(invocationCount = 1, priority = 2, enabled = true)
    public void correctLogin() {
        System.out
                .println("LoginTest.Correct!!!!  in thread: " + Thread.currentThread().threadId());
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());

        assertTrue(productsPage.isTitleIsDisplayed(), "Заголовок не виден");
        assertEquals(productsPage.checkTitleName(), "Products", "Не верный заголовок");
        assertTrue(productsPage.isCorrectURL(), "После логина должны быть на странице товаров");
    }

    @DataProvider(name = "incorrectLoginData")
    public Object[][] loginData() {
        return new Object[][] {
                {"locked_out_user", "secret_sauce",
                        "Epic sadface: Sorry, this user has been locked out."},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"Standard_user", "secret_sauce",
                        "Epic sadface: Username and password do not match any user in this service"},};
    }

    @Test(dataProvider = "incorrectLoginData",
            description = "Тест проверяет авторизацию заблокированного пользователя",
            invocationCount = 1)
    public void incorrectLogin(String user, String password, String errorMsg) {
        System.out.println(
                "LoginTest.Incorrect!!!!  in thread: " + Thread.currentThread().threadId());
        loginPage.open();
        loginPage.login(user, password);

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об ошибке");
        assertEquals(loginPage.getErrorText(), errorMsg, "Не верный текст сообщения об ошибке");
    }
}
