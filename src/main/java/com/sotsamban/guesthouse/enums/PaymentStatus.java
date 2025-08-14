package com.sotsamban.guesthouse.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sotsamban.guesthouse.components.AbstractEnumConverter;

/**
 * A class can be used for getting RoomStatus enum
 */
public enum PaymentStatus implements GenericEnum<PaymentStatus, String> {
    PENDING("1"),
    PAID("2"),
    FAILED("3"),
    REFUNDED("4")
    ;

    private final String value;

    private PaymentStatus(String value) {
        this.value = value;
    }

    /**
     * Method fromValue : Check Enum value
     *
     * @param value value that have to check
     * @return enum value
     */
    @JsonCreator
    public static PaymentStatus fromValue(String value) {
        for(PaymentStatus status: PaymentStatus.values()) {
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
        if("1".equals(value)) label = "Pending";
        else if("2".equals(value)) label = "Paid";
        else if("3".equals(value)) label = "Failed";
        else if("4".equals(value)) label = "Refunded";

        return label;
    }

    public static class Converter extends AbstractEnumConverter<PaymentStatus, String> {

        public Converter() {
            super(PaymentStatus.class);
        }

    }
}
