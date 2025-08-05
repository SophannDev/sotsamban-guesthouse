package com.sotsamban.guesthouse.dto.response.staff;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StaffResponse {

    private Long staffId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String position;
    private BigDecimal salary;
    private String createdAt;
    private String updatedAt;


    @Builder
    public StaffResponse(Long staffId, String firstName, String lastName, String email, String phone,
                         String position, BigDecimal salary, String createdAt, String updatedAt) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.position = position;
        this.salary = salary;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}