package com.skyrexio.tests;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class ProductsTest extends BaseTest {

    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.isTitleIsDisplayed();

        String item1 = "add-to-cart-sauce-labs-backpack";
        String item2 = "add-to-cart-sauce-labs-bolt-t-shirt";
        String item3 = "add-to-cart-sauce-labs-onesie";

        assertTrue(productsPage.isAddToCart(item1),
                "Начальное состояние кнопки неверно должно быть 'Add to cart'");

        productsPage.addGoodsToCart(item1);
        productsPage.addGoodsToCart(item2);
        productsPage.addGoodsToCart(item3);

        assertTrue(productsPage.isRemove(item1), "Текст кнопки не изменился на 'Remove'");
        assertTrue(productsPage.isRemove(item2), "Текст кнопки не изменился на 'Remove'");
        assertTrue(productsPage.isRemove(item3), "Текст кнопки не изменился на 'Remove'");
        assertEquals(productsPage.checkCounterValue(), "3");
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
    }
}
