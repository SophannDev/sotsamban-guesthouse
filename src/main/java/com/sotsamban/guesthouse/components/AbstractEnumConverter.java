package com.sotsamban.guesthouse.components;

import com.sotsamban.guesthouse.enums.GenericEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public abstract class AbstractEnumConverter<E extends Enum<E> & GenericEnum<E, T>, T>
        implements AttributeConverter<E, T> {

    private final Class<E> enumClass;

    public AbstractEnumConverter(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public T convertToDatabaseColumn(E attribute) {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public E convertToEntityAttribute(T dbData) {
        if (dbData == null) {
            return null;
        }

        // Use reflection to call the fromValue method
        try {
            java.lang.reflect.Method fromValueMethod = enumClass.getMethod("fromValue", Object.class);
            return (E) fromValueMethod.invoke(null, dbData);
        } catch (Exception e) {
            // Fallback to manual search
            for (E enumValue : enumClass.getEnumConstants()) {
                if (enumValue.getValue().equals(dbData)) {
                    return enumValue;
                }
            }
        }

        return null;
    }
}