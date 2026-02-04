package com.skyrexio.pages;

import java.util.List;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {
    By product = By.cssSelector(DATA_TEST_PATTERN.formatted("inventory-item-name"));

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public ArrayList<String> getProductsNames() {
        List<WebElement> allProducts = driver.findElements(product);
        ArrayList<String> names = new ArrayList<>();
        for (WebElement product : allProducts) {
            names.add(product.getText());
        }
        return names;
    }
}
