package com.skyrexio.user;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    public static final String STANDARD_USER = "standard_user";
    public static final String LOCKED_USER = "locked_out_user";
    public static final String PROBLEM_USER = "problem_user";
    public static final String PERFORMANCE_USER = "performance_glitch_user";
    public static final String ERROR_USER = "error_user";
    public static final String VISUAL_USER = "visual_user";

    public static final String PASSWORD = "secret_sauce";

    public static final String ERROR_LOCKED = "Epic sadface: Sorry, this user has been locked out.";
    public static final String ERROR_USERNAME_REQUIRED = "Epic sadface: Username is required";
    public static final String ERROR_PASSWORD_REQUIRED = "Epic sadface: Password is required";
    public static final String ERROR_INVALID_CREDENTIALS =
            "Epic sadface: Username and password do not match any user in this service";

    public static final String BACKPACK = "sauce-labs-backpack";
    public static final String BIKE_LIGHT = "sauce-labs-bike-light";
    public static final String BOLT_T_SHIRT = "sauce-labs-bolt-t-shirt";
    public static final String FLEECE_JACKET = "sauce-labs-fleece-jacket";
    public static final String ONESIE = "sauce-labs-onesie";
    public static final String RED_T_SHIRT = "test.allthethings()-t-shirt-(red)";

    public static final String BACKPACK_NAME = "Sauce Labs Backpack";
    public static final String ONESIE_NAME = "Sauce Labs Onesie";

    @DataProvider(name = "incorrectLoginData")
    public static Object[][] getIncorrectLoginData() {
        return new Object[][] {{LOCKED_USER, PASSWORD, ERROR_LOCKED},
                {"", PASSWORD, ERROR_USERNAME_REQUIRED},
                {STANDARD_USER, "", ERROR_PASSWORD_REQUIRED},
                {"Standard_user", PASSWORD, ERROR_INVALID_CREDENTIALS}};
    }

    @DataProvider(name = "correctLoginData")
    public static Object[][] getCorrectLoginData() {
        return new Object[][] {{STANDARD_USER, PASSWORD}, {PROBLEM_USER, PASSWORD},
                {PERFORMANCE_USER, PASSWORD}};
    }

    @DataProvider(name = "productsData")
    public static Object[][] getProductsData() {
        return new Object[][] {{BACKPACK, BACKPACK_NAME}, {ONESIE, ONESIE_NAME},
                {BOLT_T_SHIRT, "Sauce Labs Bolt T-Shirt"}};
    }

    @DataProvider(name = "multipleProducts")
    public static String[] getMultipleProducts() {
        return new String[] {"add-to-cart-" + BACKPACK, "add-to-cart-" + BOLT_T_SHIRT,
                "add-to-cart-" + ONESIE};
    }
}
