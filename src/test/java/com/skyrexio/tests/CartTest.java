package com.skyrexio.tests;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {
    final String goodsName = "add-to-cart-sauce-labs-onesie";
    final String expectedProductName = "Sauce Labs Onesie";

    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
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
