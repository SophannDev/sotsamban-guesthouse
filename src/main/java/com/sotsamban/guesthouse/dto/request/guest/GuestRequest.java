package com.sotsamban.guesthouse.dto.request.guest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GuestRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String idDocumentType; // PASSPORT, DRIVING_LICENSE, NATIONAL_ID, OTHER

    private String idDocumentNumber;
}
