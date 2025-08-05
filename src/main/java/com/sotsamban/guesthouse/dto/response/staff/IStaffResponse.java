package com.sotsamban.guesthouse.dto.response.staff;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
public interface IStaffResponse {

    @Value("#{target.staff_id}")
    Long getStaffId();

    @Value("#{target.first_name}")
    String getFirstName();

    @Value("#{target.last_name}")
    String getLastName();

    @Value("#{target.email}")
    String getEmail();

    @Value("#{target.phone}")
    String getPhone();

    @Value("#{target.position}")
    String getPosition();

    @Value("#{target.salary}")
    BigDecimal getSalary();

    @Value("#{target.created_at}")
    String getCreatedAt();

    @Value("#{target.updated_at}")
    String getUpdatedAt();
}
