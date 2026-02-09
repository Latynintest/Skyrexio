package com.skyrexio.tests;

import io.qameta.allure.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import com.skyrexio.user.UserFactory;
import com.skyrexio.user.TestDataProvider;
import static com.skyrexio.enums.TitleNaming.CART; 
import static com.skyrexio.enums.TitleNaming.PRODUCTS; 

@Epic("Корзина")
@Feature("Работа с корзиной")
@Owner("Sergey Latynin")
public class CartTest extends BaseTest {
    private static final String GOODS_NAME = "add-to-cart-" + TestDataProvider.ONESIE;
    private static final String EXPECTED_PRODUCT_NAME = TestDataProvider.ONESIE_NAME;

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
        assertEquals(productsPage.checkTitleName(), PRODUCTS.getDisplayName());
        productsPage.addGoodsToCart(GOODS_NAME);
        productsPage.switchToCart();
        assertEquals(cartPage.checkTitleName(), CART.getDisplayName());
        assertFalse(cartPage.getProductsNames().isEmpty());
        assertEquals(cartPage.getProductsNames().size(), 1);
        assertTrue(cartPage.getProductsNames().contains(EXPECTED_PRODUCT_NAME));
    }
}
