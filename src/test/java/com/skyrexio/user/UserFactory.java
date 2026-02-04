package com.skyrexio.user;

import com.skyrexio.utils.PropertyReader;

public class UserFactory {
    public static User withAdminPermission() {
        return new User(PropertyReader.getProperty("saucedemmo.admin_user"),
                PropertyReader.getProperty("saucedemmo.password"));
    }

    public static User wihtLockedPermission() {
        return new User(PropertyReader.getProperty("saucedemmo.locked_user"),
                PropertyReader.getProperty("saucedemmo.password"));
    }
}
