package com.sotsamban.guesthouse.mapper;

import com.sotsamban.guesthouse.common.StatusCode;
import com.sotsamban.guesthouse.domain.guest.Guest;
import com.sotsamban.guesthouse.domain.guest.GuestRepository;
import com.sotsamban.guesthouse.dto.request.guest.GuestRequest;
import com.sotsamban.guesthouse.dto.response.guest.GuestResponse;
import com.sotsamban.guesthouse.exception.BusinessException;
import com.sotsamban.guesthouse.utils.PhoneUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GuestMapper {

    private final PhoneUtil phoneUtil;

    private final GuestRepository guestRepository;

    public Guest toEntity(GuestRequest request) {
        if (request == null) return null;

        String phone = null;
        String email = null;
        // Validate Cambodia phone format
        if (!phoneUtil.isValid(request.getPhone())) {
            throw new BusinessException(StatusCode.NOT_FOUND, "Invalid phone number format: " + request.getPhone());
        }

        // Format to standard format
        phone = phoneUtil.format(request.getPhone());
        // Check if phone already exists
        if (guestRepository.existsByPhone(phone)) {
            throw new BusinessException(StatusCode.ALREADY_EXISTS, "Phone number already exists: " + phone);
        }


        email = request.getEmail().trim().toLowerCase();

        // Basic email format validation
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new BusinessException(StatusCode.NOT_FOUND, "Invalid email format: " + email);
        }

        // Check if email already exists
        if (guestRepository.existsByEmail(email)) {
            throw new BusinessException(StatusCode.ALREADY_EXISTS, "Email already exists: " + email);
        }

        Guest guest = new Guest();
        guest.setFirstName(request.getFirstName());
        guest.setLastName(request.getLastName());
        guest.setEmail(request.getEmail());
        guest.setPhone(phone);

        if (request.getIdDocumentType() != null) {
            guest.setIdDocumentType(Guest.IdDocumentType.valueOf(request.getIdDocumentType().toUpperCase()));
        }

        guest.setIdDocumentNumber(request.getIdDocumentNumber());

        return guest;
    }

    public GuestResponse  toResponse(Guest guest) {
        if (guest == null) return null;

        GuestResponse response = new GuestResponse();
        response.setGuestId(guest.getGuestId());
        response.setFirstName(guest.getFirstName());
        response.setLastName(guest.getLastName());
        response.setEmail(guest.getEmail());
        response.setPhone(guest.getPhone());
        response.setIdDocumentType(guest.getIdDocumentType() != null ? guest.getIdDocumentType().name() : null);
        response.setIdDocumentNumber(guest.getIdDocumentNumber());
        response.setCreatedAt(guest.getCreatedAt());
        response.setUpdatedAt(guest.getUpdatedAt());

        return response;
    }

}
