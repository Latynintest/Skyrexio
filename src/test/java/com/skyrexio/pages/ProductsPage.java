package com.skyrexio.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class ProductsPage extends BasePage {
    private static final String ADD_TO_CART_PATTERN = "[data-test='%s']";
    private static final String TITLE_PATTERN = "//span[text()='%s']";
    private final By cartCounter =
            By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-badge"));

    private final By cartLink = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-link"));

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверяем, что URL корректный для страницы товаров")
    public boolean isCorrectURL() {
        return driver.getCurrentUrl().contains("/inventory.html");
    }

    @Step("Проверяем отображение заголовка 'Products'")
    public boolean isTitleIsDisplayed() {
        By title = By.xpath(TITLE_PATTERN.formatted("Products"));
        return driver.findElement(title).isDisplayed();
    }

    @Step("Добавляем товар '{goodsName}' в корзину")
    public void addGoodsToCart(String goodsName) {
        By addToCart = By.cssSelector(ADD_TO_CART_PATTERN.formatted(goodsName));
        driver.findElement(addToCart).click();
    }

    @Step("Добавляем товар с индексом {goodsIndex} в корзину")
    public void addGoodsToCart(int goodsIndex) {
        driver.findElements(By.xpath("//*[text()='Add to cart']")).get(goodsIndex).click();
    }

    @Step("Проверяем, что для товара '{goodsName}' отображается кнопка 'Remove'")
    public boolean isRemove(String goodsName) {
        String removeButtonName = goodsName.replace("add-to-cart", "remove");
        By removeButton = By.cssSelector(ADD_TO_CART_PATTERN.formatted(removeButtonName));
        return driver.findElements(removeButton).size() > 0;
    }

    @Step("Проверяем, что для товара '{goodsName}' отображается кнопка 'Add to cart'")
    public boolean isAddToCart(String goodsName) {
        By addButton = By.cssSelector(ADD_TO_CART_PATTERN.formatted(goodsName));
        return driver.findElements(addButton).size() > 0;
    }

    @Step("Получаем значение счетчика корзины")
    public String checkCounterValue() {
        return driver.findElement(cartCounter).getText();
    }

    @Step("Получаем цвет счетчика корзины")
    public String checkCounterColor() {
        return driver.findElement(cartCounter).getCssValue("background-color");
    }

    @Step("Переходим в корзину")
    public void switchToCart() {
        driver.findElement(cartLink).click();
    }
}
