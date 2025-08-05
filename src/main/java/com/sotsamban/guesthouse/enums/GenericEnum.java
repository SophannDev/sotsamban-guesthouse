package com.sotsamban.guesthouse.enums;

public interface GenericEnum<E extends Enum<E>, T> {
    T getValue();
    String getLabel();
}
