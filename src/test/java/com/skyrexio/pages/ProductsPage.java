package com.skyrexio.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    private static final String ADD_TO_CART_PATTERN = "[data-test='%s']";
    private static final String TITLE_PATTERN = "//span[text()='%s']";
    private final By title = By.cssSelector(".title");

    private final By cartCounter =
            By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-badge"));

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCorrectURL() {
        return driver.getCurrentUrl().contains("/inventory.html");
    }

    public boolean isTitleIsDisplayed() {
        By title = By.xpath(TITLE_PATTERN.formatted("Products"));
        return driver.findElement(title).isDisplayed();
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public void addGoodsToCart(String goodsName) {
        By addToCart = By.cssSelector(ADD_TO_CART_PATTERN.formatted(goodsName));
        driver.findElement(addToCart).click();
    }

    public boolean isRemove(String goodsName) {
        String removeButtonName = goodsName.replace("add-to-cart", "remove");
        By removeButton = By.cssSelector(ADD_TO_CART_PATTERN.formatted(removeButtonName));
        return driver.findElements(removeButton).size() > 0;
    }

    public boolean isAddToCart(String goodsName) {
        By addButton = By.cssSelector(ADD_TO_CART_PATTERN.formatted(goodsName));
        return driver.findElements(addButton).size() > 0;
    }

    public String checkCounterValue() {
        return driver.findElement(cartCounter).getText();
    }

    public String checkCounterColor() {
        return driver.findElement(cartCounter).getCssValue("background-color");
    }
}
