package com.sotsamban.guesthouse.common;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultiSortBuilder {
    private final List<Order> orders;

    public MultiSortBuilder() {
        this.orders = new ArrayList<>();
    }

    private String getProperty(String property){
        return switch (property){
            case "account_no" -> "accountNumber";
            case "bank_code" -> "bankName";
            default -> property;
        };
    }

    public final MultiSortBuilder with(String sortDirection) {

        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(sortDirection + ",");
        while (matcher.find()) {
            String field = matcher.group(1);
            String direction = matcher.group(3);

            if (field.equals("sts")) {
                // For status field, invert the sort direction of bllr status in customer tap
                Sort.Direction actualDirection = Sort.Direction.fromString(direction);
                Sort.Direction invertedDirection = (actualDirection == Sort.Direction.ASC)
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC;
                orders.add(new Order(invertedDirection, field));
            }else if (field.equals("status")) {
                // For status field, invert the sort direction of bllr status in billing report
                Sort.Direction actualDirection = Sort.Direction.fromString(direction);
                Sort.Direction invertedDirection = (actualDirection == Sort.Direction.ASC)
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC;
                orders.add(new Order(invertedDirection, field));
            }
            else {
                orders.add(new Order(Sort.Direction.fromString(direction), getProperty(field)));
            }
        }
        return this;
    }

    public List<Order> build() {
        if (orders.size() == 0)
            return Collections.emptyList();

        return orders;
    }
}
