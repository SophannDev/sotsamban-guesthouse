package com.sotsamban.guesthouse.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sotsamban.guesthouse.components.AbstractEnumConverter;

public enum ReservationStatus implements GenericEnum<ReservationStatus, String> {
    PENDING("1"),
    CONFIRMED("2"),
    CHECKED_IN("3"),
    CHECKED_OUT("4"),
    CANCELLED("0"),
    NO_SHOW("9"),
    ;

    private final String value;

    private ReservationStatus(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ReservationStatus fromValue(String value) {
        for(ReservationStatus status: ReservationStatus.values()) {
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

        if("1".equals(value)) label = "Pending";
        else if("2".equals(value)) label = "Confirmed";
        else if("3".equals(value)) label = "Checked In";
        else if("4".equals(value)) label = "Checked Out";
        else if("0".equals(value)) label = "Cancelled";
        else if("9".equals(value)) label = "No Show";

        return label;
    }

    public static class Converter extends AbstractEnumConverter<ReservationStatus, String> {
        public Converter() {
            super(ReservationStatus.class);
        }
    }
}
