package com.sotsamban.guesthouse.mapper;

import com.sotsamban.guesthouse.domain.guest.Guest;
import com.sotsamban.guesthouse.dto.request.guest.GuestRequest;
import com.sotsamban.guesthouse.dto.response.guest.GuestResponse;
import org.springframework.stereotype.Component;

@Component
public class GuestMapper {

    public Guest toEntity(GuestRequest request) {
        if (request == null) return null;

        Guest guest = new Guest();
        guest.setFirstName(request.getFirstName());
        guest.setLastName(request.getLastName());
        guest.setEmail(request.getEmail());
        guest.setPhone(request.getPhone());
//        guest.setAddress(request.getAddress());
//        guest.setCity(request.getCity());
//        guest.setState(request.getState());
//        guest.setCountry(request.getCountry());
//        guest.setDateOfBirth(request.getDateOfBirth());

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

        return response;
    }

}
