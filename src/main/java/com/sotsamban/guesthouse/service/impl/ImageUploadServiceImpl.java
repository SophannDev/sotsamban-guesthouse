package com.sotsamban.guesthouse.service.impl;

import com.sotsamban.guesthouse.domain.booking.Booking;
import com.sotsamban.guesthouse.domain.booking.BookingRepository;
import com.sotsamban.guesthouse.domain.guest.Guest;
import com.sotsamban.guesthouse.domain.guest.GuestRepository;
import com.sotsamban.guesthouse.domain.room.Room;
import com.sotsamban.guesthouse.domain.room.RoomRepository;
import com.sotsamban.guesthouse.domain.roomtype.RoomType;
import com.sotsamban.guesthouse.domain.roomtype.RoomTypeRepository;
import com.sotsamban.guesthouse.domain.staff.Staff;
import com.sotsamban.guesthouse.domain.staff.StaffRepository;
import com.sotsamban.guesthouse.service.ImageUploadService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageUploadServiceImpl implements ImageUploadService {

    private final RoomTypeRepository roomTypeRepository;

    private final RoomRepository roomRepository;

    private final BookingRepository bookingRepository;

    @Value("${app.upload.dir:uploads/}")
    private String uploadDir;

    @Value("${app.base.url:http://localhost:8080}")
    private String baseUrl;

    private final List<String> allowedMimeTypes = Arrays.asList(
            "image/jpeg", "image/jpg", "image/png", "image/gif", "image/webp"
    );

    private final long maxFileSize = 5 * 1024 * 1024; // 5MB


    @Override
    public String uploadRoomImage(Long roomId, MultipartFile file) throws IOException {
        validateFile(file);

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        String imageUrl = saveImageFile(file, "room", roomId);

        // The converter handles everything automatically
        room.getImages().add(imageUrl);
        roomRepository.save(room);

        return imageUrl;
    }

    @Override
    public String uploadRoomTypeImage(Long roomTypeId, MultipartFile file) throws IOException {
        validateFile(file);

        RoomType roomType = roomTypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new RuntimeException("RoomType not found"));

        String imageUrl = saveImageFile(file, "room_type", roomTypeId);

        // The converter handles everything automatically
        roomType.getImages().add(imageUrl);
        roomTypeRepository.save(roomType);

        return imageUrl;
    }

    private String saveImageFile(MultipartFile file, String entityType, Long entityId) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalFilename = file.getOriginalFilename();
        String extension = getFileExtension(originalFilename);
        String uniqueFilename = generateUniqueFilename(entityType, entityId, extension);

        Path filePath = uploadPath.resolve(uniqueFilename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return baseUrl + "/images/" + uniqueFilename;
    }

    private void validateFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is required");
        }
        if (file.getSize() > maxFileSize) {
            throw new IllegalArgumentException("File size exceeds maximum allowed size of 5MB");
        }
        String mimeType = file.getContentType();
        if (mimeType == null || !allowedMimeTypes.contains(mimeType.toLowerCase())) {
            throw new IllegalArgumentException("Invalid file type. Only images are allowed");
        }
    }

    private String getFileExtension(String filename) {
        if (filename == null || filename.lastIndexOf('.') == -1) {
            return "";
        }
        return filename.substring(filename.lastIndexOf('.'));
    }

    private String generateUniqueFilename(String entityType, Long entityId, String extension) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String random = UUID.randomUUID().toString().substring(0, 8);
        return entityType + "_" + entityId + "_" + timestamp + "_" + random + extension;
    }
}
