package com.hyscaler.Online_Learning_Platform.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    
    private final String UPLOAD_DIR = "uploads/"; // Local upload directory

    public String saveFile(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(path.getParent()); // Ensure directories exist
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return path.toString(); // Return the file path
        } catch (IOException e) {
            throw new RuntimeException("File upload failed", e);
        }
    }
}
