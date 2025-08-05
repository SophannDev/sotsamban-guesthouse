package com.sotsamban.guesthouse.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sotsamban.guesthouse.components.AbstractEnumConverter;

public enum BookingStatus implements GenericEnum<BookingStatus, String> {
    ACTIVE("1"),
    COMPLETED("2"),
    CANCELLED("0"),
    ;

    private final String value;

    private BookingStatus(String value) {
        this.value = value;
    }

    @JsonCreator
    public static BookingStatus fromValue(String value) {
        for(BookingStatus status: BookingStatus.values()) {
            if(status.value.equals(value)) {
                return status;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String getLabel() {
        String label = "(no label)";

        if("1".equals(value)) label = "Active";
        else if("2".equals(value)) label = "Completed";
        else if("0".equals(value)) label = "Cancelled";

        return label;
    }

    public static class Converter extends AbstractEnumConverter<BookingStatus, String> {
        public Converter() {
            super(BookingStatus.class);
        }
    }
}
