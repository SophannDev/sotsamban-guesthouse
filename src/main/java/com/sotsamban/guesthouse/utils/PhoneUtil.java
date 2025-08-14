package com.sotsamban.guesthouse.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;


@Component
public class PhoneUtil {
    // Cambodia mobile pattern
    private static final Pattern CAMBODIA_MOBILE = Pattern.compile("^(\\+855|0)(1[0-9]|6[0-9]|7[0-9]|8[0-9]|9[0-9])\\d{6}$");

    public boolean isValid(String phone) {
        return phone != null && CAMBODIA_MOBILE.matcher(clean(phone)).matches();
    }

    public String format(String phone) {
        String clean = clean(phone);
        if (clean.startsWith("0")) {
            return "+855" + clean.substring(1);
        }
        if (clean.startsWith("855")) {
            return "+" + clean;
        }
        return clean;
    }

    private String clean(String phone) {
        return phone.replaceAll("[\\s\\-\\(\\)]", "");
    }
}
