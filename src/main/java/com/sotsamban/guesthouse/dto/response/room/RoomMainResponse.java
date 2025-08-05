package com.sotsamban.guesthouse.dto.response.room;

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
public class RoomMainResponse {

    List<RoomResponse> rooms;
    Pagination page;

    @Builder
    public RoomMainResponse(List<RoomResponse> rooms, Page<?> page) {
        this.rooms = rooms;
        this.page = new Pagination(page);
    }
}
