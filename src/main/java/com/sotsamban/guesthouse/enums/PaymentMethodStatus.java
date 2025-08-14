package com.sotsamban.guesthouse.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sotsamban.guesthouse.components.AbstractEnumConverter;

/**
 * A class can be used for getting RoomStatus enum
 */
public enum PaymentMethodStatus implements GenericEnum<PaymentMethodStatus, String> {
    CASH("1"),
    CREDIT_CARD("2"),
    BANK_TRANSFER("3"),
    ;

    private final String value;

    private PaymentMethodStatus(String value) {
        this.value = value;
    }

    /**
     * Method fromValue : Check Enum value
     *
     * @param value value that have to check
     * @return enum value
     */
    @JsonCreator
    public static PaymentMethodStatus fromValue(String value) {
        for(PaymentMethodStatus status: PaymentMethodStatus.values()) {
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
        if("1".equals(value)) label = "Cash";
        else if("2".equals(value)) label = "Credit Card";
        else if("3".equals(value)) label = "Bank Transfer";

        return label;
    }

    public static class Converter extends AbstractEnumConverter<PaymentMethodStatus, String> {

        public Converter() {
            super(PaymentMethodStatus.class);
        }
    }
}
