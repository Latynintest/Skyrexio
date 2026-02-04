package com.skyrexio.tests;

import java.util.List;
import java.util.ArrayList;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import com.skyrexio.user.UserFactory;

public class ProductsTest extends BaseTest {
    List<String> goodsList = new ArrayList<>(List.of("add-to-cart-sauce-labs-backpack",
            "add-to-cart-sauce-labs-bolt-t-shirt", "add-to-cart-sauce-labs-onesie"));

    @Test
    public void checkGoodsAdded() {
        System.out.println(
                "ProductsTest.Correct!!!!  in thread: " + Thread.currentThread().threadId());
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        assertTrue(productsPage.isTitleIsDisplayed());
        assertEquals(productsPage.checkTitleName(), "Products");

        for (String item : goodsList) {
            assertTrue(productsPage.isAddToCart(item),
                    "Начальное состояние кнопки неверно должно быть 'Add to cart'");
            productsPage.addGoodsToCart(item);
            assertTrue(productsPage.isRemove(item),
                    "Текст кнопки не изменился на 'Remove' для товара: " + item);
        }
        assertEquals(productsPage.checkCounterValue(), "3");
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
    }
}
