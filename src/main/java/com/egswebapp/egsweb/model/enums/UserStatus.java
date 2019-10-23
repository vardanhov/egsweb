package com.egswebapp.egsweb.model.enums;

/**
 * UserStatus enum
 */

public enum UserStatus {


    ACTIVE(1, "ACTIVE"),
    INACTIVE(2, "INACTIVE"),
    DELETE(3, "DELETE"),
    LOCKED(4, "LOCKED");;


    private int value;

    private String name;


    UserStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }


    public static UserStatus ofValue(final int value) {
        for (UserStatus item : UserStatus.values()) {
            if (item.getValue() == value) {
                return item;
            }
        }

        return null;
    }

    public static UserStatus ofName(final String name) {
        for (UserStatus item : UserStatus.values()) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }

        return null;
    }


    public String getName() {
        return name;
    }


    public int getValue() {
        return value;
    }
}
