package com.sotsamban.guesthouse.controller;

import com.sotsamban.guesthouse.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/images")
public class ImageUploadController extends BaseRestController{

    private final ImageUploadService imageUploadService;

    @PostMapping("/room/{roomId}")
    public Object uploadRoomImage(
            @PathVariable Long roomId,
            @RequestParam("file") MultipartFile[] files) {
        try {
            List<String> imageUrls = new ArrayList<>();
            for (MultipartFile file : files) {
                if (file != null && !file.isEmpty()) {
                    String imageUrl = imageUploadService.uploadRoomImage(roomId, file);
                    imageUrls.add(imageUrl);
                }
            }

            Map<String, Object> response = new HashMap<>();
            response.put("imageUrls", imageUrls);
            response.put("count", imageUrls.size());
            response.put("message", imageUrls.size() + " images uploaded successfully");
            return ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/room-type/{roomTypeId}")
    public Object uploadRoomTypeImage(
            @PathVariable Long roomTypeId,
            @RequestParam("file") MultipartFile[] files) {
        try {
            List<String> imageUrls = new ArrayList<>();
            for (MultipartFile file : files) {
                if (file != null && !file.isEmpty()) {
                    String imageUrl = imageUploadService.uploadRoomTypeImage(roomTypeId, file);
                    imageUrls.add(imageUrl);
                }
            }

            Map<String, Object> response = new HashMap<>();
            response.put("imageUrls", imageUrls);
            response.put("count", imageUrls.size());
            response.put("message", imageUrls.size() + " images uploaded successfully");
            return ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

}
