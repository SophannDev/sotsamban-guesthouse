package com.sotsamban.guesthouse.controller;

import com.sotsamban.guesthouse.dto.request.roomtype.RoomTypeRequest;
import com.sotsamban.guesthouse.service.RoomTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/room_type")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class RoomTypeController extends BaseRestController{

     private final RoomTypeService roomTypeService;

     @PostMapping
     public Object createRoomType(@RequestBody @Valid RoomTypeRequest payload) {
         roomTypeService.createRoomType(payload);
         return ok();
     }



}
