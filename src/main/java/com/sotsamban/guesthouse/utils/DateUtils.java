package com.sotsamban.guesthouse.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    /**
     * Parse YYYYMMDDHHmmss string to LocalDateTime then back to String
     * @param dateTimeString "20250806114558"
     * @return String "20250806114558" (validated and formatted)
     */
    public static String parseFromYYYYMMDDHHmmss(String dateTimeString) {
        if (dateTimeString == null || dateTimeString.trim().isEmpty()) {
            return null;
        }

        // Validate format by parsing then formatting back
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DATETIME_FORMATTER);
            return dateTime.format(DATETIME_FORMATTER);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid datetime format. Expected: yyyyMMddHHmmss (e.g., 20250806114558)");
        }
    }

    /**
     * Get current datetime in YYYYMMDDHHmmss format
     * @return "20250806114558"
     */
    public static String getCurrentDateTimeYYYYMMDDHHmmss() {
        return LocalDateTime.now().format(DATETIME_FORMATTER);
    }

    /**
     * Format LocalDateTime to YYYYMMDDHHmmss string
     * @param dateTime LocalDateTime
     * @return "20250806114558"
     */
    public static String formatToYYYYMMDDHHmmss(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DATETIME_FORMATTER) : null;
    }

    /**
     * Parse YYYYMMDDHHmmss string to LocalDateTime (for internal use)
     * @param dateTimeString "20250806114558"
     * @return LocalDateTime 2025-08-06T11:45:58
     */
    public static LocalDateTime parseToLocalDateTime(String dateTimeString) {
        if (dateTimeString == null || dateTimeString.trim().isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(dateTimeString, DATETIME_FORMATTER);
    }

    /**
     * Validate YYYYMMDDHHmmss string format
     * @param dateTimeString "20250806114558"
     * @return true if valid format
     */
    public static boolean isValidYYYYMMDDHHmmss(String dateTimeString) {
        if (dateTimeString == null || dateTimeString.length() != 14) {
            return false;
        }
        try {
            LocalDateTime.parse(dateTimeString, DATETIME_FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Create datetime string for specific date and time
     * @param year 2025
     * @param month 8
     * @param day 6
     * @param hour 11
     * @param minute 45
     * @param second 58
     * @return "20250806114558"
     */
    public static String createDateTimeString(int year, int month, int day, int hour, int minute, int second) {
        LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute, second);
        return dateTime.format(DATETIME_FORMATTER);
    }
}