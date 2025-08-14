package com.sotsamban.guesthouse.dto.response.guest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GuestResponse {
    @JsonProperty("guest_id")
    private Integer guestId;

    private String firstName;

    private String lastName;

    private String email;
    private String phone;

    private String idDocumentType;

    private String idDocumentNumber;

    private String createdAt;

    private String updatedAt;
}
