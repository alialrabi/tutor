package com.tutor.enums;

public enum Provider {
    LOCAL(1),
    GOOGLE(2);

    private int value;

    Provider(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Provider fromValue(int value) {
        for (Provider type: Provider.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid Provider value: " + value);
    }
}
