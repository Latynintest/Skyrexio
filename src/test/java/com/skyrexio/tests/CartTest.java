package com.skyrexio.tests;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
import com.skyrexio.user.UserFactory;

public class CartTest extends BaseTest {
    final String goodsName = "add-to-cart-sauce-labs-onesie";
    final String expectedProductName = "Sauce Labs Onesie";

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
