package com.sotsamban.guesthouse.dto.response.room;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public interface IRoomResponse {

    @Value("#{target.room_id}")
    Long getRoomId();

    @Value("#{target.room_number}")
    String getRoomNumber();

    @Value("#{target.sts}")
    String getStatus();

    @Value("#{target.room_type_id}")
    Long getRoomTypeId();

    @Value("#{target.room_type_name}")
    String getRoomTypeName();

    @Value("#{target.price_per_night}")
    BigDecimal getPricePerNight();

    @Value("#{target.base_price}")
    BigDecimal getBasePrice();

    @Value("#{target.image_url}")
    String getImageUrl();


}
