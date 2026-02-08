package com.skyrexio.tests;

import io.qameta.allure.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import com.skyrexio.user.UserFactory;
import org.testng.annotations.DataProvider;

@Epic("Авторизация")
@Feature("Функционал входа в систему")
@Owner("Sergey Latynin")
public class LoginTest extends BaseTest {

        @Feature("Успешная авторизация")
        @Story("Пользователь может успешно авторизоваться с корректными данными")
        @Severity(SeverityLevel.BLOCKER)
        @Owner("Sergey Latynin")
        @TmsLink("skyrexio ТС-1")
        @Description("Тест проверяет успешную авторизацию с корректными данными пользователя")
        @Test(invocationCount = 1, priority = 2, enabled = true)
        public void correctLogin() {
                System.out.println("LoginTest.Correct!!!!  in thread: "
                                + Thread.currentThread().threadId());
                loginPage.open();
                loginPage.login(UserFactory.withAdminPermission());

                assertTrue(productsPage.isTitleIsDisplayed(), "Заголовок не виден");
                assertEquals(productsPage.checkTitleName(), "Products", "Не верный заголовок");
                assertTrue(productsPage.isCorrectURL(),
                                "После логина должны быть на странице товаров");
        }

        @DataProvider(name = "incorrectLoginData")
        public Object[][] loginData() {
                return new Object[][] {{"locked_out_user", "secret_sauce",
                                "Epic sadface: Sorry, this user has been locked out."},
                                {"", "secret_sauce", "Epic sadface: Username is required"},
                                {"standard_user", "", "Epic sadface: Password is required"},
                                {"Standard_user", "secret_sauce",
                                                "Epic sadface: Username and password do not match any user in this service"},};
        }

        @Feature("Неуспешная авторизация")
        @Story("Пользователь не может авторизоваться с некорректными данными")
        @Severity(SeverityLevel.CRITICAL)
        @Owner("Sergey Latynin")
        @TmsLink("skyrexio ТС-2")
        @Issue("BUG")
        @Description("Тест проверяет авторизацию с некорректными данными и отображение ошибок")
        @Test(dataProvider = "incorrectLoginData",
                        description = "Тест проверяет авторизацию заблокированного пользователя",
                        invocationCount = 1)
        public void incorrectLogin(String user, String password, String errorMsg) {
                System.out.println("LoginTest.Incorrect!!!!  in thread: "
                                + Thread.currentThread().threadId());
                loginPage.open();
                loginPage.login(user, password);

                assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об ошибке");
                assertEquals(loginPage.getErrorText(), errorMsg,
                                "Не верный текст сообщения об ошибке");
        }
}
