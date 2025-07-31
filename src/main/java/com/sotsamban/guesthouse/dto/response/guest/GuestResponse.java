package com.sotsamban.guesthouse.dto.response.guest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestResponse {
    @JsonProperty("guest_id")
    private Integer guestId;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String country;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("date_of_birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @JsonProperty("id_document_type")
    private String idDocumentType;

    @JsonProperty("id_document_number")
    private String idDocumentNumber;

    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyyMMdd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    @JsonFormat(pattern = "yyyyMMdd HH:mm:ss")
    private LocalDateTime updatedAt;
}
