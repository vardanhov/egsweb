package com.egswebapp.egsweb.model.enums;

/**
 * Language interface
 *
 */
public enum Language {

    /**
     * English languages
     */
    ENG(1, "Eng"),


    /**
     * French languages
     */
    FR(2, "Fr"),


    /**
     * German languages
     */
    DEU(3, "Deu");

    /***/
    private int value;

    private String name;

    Language(int value, String name) {
        this.value = value;
        this.name = name;
    }


    public static Language forName(final String name) {
        for (Language item : Language.values()) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;

    }

    public static Language forValue(final int value) {
        for (Language item : Language.values()) {
            if (item.getValue() == value) {
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
