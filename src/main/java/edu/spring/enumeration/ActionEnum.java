package edu.spring.enumeration;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static edu.spring.utils.Constants.*;

public enum ActionEnum {

    UNDEFINED(ACTION_UNDEFINED),
    DISPLAY_JSON(ACTION_DISPLAY_JSON),
    DISPLAY_XML(ACTION_DISPLAY_XML),
    LOWER(ACTION_LOWER),
    UPPER(ACTION_UPPER),
    TRIM_FILE(ACTION_TRIM_FILE),
    TRIM_JSON(ACTION_TRIM_JSON),
    TRIM_XML(ACTION_TRIM_XML),
    JSON_TO_XML(ACTION_JSON_TO_XML),
    XML_TO_JSON(ACTION_XML_TO_JSON),
    CSV_TO_JSON(ACTION_CSV_TO_JSON);

    private String value;
    private static final Map<String,ActionEnum> LOOKUP = new HashMap<>();

    ActionEnum(String value) {
        this.value = value;
    }

    static {
        for(ActionEnum actionEnum : EnumSet.allOf(ActionEnum.class)) {
            LOOKUP.put(actionEnum.getValue(), actionEnum);
        }
    }

    public static String[] getArray() {
        return Stream.of(ActionEnum.values()).map(ActionEnum::getValue).toArray(String[]::new);
    }

    public static ActionEnum find(String value) {
        if (value != null && LOOKUP.get(value) != null) {
            return LOOKUP.get(value);
        } else {
            return ActionEnum.UNDEFINED;
        }
    }

    public String getValue() {
        return value;
    }

}
