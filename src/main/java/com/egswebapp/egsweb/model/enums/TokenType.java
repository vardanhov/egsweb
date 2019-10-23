package com.egswebapp.egsweb.model.enums;

public enum TokenType {


    CONFIRM_EMAIL   (1, "Confirm Email");




    TokenType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static TokenType ofValue(int value) {
        for (TokenType item : values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }

    public static TokenType ofName(String name) {
        for (TokenType item : values()) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    private final int value;

    private final String name;
}
