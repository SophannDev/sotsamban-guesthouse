package com.sotsamban.guesthouse.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageUploadService {

    String uploadRoomImage(Long roomId, MultipartFile file) throws IOException;

    String uploadRoomTypeImage(Long roomTypeId, MultipartFile file) throws IOException;




}
