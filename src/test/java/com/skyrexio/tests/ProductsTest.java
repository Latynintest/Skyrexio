package com.skyrexio.tests;

import java.util.List;
import java.util.ArrayList;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class ProductsTest extends BaseTest {
    List<String> goodsList = new ArrayList<>(List.of("add-to-cart-sauce-labs-backpack",
            "add-to-cart-sauce-labs-bolt-t-shirt", "add-to-cart-sauce-labs-onesie"));

    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPage.isTitleIsDisplayed());
        assertEquals(productsPage.checkTitleName(), "Products");

<<<<<<< HEAD
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
=======
        for (String item : goodsList) {
            assertTrue(productsPage.isAddToCart(item),
                    "Начальное состояние кнопки неверно должно быть 'Add to cart'");
            productsPage.addGoodsToCart(item);
            assertTrue(productsPage.isRemove(item),
                    "Текст кнопки не изменился на 'Remove' для товара: " + item);
        }
>>>>>>> ifat-5
        assertEquals(productsPage.checkCounterValue(), "3");
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
    }
}
