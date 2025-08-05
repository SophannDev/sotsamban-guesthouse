package com.sotsamban.guesthouse.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sotsamban.guesthouse.components.AbstractEnumConverter;

/**
 * A class can be used for getting RoomStatus enum
 */
public enum RoomStatus implements GenericEnum<RoomStatus, String> {
    AVAILABLE("1"),
    OCCUPIED("2"),
    MAINTENANCE("3"),
    CLEANING("4"),
    OUT_OF_ORDER("0"),
    ;

    private final String value;

    private RoomStatus(String value) {
        this.value = value;
    }

    /**
     * Method fromValue : Check Enum value
     *
     * @param value value that have to check
     * @return enum value
     */
    @JsonCreator
    public static RoomStatus fromValue(String value) {
        for(RoomStatus status: RoomStatus.values()) {
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

        if("1".equals(value)) label = "Available";
        else if("2".equals(value)) label = "Occupied";
        else if("3".equals(value)) label = "Under Maintenance";
        else if("4".equals(value)) label = "Being Cleaned";
        else if("0".equals(value)) label = "Out of Order";

        return label;
    }

    public static class Converter extends AbstractEnumConverter<RoomStatus, String> {

        public Converter() {
            super(RoomStatus.class);
        }

    }
}
