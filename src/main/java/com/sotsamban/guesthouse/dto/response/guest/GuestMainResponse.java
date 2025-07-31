package com.sotsamban.guesthouse.dto.response.guest;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sotsamban.guesthouse.common.Pagination;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GuestMainResponse {

    List<GuestResponse> guests;
    Pagination page;

    @Builder
    public GuestMainResponse(List<GuestResponse> guests, Page<?> page) {
        this.guests = guests;
        this.page = new Pagination(page);
    }
}
