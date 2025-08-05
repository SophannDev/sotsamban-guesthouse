package com.sotsamban.guesthouse.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sotsamban.guesthouse.components.AbstractEnumConverter;

/**
 * A class can be used for getting RoomStatus enum
 */
public enum RoomTypeStatus implements GenericEnum<RoomTypeStatus, String> {
    SINGLE_BED("1"),
    DOUBLE_BED("2"),
    ;

    private final String value;

    private RoomTypeStatus(String value) {
        this.value = value;
    }

    /**
     * Method fromValue : Check Enum value
     *
     * @param value value that have to check
     * @return enum value
     */
    @JsonCreator
    public static RoomTypeStatus fromValue(String value) {
        for(RoomTypeStatus status: RoomTypeStatus.values()) {
            if(status.value.equals(value)) {
                return status;
            }
        }

        return null;
    }

    /**
     * Method getValue : Get Enum value
     * @return Enum value
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Method getLabel : Get Enum Label
     * @return Enum Label
     */
    @Override
    public String getLabel() {
        String label = "(no label)";
        if("1".equals(value)) label = "Single Bed";
        else if("2".equals(value)) label = "Double Bed";

        return label;
    }

    public static class Converter extends AbstractEnumConverter<RoomTypeStatus, String> {

        public Converter() {
            super(RoomTypeStatus.class);
        }

    }
}
