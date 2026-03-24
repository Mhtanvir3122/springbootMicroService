package com.example.ServiceClassRoutine.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileService {

    private static final String UPLOAD_DIR = "uploads/";

    public String saveFile(MultipartFile file) throws Exception {

        // Create folder if not exists
        File folder = new File(UPLOAD_DIR);
        if (!folder.exists()) {
            folder.mkdirs();     // <-- Important!
        }

        // Unique file name
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        // Save file
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }
}
