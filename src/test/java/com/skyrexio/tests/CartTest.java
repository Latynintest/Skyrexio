package com.skyrexio.tests;

import io.qameta.allure.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import com.skyrexio.user.UserFactory;
import io.qameta.allure.Description;

@Epic("Корзина")
@Feature("Работа с корзиной")
@Owner("Sergey Latynin")
public class CartTest extends BaseTest {
    final String goodsName = "add-to-cart-sauce-labs-onesie";
    final String expectedProductName = "Sauce Labs Onesie";

    @Story("Пользователь может добавить товар в корзину и увидеть его там")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("skyrexio ТС-5")
    @Description("Тест проверяет добавление товара в корзину и отображение в ней")
    @Test
    public void checkGoodsAdded() {
        System.out.println("CartTest.Correct!!!!  in thread: " + Thread.currentThread().threadId());
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        assertTrue(productsPage.isTitleIsDisplayed());
        assertEquals(productsPage.checkTitleName(), "Products");
        productsPage.addGoodsToCart(goodsName);
        productsPage.switchToCart();
        assertEquals(cartPage.checkTitleName(), "Your Cart");
        assertFalse(cartPage.getProductsNames().isEmpty());
        assertEquals(cartPage.getProductsNames().size(), 1);
        assertTrue(cartPage.getProductsNames().contains(expectedProductName));
    }
}
