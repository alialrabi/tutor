package com.tutor.business;

import lombok.Getter;

@Getter
public enum DayOfWeekEnum {

    MONDAY(1, "Monday", "الاثنين"),
    TUESDAY(2, "Tuesday", "الثلاثاء"),
    WEDNESDAY(3, "Wednesday", "الأربعاء"),
    THURSDAY(4, "Thursday", "الخميس"),
    FRIDAY(5, "Friday", "الجمعة"),
    SATURDAY(6, "Saturday", "السبت"),
    SUNDAY(7, "Sunday", "الأحد");


    private final Integer value;
    private final String nameEn;
    private final String nameAr;

    DayOfWeekEnum(int value, String nameEn, String nameAr) {
        this.value = value;
        this.nameEn = nameEn;
        this.nameAr = nameAr;
    }

    public static DayOfWeekEnum fromValue(Integer value) {
        for (DayOfWeekEnum day : values()) {
            if (day.value == value) {
                return day;
            }
        }
        throw new IllegalArgumentException("Invalid day value: " + value);
    }
}