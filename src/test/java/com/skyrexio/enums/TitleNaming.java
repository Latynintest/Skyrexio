package com.skyrexio.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
public enum TitleNaming {
    PRODUCTS("Products"), CART("Your Cart"), CHECKOUT("Checkout: Your Information");

    private final String displayName;

    /*
     * Ручной конструктор TitleNaming(String displayName) { this.displayName = displayName; } 
    * Ручной геттер public String getDisplayName() { return displayName; }
     */
}
