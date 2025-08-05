package com.sotsamban.guesthouse.components;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Converter
public class TimestampConverter implements AttributeConverter<LocalDateTime, String> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Override
    public String convertToDatabaseColumn(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.format(FORMATTER) : null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(String timestamp) {
        return timestamp != null ? LocalDateTime.parse(timestamp, FORMATTER) : null;
    }
}
