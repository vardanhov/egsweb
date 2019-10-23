package com.egswebapp.egsweb.model.enums;


/**
 * UserProfile enum,
 *
 */
public enum UserProfile  {

    /**
     * user role type Admin
     */
    ROLE_ADMIN(1, "ROLE_ADMIN"),

    /**
     * user role type User
     */
    ROLE_USER(2, "ROLE_USER");


    private int value;

    private String name;


    UserProfile(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static UserProfile ofValue(final int value) {
        for (UserProfile item : UserProfile.values()) {
            if (item.getValue() == value) {
                return item;
            }
        }

        return null;
    }

    public static UserProfile ofName(final String name) {
        for (UserProfile item : UserProfile.values()) {
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
