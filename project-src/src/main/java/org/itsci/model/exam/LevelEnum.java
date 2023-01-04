package org.itsci.model.exam;

import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.*;

public enum LevelEnum {
    BEGINNER("1_BEGINNER"),
    INTERMEDIATE("2_INTERMEDIATE"),
    HARD("3_HARD"),
    VERY_HARD("4_VERY_HARD");

    private static List<String> values = null;
    private final String role;

    LevelEnum(String role) {
        this.role = role;
    }

    public static List<String> getLevels() {
        if (values == null) {
            values = new ArrayList<>();
            for (LevelEnum val : LevelEnum.values()) {
                values.add(val.role);
            }
        }
        return values;
    }

    public static Object getLevelOptions(ResourceBundleMessageSource messageSource, Locale locale) {
        List<String> values = getLevels();
        SortedMap<String, String> authorityOptions = new TreeMap<>();
        for (String level : values) {
            String label = messageSource.getMessage("enum.level_enum." + level, null, locale);
            authorityOptions.put(level, label);
        }
        return authorityOptions;
    }

    @Override
    public String toString() {
        return role;
    }
}
