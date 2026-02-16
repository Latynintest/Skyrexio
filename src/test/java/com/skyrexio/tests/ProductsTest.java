package com.skyrexio.tests;
import io.qameta.allure.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import com.skyrexio.user.UserFactory;
import com.skyrexio.user.TestDataProvider;
import static com.skyrexio.enums.TitleNaming.PRODUCTS; 

@Epic("Товары")
@Feature("Работа с товарами")
@Owner("Sergey Latynin")
public class ProductsTest extends BaseTest {

        @Story("Пользователь может добавлять товары в корзину")
        @Severity(SeverityLevel.CRITICAL)
        @TmsLink("skyrexio ТС-3")
        @Description("Тест проверяет добавление товаров в корзину и отображение счетчика")
        @Test
        public void checkGoodsAdded() {
                System.out.println("ProductsTest.Correct!!!!  in thread: "
                                + Thread.currentThread().threadId());
                loginPage.open();
                loginPage.login(UserFactory.withAdminPermission());
                assertTrue(productsPage.isTitleIsDisplayed());
                assertEquals(productsPage.checkTitleName(), PRODUCTS.getDisplayName());

                String[] goodsList = TestDataProvider.getMultipleProducts();

                for (String item : goodsList) {
                        assertTrue(productsPage.isAddToCart(item),
                                        "Начальное состояние кнопки неверно должно быть 'Add to cart'");
                        productsPage.addGoodsToCart(item);
                        assertTrue(productsPage.isRemove(item),
                                        "Текст кнопки не изменился на 'Remove' для товара: "
                                                        + item);
                }
                assertEquals(productsPage.checkCounterValue(), "3");
                assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
        }
}
